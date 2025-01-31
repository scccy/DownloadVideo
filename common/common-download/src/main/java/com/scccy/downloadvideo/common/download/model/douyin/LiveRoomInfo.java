package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

@Data
public class LiveRoomInfo {
    private String roomId;
    private String title;
    private String coverUrl;
    private String streamUrl;
    private int onlineCount;
    private String status; // 直播状态
    private long startTime;
} 