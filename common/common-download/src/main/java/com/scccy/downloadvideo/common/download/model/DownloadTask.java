package com.scccy.downloadvideo.common.download.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class DownloadTask {
    private String id;
    private String url;
    private String savePath;
    private String fileName;
    private Long contentLength;
    private Long downloadedSize;
    private TaskStatus status;
    private String error;

    public DownloadTask(String id, String url, String savePath, String fileName, Long contentLength, Long downloadedSize, TaskStatus status) {
        this.id = id;
        this.url = url;
        this.savePath = savePath;
        this.fileName = fileName;
        this.contentLength = contentLength;
        this.downloadedSize = downloadedSize;
        this.status = status;
    }

    public DownloadTask(String taskId, String url, String savePath, String fileName, TaskStatus taskStatus) {
    }

    // 添加新的构造器
    public DownloadTask(String id, String url, String savePath, String fileName, Long contentLength, Long downloadedSize, TaskStatus status, String error) {
        this.id = id;
        this.url = url;
        this.savePath = savePath;
        this.fileName = fileName;
        this.contentLength = contentLength;
        this.downloadedSize = downloadedSize;
        this.status = status;
        this.error = error;
    }
}

