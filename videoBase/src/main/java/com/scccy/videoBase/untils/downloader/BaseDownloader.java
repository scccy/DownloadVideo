package com.scccy.videoBase.untils.downloader;

import com.scccy.videoBase.handlerExption.CustomExceptions;
import com.scccy.videoBase.service.ConfigAppConfigService;
import com.scccy.videoBase.service.ConfigBaseService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
public class BaseDownloader {
    private static final int MAX_SEGMENT_COUNT = 1000;

    private final BaseCrawler baseCrawler;
    private final ExecutorService executor;
    private final List<CompletableFuture<Void>> downloadTasks = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);

    public BaseDownloader(ConfigBaseService configBaseService, Long baseId, ConfigAppConfigService configAppConfigService, Long appId) {
        this.baseCrawler = new BaseCrawler(configBaseService, baseId, configAppConfigService, appId);
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private Path ensurePath(String path) {
        return Paths.get(path);
    }

    private int getContentLength(String url) throws CustomExceptions.CustomException {
        Response response = baseCrawler.getFetchData(url, Response.class);
        String contentLengthHeader = response.header("Content-Length");
        return contentLengthHeader != null ? Integer.parseInt(contentLengthHeader) : 0;
    }

    @Async
    public void downloadChunks(String url, Path filePath, int contentLength, int taskId) {
        try {
            InputStream in = baseCrawler.getFetchData(url, InputStream.class);
            try (SeekableByteChannel out = Files.newByteChannel(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
                ByteBuffer buffer = ByteBuffer.allocate(8192);
                int bytesRead;
                while ((bytesRead = in.read(buffer.array())) != -1) {
                    buffer.flip();
                    out.write(buffer);
                    buffer.clear();
                    updateProgress(taskId, bytesRead, contentLength);
                }
            }
        } catch (IOException | CustomExceptions.CustomException e) {
            log.warn("Download chunks failed", e);
        }
    }
//todo: 用于更新下载任务的进度
    private void updateProgress(int taskId, int bytesDownloaded, int totalBytes) {
        // Implement progress update logic here
    }

    @Async
    public void downloadFile(int taskId, List<String> urls, String fullPath) throws InterruptedException, ExecutionException, IOException {
        Path path = ensurePath(fullPath);
        Files.createDirectories(path.getParent());
        for (String url : urls) {
            int contentLength = getContentLength(url);
            if (contentLength == 0) {
                continue;
            }
            Path tempPath = path.resolveSibling(path.getFileName() + ".tmp");
            long startByte = Files.exists(tempPath) ? Files.size(tempPath) : 0;
            if (startByte == contentLength) {
                Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
                return;
            }
            String finalUrl = url + "?Range=bytes=" + startByte + "-";
            CompletableFuture<Void> downloadTask = CompletableFuture.runAsync(() -> {
                downloadChunks(finalUrl, tempPath, contentLength, taskId);
                try {
                    Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    log.warn("Failed to rename temp file", e);
                }
            }, executor);
            downloadTasks.add(downloadTask);
        }
    }

    @Async
    public void downloadM3u8Stream(int taskId, String url, String fullPath) throws InterruptedException, ExecutionException, IOException {
        Path path = ensurePath(fullPath);
        Files.createDirectories(path.getParent());
        Set<String> downloadedSegments = ConcurrentHashMap.newKeySet();

        while (true) {
            List<String> segments = getSegmentsFromM3u8(url);
            if (segments.isEmpty()) {
                updateProgress(taskId, 0, 1);
                return;
            }
            try (SeekableByteChannel out = Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
                for (String segment : segments) {
                    if (downloadedSegments.contains(segment)) {
                        continue;
                    }
                    int contentLength = getContentLength(segment);
                    if (contentLength == 0) {
                        contentLength = 409600;
                    }
                    int finalContentLength = contentLength;
                    CompletableFuture<Void> downloadTask = CompletableFuture.runAsync(() -> {
                        downloadChunks(segment, path, finalContentLength, taskId);
                    }, executor);
                    downloadTasks.add(downloadTask);
                    downloadedSegments.add(segment);
                    if (downloadedSegments.size() > MAX_SEGMENT_COUNT) {
                        downloadedSegments.clear();
                    }
                }
            } catch (IOException e) {
                log.warn("Failed to download m3u8 segment", e);
            }
            Thread.sleep(1000); // Wait for new segments
        }
    }

    private List<String> getSegmentsFromM3u8(String url) {
        // Implement logic to fetch and parse M3U8 segments
        return Collections.emptyList();
    }

    @Async
    public void initiateDownload(String fileType, List<String> urls, String basePath, String fileName, String fileSuffix) throws InterruptedException, ExecutionException {
        String filePath = fileName + fileSuffix;
        Path fullPath = ensurePath(basePath).resolve(filePath);
        if (Files.exists(fullPath)) {
            updateProgress(0, 1, 1);
        } else {
            CompletableFuture.runAsync(() -> {
                try {
                    downloadFile(0, urls, fullPath.toString());
                } catch (IOException | InterruptedException | ExecutionException e) {
                    log.warn("Failed to download file", e);
                }
            }, executor).join();
        }
    }

    @Async
    public void executeTasks() throws InterruptedException, ExecutionException {
        CompletableFuture.allOf(downloadTasks.toArray(new CompletableFuture[0])).join();
        downloadTasks.clear();
    }

    public void close() {
        executor.shutdown();
    }
}