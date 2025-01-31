package com.scccy.downloadvideo.common.download.m3u8;

import lombok.Data;

@Data
public class M3u8Segment {
    private String url;
    private double duration;
    
    public M3u8Segment(String url) {
        this.url = url;
    }
} 