package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class QueryUserResult {
    private List<UserInfo> users;
    private boolean hasMore;
    private String cursor;
    
    @Data
    public static class UserInfo {
        private String userId;
        private String nickname;
        private String signature;
        private String avatar;
        private boolean isVerified;
        private String verifiedReason;
        private int followingCount;
        private int followerCount;
        private int totalFavorited;
        private int awemeCount;
    }
} 