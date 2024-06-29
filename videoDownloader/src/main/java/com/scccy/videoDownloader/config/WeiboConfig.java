package com.scccy.videoDownloader.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weibo")
public class WeiboConfig {
    private CookieConfig cookie;
    private boolean folderize;
    private String mode;
    private int max_connections;
    private int max_counts;
    private int max_tasks;
    private int page_counts;
}