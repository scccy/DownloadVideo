package com.scccy.downloadvideo.common.download.downloader;

import com.alibaba.fastjson2.JSON;
import com.scccy.downloadvideo.common.core.enums.ErrorCodeEnum;
import com.scccy.downloadvideo.common.core.model.ResultData;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BaseHttpClientImpl implements BaseHttpClient {

    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient DEFAULT_CLIENT = createDefaultClient();

    @Override
    public Mono<ResultData> get(String url, Map<String, String> headers, String proxyHost, Integer proxyPort) {
        return Mono.fromCallable(() -> {
            OkHttpClient client = (proxyHost != null && proxyHost != null) ?
                    createProxyClient(proxyHost, proxyPort) : DEFAULT_CLIENT;
            Request.Builder requestBuilder = new Request.Builder().url(url);
            
            if (headers != null) {
                headers.forEach(requestBuilder::addHeader);
            }
            
            try (Response response = client.newCall(requestBuilder.build()).execute()) {
                return getResponseUrl(response);
            } catch (Exception e) {
                log.error("GET request failed: {}", e.getMessage(), e);
                return ResultData.fail(ErrorCodeEnum.SYSTEM_ERROR.getCode(),
                        ErrorCodeEnum.SYSTEM_ERROR.getMessage());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ResultData> post(String url, Object body, Map<String, String> headers, String proxyHost, Integer proxyPort) {
        return Mono.fromCallable(() -> {
            OkHttpClient client = (proxyHost != null && !proxyHost.isEmpty()) ? 
                    createProxyClient(proxyHost, proxyPort) : DEFAULT_CLIENT;
                    
            RequestBody requestBody = RequestBody.create(
                    JSON.toJSONString(body), JSON_TYPE
            );
            
            Request.Builder requestBuilder = new Request.Builder()
                    .url(url)
                    .post(requestBody);
            
            if (headers != null) {
                headers.forEach(requestBuilder::addHeader);
            }
            
            try (Response response = client.newCall(requestBuilder.build()).execute()) {
                return getResponseUrl(response);
            } catch (Exception e) {
                log.error("POST request failed: {}", e.getMessage(), e);
                return ResultData.fail(ErrorCodeEnum.SYSTEM_ERROR.getCode(), 
                        ErrorCodeEnum.SYSTEM_ERROR.getMessage());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private static OkHttpClient createDefaultClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(true)
                .followSslRedirects(true)
                .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
                .build();
    }

    private OkHttpClient createProxyClient(String proxyHost, int proxyPort) {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(true)
                .followSslRedirects(true)
                .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .build();
    }

    private ResultData getResponseUrl(Response response) {
        try {
            if (!response.isSuccessful()) {
                return ResultData.fail(
                        String.valueOf(response.code()),
                        "HTTP request failed with code: " + response.code()
                );
            }

            HttpUrl url = response.request().url();
            return ResultData.ok(url.url());
        } catch (Exception e) {
            log.error("Failed to handle response: {}", e.getMessage(), e);
            return ResultData.fail(
                    ErrorCodeEnum.PARSE_ERROR.getCode(),
                    ErrorCodeEnum.PARSE_ERROR.getMessage()
            );
        }
    }
} 