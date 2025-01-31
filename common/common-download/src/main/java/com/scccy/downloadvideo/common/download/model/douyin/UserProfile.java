package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

@Data
public class UserProfile {
    private String userId;
    private String nickname;
    private String signature;
    private String avatar;
    private Integer followingCount;
    private Integer followerCount;
    private Integer favoriteCount;
    // ... 其他字段
} 