package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class VideoAnalytics {
    private String videoId;
    private ViewerStats viewerStats;
    private InteractionStats interactionStats;
    private List<TimelinePoint> timeline;
    
    @Data
    public static class ViewerStats {
        private long totalViews;
        private long uniqueViewers;
        private double averageWatchTime;
        private double completionRate;
        private Map<String, Long> deviceDistribution;
        private Map<String, Long> regionDistribution;
    }
    
    @Data
    public static class InteractionStats {
        private long shares;
        private long comments;
        private long likes;
        private long saves;
        private double engagementRate;
    }
    
    @Data
    public static class TimelinePoint {
        private long timestamp;
        private long viewCount;
        private long likeCount;
        private long commentCount;
        private long shareCount;
    }
} 