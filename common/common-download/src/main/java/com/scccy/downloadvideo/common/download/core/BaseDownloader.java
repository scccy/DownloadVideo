package com.scccy.downloadvideo.common.download.core;

import com.scccy.downloadvideo.common.core.model.entity.DownloadConfig;
import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import com.scccy.downloadvideo.common.download.listener.DownLoadProgressListener;
import com.scccy.downloadvideo.common.download.m3u8.M3u8Downloader;
import com.scccy.downloadvideo.common.download.model.DownloadTask;
import com.scccy.downloadvideo.common.download.model.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

@Component
@Slf4j
public class BaseDownloader {

    private final DownloadFeignClient downloadClient;
    private final Map<String, String> headers;
    private final DownloadConfig downloadConfig;
    private final Map<String, DownloadTask> downloadTasks = new ConcurrentHashMap<>();
    private final Map<String, DownLoadProgressListener> listeners = new ConcurrentHashMap<>();
    private final ExecutorService downloadExecutor;
    private final BlockingQueue<DownloadTask> downloadQueue = new LinkedBlockingQueue<>();
    private final Thread queueProcessor;

    public BaseDownloader(DownloadFeignClient downloadClient, DownloadConfig downloadConfig, Map<String, String> headers) {
        this.downloadClient = downloadClient;
        this.downloadConfig = downloadConfig;
        this.headers = headers;

        this.downloadExecutor = Executors.newFixedThreadPool(downloadConfig.getCorePoolSize());
        this.queueProcessor = new Thread(this::processQueue, "download-queue-processor");
        this.queueProcessor.start();
    }

    public void addListener(String taskId, DownLoadProgressListener listener) {
        listeners.put(taskId, listener);
    }

    private long getContentLength(String url) {
        ResponseEntity<byte[]> response = downloadClient.download2Byte(url, headers);
        return response.getHeaders().getContentLength();
    }

    @Async
    public CompletableFuture<Path> downloadFile(String url, String savePath, String fileName) {
        return startDownload(url, savePath, fileName, false).toCompletableFuture();
    }

    @Async
    public CompletableFuture<Path> downloadM3u8Stream(String url, String savePath, String fileName) {
        M3u8Downloader m3u8Downloader = new M3u8Downloader(downloadClient, headers);
        return m3u8Downloader.download(url, savePath, fileName).toCompletableFuture();
    }

    private CompletableFuture<Path> startDownload(String url, String savePath, String fileName, boolean useChunks) {
        String taskId = UUID.randomUUID().toString();
        Path fullPath = Paths.get(savePath, fileName);

        DownloadTask task = new DownloadTask(taskId, url, savePath, fileName, TaskStatus.PENDING);
        downloadTasks.put(taskId, task);

        return CompletableFuture.supplyAsync(() -> {
            try {
                Files.createDirectories(fullPath.getParent());
                long contentLength = getContentLength(url);
                task.setContentLength(contentLength);

                if (useChunks) {
                    downloadWithChunks(task, fullPath, contentLength).join();
                } else {
                    downloadSingleFile(task, fullPath);
                }

                task.setStatus(TaskStatus.COMPLETED);
                return fullPath;
            } catch (Exception e) {
                task.setStatus(TaskStatus.ERROR);
                task.setError(e.getMessage());
                throw new CompletionException(e);
            }
        }, downloadExecutor);
    }

    private void downloadSingleFile(DownloadTask task, Path fullPath) throws IOException {
        ResponseEntity<byte[]> response = downloadClient.download2Byte(task.getUrl(), headers);
        Files.write(fullPath, response.getBody());
    }

    private CompletableFuture<Void> downloadWithChunks(DownloadTask task, Path fullPath, long contentLength) throws IOException {
        int chunks = downloadConfig.getChunks();
        long chunkSize = contentLength / chunks;
        Path tmpDir = Paths.get(task.getSavePath(), ".tmp");
        Files.createDirectories(tmpDir);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < chunks; i++) {
            long start = i * chunkSize;
            long end = (i == chunks - 1) ? contentLength : (i + 1) * chunkSize - 1;
            Path chunkPath = tmpDir.resolve(task.getFileName() + ".part" + i);
            futures.add(CompletableFuture.runAsync(() -> downloadChunk(task.getUrl(), chunkPath, start, end), downloadExecutor));
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    private void downloadChunk(String url, Path chunkPath, long start, long end) {
        try {
            ResponseEntity<byte[]> response = downloadClient.downloadWithRange2Byte(url, headers, String.format("bytes=%d-%d", start, end));
            Files.write(chunkPath, response.getBody(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("Chunk download failed: {}", chunkPath, e);
        }
    }

    private void mergeChunks(Path tmpDir, Path fullPath, int chunks) throws IOException {
        try (FileChannel outChannel = FileChannel.open(fullPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            for (int i = 0; i < chunks; i++) {
                Path chunkPath = tmpDir.resolve(fullPath.getFileName() + ".part" + i);
                try (FileChannel inChannel = FileChannel.open(chunkPath, StandardOpenOption.READ)) {
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                }
                Files.delete(chunkPath);
            }
        }
    }

    private void processQueue() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                DownloadTask task = downloadQueue.take();
                startDownload(task.getUrl(), task.getSavePath(), task.getFileName(), downloadConfig.getEnableChunks()).join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void shutdown() {
        queueProcessor.interrupt();
        downloadExecutor.shutdown();
        try {
            if (!downloadExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                downloadExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            downloadExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
