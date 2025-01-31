package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class TopicList {
    private List<Topic> topics;
    private boolean hasMore;
    private String cursor;
    
    @Data
    public static class Topic {
        private String topicId;
        private String title;
        private String description;
        private String coverUrl;
        private long viewCount;
        private long discussCount;
        private List<String> relatedTags;
    }
} 