package com.scccy.downloadvideo.common.download.core;


import com.scccy.downloadvideo.common.core.model.entity.DownloadConfig;
import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import com.scccy.downloadvideo.common.download.listener.DownLoadProgressListener;
import com.scccy.downloadvideo.common.download.model.DownloadTask;
import com.scccy.downloadvideo.common.download.model.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class BaseDownloader {

    protected final DownloadFeignClient downloadClient;
    protected final Map<String, String> headers;
    protected final DownloadConfig downloadConfig;
    protected final Map<String, DownloadTask> downloadTasks = new ConcurrentHashMap<>();
    
    public BaseDownloader(DownloadFeignClient downloadClient, DownloadConfig downloadConfig, Map<String, String> headers) {
        this.downloadClient = downloadClient;
        this.downloadConfig = downloadConfig;
        this.headers = headers;
    }

    /**
     * 添加下载进度监听器
     */
//    public void addListener(String taskId, DownLoadProgressListener listener) {
//        listeners.put(taskId, listener);
//    }

    /**
     * 获取文件大小
     */
    protected long getContentLength(String url) throws IOException {
        ResponseEntity<byte[]> response = downloadClient.download2Byte(url, headers);
        return response.getHeaders().getContentLength();
    }

    /**
     * 下载文件
     */
    public Mono<Path> downloadFile(String url, String savePath, String fileName) throws IOException {
        String taskId = UUID.randomUUID().toString();
        Path fullPath = Paths.get(savePath, fileName);
        Path tmpPath = Paths.get(savePath, fileName + ".tmp");

        // 创建下载任务
        DownloadTask task = DownloadTask.builder()
            .id(taskId)
            .url(url)
            .savePath(savePath)
            .fileName(fileName)
            .status(TaskStatus.PENDING)
            .build();
            
        downloadTasks.put(taskId, task);

        try {
            // 确保目录存在
            File dir = new File(savePath);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            // 获取文件大小
            long contentLength = getContentLength(url);
            task.setContentLength(contentLength);

            // 检查是否存在临时文件
            long startPosition = 0;
            if(tmpPath.toFile().exists()) {
                startPosition = tmpPath.toFile().length();
            }

            // 执行下载
            String range = String.format("bytes=%d-", startPosition);
            ResponseEntity<byte[]> response = downloadClient.downloadWithRange2Byte(url, headers, range);

            try(RandomAccessFile file = new RandomAccessFile(tmpPath.toFile(), "rw")) {
                file.seek(startPosition);
                file.write(response.getBody());
                task.setDownloadedSize(file.length());
            }

            // 下载完成,重命名文件
            if(!tmpPath.toFile().renameTo(fullPath.toFile())) {
                throw new IOException("Failed to rename temp file");
            }

            task.setStatus(TaskStatus.COMPLETED);
            return Mono.just(fullPath);

        } catch (IOException e) {
            task.setStatus(TaskStatus.ERROR);
            task.setError(e.getMessage());
            throw e;
        }
    }

    /**
     * 下载M3U8流
     */
    public Mono<Path> downloadM3u8Stream(String url, String savePath, String fileName) {
        // TODO: 实现M3U8流下载
        return Mono.empty();
    }

    // 其他辅助方法...
}