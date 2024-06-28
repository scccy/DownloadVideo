package com.scccy.videoDownloader.base;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Acquirer {
    private static final String USER_AGENT = "com.ss.android.ugc.trill/494+Mozilla/5.0+(Linux;+Android+12;+2112123G+Build/SKQ1.211006.001;+wv)"
            + "+AppleWebKit/537.36+(KHTML,+like+Gecko)+Version/4.0+Chrome/107.0.5304.105+Mobile+Safari/537.36";

    private static final Map<String, String> API_URLS = Map.ofEntries(
            Map.entry("mixListApi", "https://www.douyin.com/aweme/v1/web/mix/listcollection/"),
            Map.entry("feedApi", "https://www.douyin.com/aweme/v1/web/tab/feed/"),
            Map.entry("spotlightApi", "https://www.douyin.com/aweme/v1/web/im/spotlight/relation/"),
            Map.entry("familiarApi", "https://www.douyin.com/aweme/v1/web/familiar/feed/"),
            Map.entry("followApi", "https://www.douyin.com/aweme/v1/web/follow/feed/"),
            Map.entry("historyApi", "https://www.douyin.com/aweme/v1/web/history/read/"),
            Map.entry("followingApi", "https://www.douyin.com/aweme/v1/web/user/following/list/"),
            Map.entry("recommendApi", "https://www.tiktok.com/api/recommend/item_list/"),
            Map.entry("homeTiktokApi", "https://www.tiktok.com/api/post/item_list/"),
            Map.entry("userTiktokApi", "https://www.tiktok.com/api/user/detail/"),
            Map.entry("relatedTiktokApi", "https://www.tiktok.com/api/related/item_list/"),
            Map.entry("commentTiktokApi", "https://www.tiktok.com/api/comment/list/"),
            Map.entry("replyTiktokApi", "https://www.tiktok.com/api/comment/list/reply/")
    );

    public final Map<String, String> pcHeaders;
    public final Map<String, String> blackHeaders;
    public final String xb;
    public final boolean console;
    public final String proxies;
    public final int maxRetry;
    public final int timeout;
    public final String cookie;
    public int cursor;
    public final List<Map<String, Object>> response;
    public boolean finished;

    public Acquirer(Parameter params, String cookie) {
        this.pcHeaders = initHeaders(params.headers);
        this.blackHeaders = Map.of("User-Agent", params.headers.get("User-Agent"));
        this.xb = params.xb;
        this.console = params.console;
        this.proxies = params.proxies;
        this.maxRetry = params.maxRetry;
        this.timeout = params.timeout;
        this.cookie = params.cookie;
        this.cursor = 0;
        this.response = new ArrayList<>();
        this.finished = false;
        setTempCookie(cookie);
    }

    private Map<String, String> initHeaders(Map<String, String> headers) {
        Map<String, String> result = new HashMap<>(headers);
        result.put("Referer", "https://www.douyin.com/");
        return result;
    }

    public Map<String, Object> sendRequest(String url, Map<String, String> params, String method, Map<String, String> headers) {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(timeout)).build();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "?" + URLEncoder.encode(params.toString(), "UTF-8")))
                    .timeout(Duration.ofMillis(timeout))
                    .header("User-Agent", headers.getOrDefault("User-Agent", USER_AGENT))
                    .method(method.toUpperCase(), HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return parseJson(response.body());
        } catch (IOException | InterruptedException e) {
            log.warn(String.format("网络异常，请求 %s 失败", url));
            return Map.of();
        }
    }

    private Map<String, Object> parseJson(String responseBody) {
        try {
            // Use your preferred JSON library here
            // return new ObjectMapper().readValue(responseBody, Map.class);
            return Map.of(); // Placeholder
        } catch (Exception e) {
            log.warn(String.format("响应内容不是有效的 JSON 格式：%s", responseBody));
            return Map.of();
        }
    }

    public void dealUrlParams(Map<String, String> params, int number) {
        addMsToken(params);
        params.put("X-Bogus", getXb(params, number));
    }

    private void addMsToken(Map<String, String> params) {
        if (cookie != null && cookie.contains("msToken")) {
            params.put("msToken", cookie);
        }
    }

    public void dealItemData(List<Map<String, Object>> data, int start, int end) {
        for (Map<String, Object> item : data.subList(start, end)) {
            response.add(item);
        }
    }

    public void progressObject() {
        // Implement progress handling here
    }

    private void setTempCookie(String cookie) {
        if (cookie != null) {
            pcHeaders.put("Cookie", cookie);
        }
    }

    private String getXb(Map<String, String> params, int number) {
        // Implement X-Bogus calculation or fetching here
        return "X-Bogus-Value"; // Placeholder
    }

    public static class Parameter {
        public Map<String, String> headers;
        public String xb;
        public boolean console;
        public String proxies;
        public int maxRetry;
        public int timeout;
        public String cookie;

        public Parameter(Map<String, String> headers, String xb, boolean console, String proxies, int maxRetry, int timeout, String cookie) {
            this.headers = headers;
            this.xb = xb;
            this.console = console;
            this.proxies = proxies;
            this.maxRetry = maxRetry;
            this.timeout = timeout;
            this.cookie = cookie;
        }
    }
}
