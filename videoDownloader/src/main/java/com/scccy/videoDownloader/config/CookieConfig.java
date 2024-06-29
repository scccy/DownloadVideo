package com.scccy.videoDownloader.config;

import lombok.Data;

@Data
public class CookieConfig {
    private String naming;
    private String path;
    private int timeout;
    private int max_retries;
    private String interval; // This is a common field among twitter and weibo



}
