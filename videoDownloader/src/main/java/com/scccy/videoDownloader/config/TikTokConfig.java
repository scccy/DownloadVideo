package com.scccy.videoDownloader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tiktok")
@Data
public class TikTokConfig {
    private CookieConfig cookie;
    private int max_connections;
    private int max_counts;
    private int max_tasks;
    private int page_counts;
}
