package com.scccy.downloader.pojo.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;


@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppSettings implements Serializable {
    @Value("${app.douyin.cookie}")
    private String cookie;
    @Value("${app.douyin.naming}")
    private String naming;
    @Value("${app.douyin.path}")
    private String path;
    @Value("${app.douyin.timeout}")
    private Integer timeout;
    @Value("${app.douyin.max_retries}")
    private Integer max_retries;
    @Value("${app.douyin.lyric}")
    private String lyric;
    @Value("${app.douyin.max_connections}")
    private Integer max_connections;
    @Value("${app.douyin.max_counts}")
    private Integer max_counts;
    @Value("${app.douyin.max_tasks}")
    private Integer max_tasks;
    @Value("${app.douyin.page_counts}")
    private Integer page_counts;
    
}
