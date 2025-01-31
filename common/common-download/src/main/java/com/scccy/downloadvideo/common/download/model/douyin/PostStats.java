package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

@Data
public class PostStats {
    private String videoId;
    private long playCount;
    private long diggCount;
    private long commentCount;
    private long shareCount;
    private long downloadCount;
    private long forwardCount;
} 