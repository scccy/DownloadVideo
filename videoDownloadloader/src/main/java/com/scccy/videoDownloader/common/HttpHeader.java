package com.scccy.videoDownloader.common;

public enum HttpHeader {
    PC_CHROME("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36", "https://www.example.com"),
    MOBILE_CHROME("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Mobile Safari/537.36", "https://m.example.com"),
    BILI_DROID("Mozilla/5.0 BiliDroid/7.25.0 (bbcallen@gmail.com)", "https://www.bilibili.com");

    private final String userAgent;
    private final String referer;

    HttpHeader(String userAgent, String referer) {
        this.userAgent = userAgent;
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getReferer() {
        return referer;
    }
}