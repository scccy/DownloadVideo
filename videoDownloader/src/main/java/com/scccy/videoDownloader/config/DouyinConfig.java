package com.scccy.videoDownloader.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "douyin")
public class DouyinConfig {
    private CookieConfig cookie;
    private boolean lyric;
    private int max_connections;
    private int max_counts;
    private int max_tasks;
    private int page_counts;
}
