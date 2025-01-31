package com.scccy.downloadvideo.common.core.config.properties;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "download")
@Component
public class DownloadProperties {


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
    private Boolean enableChunks = true;
    private String proxyHost;
    private Integer proxyPort;
} 