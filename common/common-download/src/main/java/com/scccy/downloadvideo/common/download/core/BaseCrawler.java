package com.scccy.downloadvideo.common.download.core;

import com.alibaba.fastjson2.JSONObject;

import com.google.protobuf.ServiceException;
import com.scccy.downloadvideo.common.download.feign.CrawlerFeignClient;
import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public abstract class BaseCrawler {
    
    protected final CrawlerFeignClient crawlerClient;
    protected final DownloadFeignClient downloadClient;
    protected final Map<String, String> headers;
    protected final Map<String, String> proxies;

    public BaseCrawler(CrawlerFeignClient crawlerClient,
                      DownloadFeignClient downloadClient,
                      Map<String, String> headers,
                      Map<String, String> proxies) {
        this.crawlerClient = crawlerClient;
        this.downloadClient = downloadClient;
        this.headers = headers;
        this.proxies = proxies;
    }

    /**
     * GET请求并解析响应
     */
//    protected <T> Mono<T> fetchGet(String url, Class<T> clazz) {
//        return Mono.fromCallable(() -> crawlerClient.get(url, headers, proxies))
//                .map(response -> ApiResponse.parseData(response.getBody(), clazz));
//    }
//
//    /**
//     * POST请求并解析响应
//     */
//    protected <T> Mono<T> fetchPost(String url, Object body, Class<T> clazz) {
//        return Mono.fromCallable(() ->
//            crawlerClient.post(url, JSONObject.toJSONString(body), headers, proxies))
//                .map(response -> ApiResponse.parseData(response.getBody(), clazz));
//    }

    /**
     * 获取原始响应
     */
    protected Mono<ResponseEntity<String>> fetchResponse(String url) {
        return Mono.fromCallable(() -> crawlerClient.get(url, headers, proxies));
    }

    /**
     * POST获取原始响应
     */
    protected Mono<ResponseEntity<String>> fetchPostResponse(String url, Object body) {
        return Mono.fromCallable(() ->
            crawlerClient.post(url, JSONObject.toJSONString(body), headers, proxies));
    }

    /**
     * 发送GET请求并获取二进制响应
     */
//    protected Mono<ResponseEntity<byte[]>> fetchBytes(String url) {
//        return Mono.fromCallable(() -> downloadClient.download2Byte(url, headers))
//                .onErrorResume(e -> {
//                    log.error("Failed to fetch bytes from: {}", url, e);
//                    return Mono.error(new ServiceException("500", "Failed to fetch bytes: " + e.getMessage()));
//                });
//    }

    /**
     * 发送带Range的GET请求
     */
    protected Mono<ResponseEntity<String>> fetchWithRange(String url, String range) {
        return downloadClient.downloadWithRange(url, headers, range);
    }

    /**
     * 发送GET请求并获取JSON响应
     */
//    protected Mono<ResponseEntity<String>> fetchGetJson(String url) {
//        return Mono.fromCallable(() -> crawlerClient.getJson(url, headers, proxies));
//    }

    /**
     * 处理HTTP错误
     */
//    protected void handleHttpError(int statusCode, String url) {
//        String code = String.valueOf(statusCode);
//        String message = switch (statusCode) {
//            case 401 -> "Unauthorized access";
//            case 403 -> "Forbidden access";
//            case 404 -> "Resource not found";
//            case 429 -> "Too many requests";
//            case 500 -> "Server error";
//            default -> "HTTP error";
//        };
//        throw new ServiceException(code, message + ": " + url);
//    }
} 