package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class FriendFeed {
    private List<VideoInfo> videos;
    private boolean hasMore;
    private String cursor;
    
    @Data
    public static class VideoInfo {
        private String videoId;
        private String title;
        private String coverUrl;
        private String playUrl;
        private String authorId;
        private String authorName;
        private String authorAvatar;
        private long createTime;
        private long diggCount;
        private long commentCount;
        private long shareCount;
    }
} 