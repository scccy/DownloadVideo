package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class RelatedVideos {
    private List<VideoInfo> videoList;
    private boolean hasMore;
    
    @Data
    public static class VideoInfo {
        private String videoId;
        private String title;
        private String coverUrl;
        private String playUrl;
        private String authorId;
        private String authorName;
        private long diggCount;
        private long commentCount;
    }
} 