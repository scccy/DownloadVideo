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
public class AwemeUtils {
    private static final BaseHttpClient baseHttpClientImpl = new BaseHttpClient();

    private static final Pattern[] ID_PATTERNS = {
            Pattern.compile("video/(\\d+)"),
            Pattern.compile("note/(\\d+)")
  // 匹配19位数字ID
    };

    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();
    static {
        DEFAULT_HEADERS.put("User-Agent", DouyinDownloadEnum.USER_AGENT.getValue());
    }

    /**
     * 获取单个视频的 aweme_id
     */
    public static Mono<String> getAwemeId(String url) {
        return getAwemeId(url, null, null);
    }

    /**
     * 获取单个视频的 aweme_id（带代理）
     */
    public static Mono<String> getAwemeId(String url, String host, Integer port) {
        if (url == null || url.isEmpty()) {
            return Mono.empty();
        }

        return baseHttpClientImpl.getAsync(Utils.extractValidUrl(url), DEFAULT_HEADERS, host, port)
                .map(Object::toString)
                .map(AwemeUtils::extractId)
                .filter(id -> id != null);
    }

    /**
     * 批量获取视频的 aweme_id
     */
    public static Flux<String> getAwemeIds(List<String> urls) {
        return getAwemeIds(urls, null, null);
    }

    /**
     * 批量获取视频的 aweme_id（带代理）
     */
    public static Flux<String> getAwemeIds(List<String> urls, String host, Integer port) {
        if (urls == null || urls.isEmpty()) {
            return Flux.empty();
        }

        return Flux.fromIterable(urls)
                .flatMap(url -> getAwemeId(url, host, port))
                .distinct();
    }

    /**
     * 从URL中提取ID
     */
    private static String extractId(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        for (Pattern pattern : ID_PATTERNS) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String id = matcher.group(1);
                // 验证ID格式
                if (id.matches("\\d+") && id.length() >= 19) {
                    return id;
                }
            }
        }
        return null;
    }
}
