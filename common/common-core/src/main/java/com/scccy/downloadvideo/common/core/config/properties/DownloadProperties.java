package com.scccy.downloadvideo.common.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Validated
@ConfigurationProperties(prefix = "download")
@Component
public class DownloadProperties {
    @NotNull
    private String path = "Download";
    private Boolean folderize = true;
    private String naming = "{create}_{desc}";
    private String cookie;
    private Integer timeout = 10;
    private Integer maxRetries = 5;
    private Integer maxConnections = 5;
    private Integer maxCounts = 0;
    @Min(1)
    @Max(100)
    private Integer maxTasks = 10;
    private Integer pageCounts = 20;
    private Integer corePoolSize = 20;
    private Integer chunks = 20;
    private Boolean enableChunks = true;
    private String proxyHost;
    private Integer proxyPort;
} 