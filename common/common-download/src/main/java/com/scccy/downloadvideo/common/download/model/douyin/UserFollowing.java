package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class UserFollowing {
    private List<FollowingUser> followingList;
    private boolean hasMore;
    private String cursor;
    
    @Data
    public static class FollowingUser {
        private String userId;
        private String nickname;
        private String avatar;
        private String signature;
        // ... 其他字段
    }
} 