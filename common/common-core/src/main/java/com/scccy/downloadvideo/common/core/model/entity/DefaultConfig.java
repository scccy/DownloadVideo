package com.scccy.downloadvideo.common.core.model.entity;

import com.scccy.downloadvideo.common.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultConfig extends BaseEntity {
    private String url;
    private Boolean music;
    private Boolean lyric;
    private Boolean cover;
    private String desc;
    private String path;
    private Boolean folderize;
    private String mode;
    private String naming;
    private String cookie;
    private String interval;
    private Integer timeout;
    private Integer maxRetries;
    private Integer maxConnections;
    private Integer maxCounts;
    private Integer maxTasks;
    private Integer pageCounts;
    private String languages;
} 