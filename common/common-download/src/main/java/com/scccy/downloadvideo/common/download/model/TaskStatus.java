package com.scccy.downloadvideo.common.download.model;

public enum TaskStatus {
    PENDING,
    DOWNLOADING,
    COMPLETED,
    ERROR,
    CANCELLED,
    PAUSED // 添加 PAUSED 状态
}
