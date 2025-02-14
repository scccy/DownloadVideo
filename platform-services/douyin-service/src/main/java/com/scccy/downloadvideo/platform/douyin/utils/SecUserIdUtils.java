package com.scccy.downloadvideo.platform.douyin.utils;

import com.scccy.downloadvideo.common.core.enums.DouyinDownloadEnum;
import com.scccy.downloadvideo.common.core.utils.Utils;
import com.scccy.downloadvideo.common.download.core.BaseHttpClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SecUserIdUtils {
    static BaseHttpClient baseHttpClient = new BaseHttpClient();

    private static final Pattern REDIRECT_URL_PATTERN = Pattern.compile("sec_uid=([^&]+)");
    private static final Pattern DOUYIN_URL_PATTERN = Pattern.compile("user/([^/?]+)");

    public static Mono<String> getSecUserId(String url) {
        return getSecUserId(url, null, null);
    }

    public static Mono<String> getSecUserId(String url, String host, Integer port) {
        if (url == null || url.isEmpty()) {
            return Mono.empty();
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", DouyinDownloadEnum.USER_AGENT.getValue());

        return baseHttpClient.getAsync(Utils.extractValidUrl(url), headers, host, port)
//                .mapNotNull(result -> result.getData())
                .map(Object::toString)
                .map(SecUserIdUtils::extractId)
                .filter(id -> id != null);
    }

    public static Flux<String> getAllSecUserId(List<String> urls) {
        return getAllSecUserId(urls, null, null);
    }

    public static Flux<String> getAllSecUserId(List<String> urls, String host, Integer port) {
        if (urls == null || urls.isEmpty()) {
            return Flux.empty();
        }

        return Flux.fromIterable(urls)
                .flatMap(url -> getSecUserId(url, host, port))
                .distinct();
    }

    private static String extractId(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        Pattern pattern = url.contains("v.douyin.com") ? REDIRECT_URL_PATTERN : DOUYIN_URL_PATTERN;
        Matcher matcher = pattern.matcher(url);
        return matcher.find() ? matcher.group(1) : null;
    }
} 