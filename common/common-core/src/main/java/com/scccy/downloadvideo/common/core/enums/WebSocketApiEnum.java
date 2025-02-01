package com.scccy.downloadvideo.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebSocketApiEnum {
    
    CONNECT("/connect", "建立连接"),
    SEND("/send", "发送消息"),
    DISCONNECT("/disconnect", "断开连接"),
    HEARTBEAT("/heartbeat", "心跳检测"),
    
    // 抖音直播相关
    DOUYIN_LIVE_CONNECT("/douyin/live/connect", "连接直播间"),
    DOUYIN_LIVE_DANMAKU("/douyin/live/danmaku", "发送弹幕"),
    DOUYIN_LIVE_GIFT("/douyin/live/gift", "发送礼物"),
    DOUYIN_LIVE_LIKE("/douyin/live/like", "点赞");

    private final String endpoint;
    private final String description;
} 