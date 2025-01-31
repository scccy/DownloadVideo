package com.scccy.downloadvideo.common.download.listener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownLoadProgressListener implements DownloadProgressListenerImpl {
    
    @Override
    public void onProgress(String taskId, long downloaded, long total) {
        double progress = (double) downloaded / total * 100;
        log.info("Download progress: {}%, {}/{} bytes", 
            String.format("%.2f", progress), downloaded, total);
    }
    
    @Override
    public void onComplete(String taskId) {
        log.info("Download completed: {}", taskId);
    }
    
    @Override
    public void onError(String taskId, Throwable error) {
        log.error("Download error: {}, {}", taskId, error.getMessage());
    }
} 