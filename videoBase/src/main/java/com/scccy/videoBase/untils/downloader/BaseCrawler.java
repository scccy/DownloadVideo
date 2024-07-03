package com.scccy.videoBase.untils.downloader;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.scccy.videoBase.domain.ConfigAppConfig;
import com.scccy.videoBase.domain.ConfigBase;
import com.scccy.videoBase.handlerExption.CustomExceptions;
import com.scccy.videoBase.service.ConfigAppConfigService;
import com.scccy.videoBase.service.ConfigBaseService;
import com.scccy.videoBase.untils.Untils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.scccy.videoBase.handlerExption.CustomExceptions.handleHttpStatusError;

@Slf4j
public class BaseCrawler {

    private OkHttpClient client;
    private int maxRetries;
    private int timeout;
    private ConfigBaseService configBaseService;
    private ConfigAppConfigService configAppConfigService;

    public BaseCrawler(ConfigBaseService configBaseService, Long baseId, ConfigAppConfigService configAppConfigService, Long appId) {
        this.configBaseService = configBaseService;
        this.configAppConfigService = configAppConfigService;

        ConfigBase configBase = configBaseService.getById(baseId);
        ConfigAppConfig configAppConfig = configAppConfigService.getById(appId);

        this.maxRetries = configBase.getMaxRetries();
        this.timeout = configBase.getTimeout();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(configBase.getMaxConnections(), 5, TimeUnit.MINUTES));

        // 设置代理
        configureProxy(builder, configAppConfig);

        this.client = builder.build();
    }

    private void configureProxy(OkHttpClient.Builder builder, ConfigAppConfig configAppConfig) {
        String proxiesHttp = configAppConfig.getProxiesHttp();
        String proxiesHttps = configAppConfig.getProxiesHttps();

        if (proxiesHttps != null && !proxiesHttps.isEmpty()) {
            setProxy(builder, proxiesHttps, "HTTPS");
        } else if (proxiesHttp != null && !proxiesHttp.isEmpty()) {
            setProxy(builder, proxiesHttp, "HTTP");
        }
    }

    private void setProxy(OkHttpClient.Builder builder, String proxyUrl, String proxyType) {
        Map<String, String> proxyUrlAndPort = Untils.getProxyUrlAndPort(proxyUrl);
        String proxyHost = proxyUrlAndPort.get("url");
        int proxyPort = Integer.parseInt(proxyUrlAndPort.get("port"));

        if (proxyHost != null && proxyPort > 0) {
            builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
            log.info("{} Proxy set to {}:{}", proxyType, proxyHost, proxyPort);
        }
    }

    @Async
    public <T> T getFetchData(String url, Class<T> responseClass) throws CustomExceptions.CustomException {
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    return parseResponse(response, responseClass);
                } else {
                    handleHttpStatusError(response, attempt);
                }
            } catch (IOException e) {
                log.error("在 GET 请求期间发生 IOException", e);
                handleIOException(attempt, e);
            }
        }
        throw new CustomExceptions.APIRetryExhaustedException("GET 请求的重试次数已用尽");
    }

    @Async
    public <T> T postFetchData(String url, Map<String, String> headers, Object requestBody, Class<T> responseClass) throws CustomExceptions.CustomException {
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                RequestBody body = RequestBody.create(JSON.toJSONString(requestBody), MediaType.parse("application/json; charset=utf-8"));
                Request request = buildRequest(url, body, headers);
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    return parseResponse(response, responseClass);
                } else {
                    handleHttpStatusError(response, attempt);
                }
            } catch (IOException e) {
                log.error("在 POST 请求期间发生 IOException", e);
                handleIOException(attempt, e);
            }
        }
        throw new CustomExceptions.APIRetryExhaustedException("POST 请求的重试次数已用尽");
    }

    public static Request buildRequest(String url, RequestBody body, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.addHeader(header.getKey(), header.getValue());
            }
        }
        return requestBuilder.build();
    }

    private <T> T parseResponse(Response response, Class<T> responseClass) throws IOException {
        String responseBody = response.body().string();
        log.info("Response received: {}", responseBody);
        return JSON.parseObject(responseBody, responseClass,
                JSONReader.Feature.FieldBased,
                JSONReader.Feature.SupportArrayToBean,
                JSONReader.Feature.SupportSmartMatch);
    }

    private void handleIOException(int attempt, IOException e) throws CustomExceptions.APIConnectionException {
        if (attempt == maxRetries - 1) {
            throw new CustomExceptions.APIConnectionException("连接到端点失败，重试次数：" + maxRetries, e);
        }
        try {
            Thread.sleep(timeout * 1000L);
        } catch (InterruptedException interruptedException) {
            throw new CustomExceptions.APIConnectionException("在重试等待期间被中断", interruptedException);
        }
    }
}
