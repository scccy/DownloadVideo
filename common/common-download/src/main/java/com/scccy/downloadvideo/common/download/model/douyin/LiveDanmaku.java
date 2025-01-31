package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class LiveDanmaku {
    private List<DanmakuMessage> messages;
    private String cursor;
    
    @Data
    public static class DanmakuMessage {
        private String type; // chat/gift/enter/like
        private String userId;
        private String nickname;
        private String content;
        private Long timestamp;
    }
} 