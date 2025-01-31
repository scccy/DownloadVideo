package com.scccy.downloadvideo.common.download.listener;

public interface DownloadProgressListenerImpl {
    void onProgress(String taskId, long downloaded, long total);
    void onComplete(String taskId);
    void onError(String taskId, Throwable error);
} 