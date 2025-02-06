package com.scccy.downloadvideo.common.download.downloader;

import com.scccy.downloadvideo.common.core.model.ResultData;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BaseHttpClient {
    Mono<ResultData> get(String url, Map<String, String> headers, String proxyHost, Integer proxyPort);
    
    Mono<ResultData> post(String url, Object body, Map<String, String> headers, String proxyHost, Integer proxyPort);
} 