package com.scccy.downloadvideo.common.core.model.entity;

import com.scccy.downloadvideo.common.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DownloadConfig extends BaseEntity {
    private String cookie;
    private String naming;
    private String path;
    private Integer timeout;
    private Integer maxRetries;
    private Boolean lyric;
    private Integer maxConnections;
    private Integer maxCounts;
    private Integer maxTasks;
    private Integer pageCounts;
    private Boolean folderize;
    private String mode;
    private String interval;
} 