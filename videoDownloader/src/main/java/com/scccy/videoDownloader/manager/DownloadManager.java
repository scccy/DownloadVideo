package com.scccy.videoDownloader.manager;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class DownloadManager {
    private final DownloadService downloadService;
    private final ConcurrentHashMap<String, DownloadTask> tasks = new ConcurrentHashMap<>();
    
    public String startDownload(String url, String savePath) {
        String taskId = generateTaskId();
        DownloadTask task = new DownloadTask(url, savePath);
        tasks.put(taskId, task);
        
        CompletableFuture.runAsync(() -> {
            try {
                downloadService.download(url, savePath);
                task.setStatus(DownloadStatus.COMPLETED);
            } catch (Exception e) {
                task.setStatus(DownloadStatus.FAILED);
                task.setError(e.getMessage());
            }
        });
        
        return taskId;
    }
    
    public DownloadProgress getProgress(String taskId) {
        DownloadTask task = tasks.get(taskId);
        return task != null ? task.getProgress() : null;
    }
} 