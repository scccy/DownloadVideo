package com.scccy.downloadvideo.common.core.model.message;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressMessage {
    // 下载ID
    private String downloadId;
    
    // 总大小
    private long totalSize;
    
    // 已下载大小
    private long downloadedSize;
    
    // 下载进度(百分比)
    private int progress;
    
    // 下载状态
    private DownloadStatus status;
    
    // 错误信息
    private String errorMessage;
    private String url;
    private String message;

    public enum DownloadStatus {
        PENDING,
        DOWNLOADING,
        COMPLETED,
        FAILED
    }
} 