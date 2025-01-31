package com.scccy.downloadvideo.common.download.core;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import com.scccy.downloadvideo.common.core.model.ApiResponse;
import com.scccy.downloadvideo.common.download.feign.CrawlerFeignClient;
import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Map;

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
    protected <T> T fetchGet(String url, Class<T> clazz) {
        ResponseEntity<String> response = crawlerClient.get(url, headers, proxies);
        return ApiResponse.parseData(response.getBody(), clazz);
    }

    /**
     * POST请求并解析响应
     */
    protected <T> T fetchPost(String url, Object body, Class<T> clazz) {
        ResponseEntity<String> response = crawlerClient.post(url, 
            JSONObject.toJSONString(body), headers, proxies);
        return ApiResponse.parseData(response.getBody(), clazz);
    }

    /**
     * 获取原始响应
     */
    protected ResponseEntity<String> fetchResponse(String url) {
        return crawlerClient.get(url, headers, proxies);
    }

    /**
     * POST获取原始响应
     */
    protected ResponseEntity<String> fetchPostResponse(String url, Object body) {
        return crawlerClient.post(url, JSONObject.toJSONString(body), headers, proxies);
    }

    /**
     * 发送GET请求并获取二进制响应
     */
    protected ResponseEntity<byte[]> fetchBytes(String url) {
        try {
            return downloadClient.download2Byte(url, headers);
        } catch (Exception e) {
            log.error("Failed to fetch bytes from: {}", url, e);
            throw new ServiceException("500", "Failed to fetch bytes: " + e.getMessage());
        }
    }

    /**
     * 发送带Range的GET请求
     */
    protected ResponseEntity<String> fetchWithRange(String url, String range) {
        try {
            return downloadClient.downloadWithRange(url, headers, range);
        } catch (Exception e) {
            log.error("Failed to fetch with range from: {}", url, e);
            throw new ServiceException("500", "Failed to fetch with range: " + e.getMessage());
        }
    }

    /**
     * 发送GET请求并获取JSON响应
     */
    protected ResponseEntity<String> fetchGetJson(String url) {
        return crawlerClient.getJson(url, headers, proxies);
    }

    /**
     * 处理HTTP错误
     */
    protected void handleHttpError(int statusCode, String url) {
        String code = String.valueOf(statusCode);
        String message = switch (statusCode) {
            case 401 -> "Unauthorized access";
            case 403 -> "Forbidden access";
            case 404 -> "Resource not found";
            case 429 -> "Too many requests";
            case 500 -> "Server error";
            default -> "HTTP error";
        };
        throw new ServiceException(code, message + ": " + url);
    }
} 