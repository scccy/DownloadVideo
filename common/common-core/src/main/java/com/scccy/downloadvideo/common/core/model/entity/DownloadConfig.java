package com.scccy.downloadvideo.common.core.model.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class DownloadConfig {
    private String path = "Download";
    private Boolean folderize = true;
    private String naming = "{create}_{desc}";
    private String cookie;
    private Integer timeout = 10;
    private Integer maxRetries = 5;
    private Integer maxConnections = 5;
    private Integer maxCounts = 0;
    private Integer maxTasks = 10;
    private Integer pageCounts = 20;
    private Integer corePoolSize = 20;
    private Integer chunks = 20;
    private Boolean EnableChunks = true;

    // 代理配置
    private String proxyHost;
    private Integer proxyPort;

    // 新增 enableChunks 属性
    private Boolean enableChunks = true;

    public Boolean getEnableChunks() {
        return enableChunks;
    }

    public void setEnableChunks(Boolean enableChunks) {
        this.enableChunks = enableChunks;
    }

}