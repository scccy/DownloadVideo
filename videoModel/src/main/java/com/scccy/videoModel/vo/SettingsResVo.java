package com.scccy.videoModel.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsResVo {
    private String accountsUrls;
    private String accountsUrlsTikTok;
    private String mixUrls;
    private String mixUrlsTikTok;
    private String ownerUrl;
    private String ownerUrlTikTok;
    private String root;
    private String folderName;
    private String nameFormat;
    private String dateFormat;
    private String split;
    private boolean folderMode;
    private boolean music;
    private String storageFormat;
    private String cookie;
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

}
