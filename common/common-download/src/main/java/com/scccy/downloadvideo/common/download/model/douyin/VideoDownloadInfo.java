package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

@Data
public class VideoDownloadInfo {
    private String url;
    private String wm_url; // 带水印
    private String hd_url; // 高清
    private long size;
    private String format;
    private int width;
    private int height;
    private int duration;
} 