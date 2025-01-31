package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

@Data
public class LoginQrcode {
    private String token;
    private String url;
    private long expireTime;
} 