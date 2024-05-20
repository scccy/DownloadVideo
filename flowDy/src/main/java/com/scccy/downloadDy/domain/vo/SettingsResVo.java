package com.scccy.downloadDy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsResVo {
    private List<AccountUrlVO> accountsUrls;
    private String accountsUrlsTikTok;
    private List<MixUrlVO> mixUrls;
    private String mixUrlsTikTok;
    private OwnerUrlVO ownerUrl;
    private String ownerUrlTikTok;
    private String root;
    private String folderName;
    private String nameFormat;
    private String dateFormat;
    private String split;
    private boolean folderMode;
    private boolean music;
    private String storageFormat;
    private CookieVO cookie;
    private String cookieTikTok;
    private boolean dynamicCover;
    private boolean originalCover;
    private String proxies;
    private String proxiesTikTok;
    private boolean download;
    private int maxSize;
    private int chunk;
    private int maxRetry;
    private int maxPages;
    private String defaultMode;
    private String ffmpeg;
    private boolean updateCookie;
    private boolean updateCookieTikTok;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountUrlVO {
        private String mark;
        private String url;
        private String tab;
        private String earliest;
        private String latest;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MixUrlVO {
        private String mark;
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OwnerUrlVO {
        private String mark;
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CookieVO {
        private String passportCsrfToken;
        private String passportCsrfTokenDefault;
        private String odinTt;
    }
}
