package com.scccy.videoDownloader.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlowDyIntegration {
    private final DownloadManager downloadManager;
    private final FfmpegQueueService ffmpegQueueService;
    
    public void processVideo(String url) {
        // 1. 下载视频
        String taskId = downloadManager.startDownload(url, getTempPath());
        
        // 2. 监控下载进度
        while (!isDownloadComplete(taskId)) {
            DownloadProgress progress = downloadManager.getProgress(taskId);
            // 更新进度...
        }
        
        // 3. 转码处理
        ffmpegQueueService.addToQueue(getVideoPath(taskId));
    }
} 