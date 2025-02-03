package com.scccy.downloadvideo.platform.douyin.core;

import com.scccy.downloadvideo.common.core.utils.Utils;

import java.util.regex.Pattern;

public class DouyinDownLoader {

    private static final Pattern DOUYIN_URL_PATTERN = Pattern.compile("user/([^/?]*)");
    private static final Pattern REDIRECT_URL_PATTERN = Pattern.compile("sec_uid=([^&]*)");

    public String getAwemeId(String url) {
        Utils.extractValidUrls(url);
        return "secUserId";
    }
}
