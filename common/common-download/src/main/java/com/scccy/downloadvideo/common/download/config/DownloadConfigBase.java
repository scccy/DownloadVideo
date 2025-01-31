package com.scccy.downloadvideo.common.download.config;

import lombok.Data;

@Data
public class DownloadConfigBase {
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
    // 代理配置
    private String proxyHost;
    private Integer proxyPort;
}