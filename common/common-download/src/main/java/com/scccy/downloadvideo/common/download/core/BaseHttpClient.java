package com.scccy.downloadvideo.common.download.core;

import com.alibaba.fastjson2.JSON;
import com.scccy.downloadvideo.common.core.enums.ErrorCodeEnum;
import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.common.core.utils.MonoUtils;
import com.scccy.downloadvideo.common.core.exception.CustomExceptions;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class BaseHttpClient {

    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient DEFAULT_CLIENT = createDefaultClient();

    /**
     * 异步发送GET请求
     */
    public Mono<Response> getAsync(String url, Map<String, String> headers, String proxyHost, Integer proxyPort) {
        return MonoUtils.fromBlocking(() -> {
            try {
                return executeRequest(url, headers, proxyHost, proxyPort, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 异步发送POST请求
     */
    public Mono<Response> postAsync(String url, Object body, Map<String, String> headers, String proxyHost, Integer proxyPort) {
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(body), JSON_TYPE);
        return MonoUtils.fromBlocking(() -> {
            try {
                return executeRequest(url, headers, proxyHost, proxyPort, requestBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 执行HTTP请求
     */
    private Response executeRequest(String url, Map<String, String> headers, String proxyHost, Integer proxyPort, RequestBody body) throws IOException {
        OkHttpClient client = (proxyHost != null && proxyPort != null) ?
                createProxyClient(proxyHost, proxyPort) : DEFAULT_CLIENT;

        Request.Builder requestBuilder = new Request.Builder().url(url);

        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }

        if (body != null) {
            requestBuilder.post(body);
        }

        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            if (!response.isSuccessful()) {
                throw new CustomExceptions.CustomException("请求失败，状态码: " + response.code());
            }
            return response;
        }
    }

    /**
     * 下载文件到指定路径
     */
    public Mono<Boolean> downloadFile(String url, String savePath, Map<String, String> headers, String proxyHost, Integer proxyPort) {
        return MonoUtils.fromBlocking(() -> {
            try (Response response = executeRequest(url, headers, proxyHost, proxyPort, null)) {
                try (ResponseBody body = response.body()) {
                    if (body == null) {
                        throw new CustomExceptions.CustomException("响应体为空");
                    }
                    Path targetPath = Paths.get(savePath);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(body.byteStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    return true;
                }
            } catch (IOException e) {
                throw new CustomExceptions.CustomException("文件下载失败: " + e.getMessage());
            }
        });
    }

    /**
     * 创建默认的OkHttpClient
     */
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

    /**
     * 创建带代理的OkHttpClient
     */
    private static OkHttpClient createProxyClient(String proxyHost, int proxyPort) {
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

    /**
     * 提取响应数据
     */
    private static ResultData getResponseData(Response response) {
        try {
            ResultData resultData = new ResultData();
            resultData.setCode(response.code());
            resultData.setData(response.body().string());
            resultData.setMsg(response.headers().toMultimap().entrySet().stream()
                    .collect(HashMap::new,
                            (map, entry) -> map.put(entry.getKey(), String.join("; ", entry.getValue())),
                            HashMap::putAll).toString());

            if (!response.isSuccessful()) {
                resultData.setMsg("HTTP请求失败，状态码: " + response.code());
            }

            return resultData;

        } catch (Exception e) {
            log.error("响应处理失败: {}", e.getMessage(), e);
            ResultData resultData = new ResultData();
            resultData.setCode(Integer.valueOf(ErrorCodeEnum.PARSE_ERROR.getCode()));
            resultData.setMsg(ErrorCodeEnum.PARSE_ERROR.getMessage());
            return resultData;
        }
    }
}