package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class UserCollects {
    private List<CollectInfo> collects;
    private boolean hasMore;
    private String cursor;
    
    @Data
    public static class CollectInfo {
        private String collectId;
        private String title;
        private String coverUrl;
        private int videoCount;
        private long createTime;
    }
} 