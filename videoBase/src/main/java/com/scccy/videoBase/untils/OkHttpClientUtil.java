package com.scccy.videoBase.untils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class OkHttpClientUtil {

    // 创建并配置 OkHttpClient 实例，设置连接超时、读取超时和写入超时
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    /**
     * 发送 POST 请求
     * @param url 请求的 URL
     * @param requestBody 请求体对象
     * @param responseClass 响应体的类型
     * @param <T> 响应体类型的泛型
     * @return 响应体对象
     * @throws IOException 如果请求失败抛出异常
     */
    @Async
    public <T> T post(String url, Object requestBody, Class<T> responseClass) throws IOException {
        // 将请求体对象转换为 JSON 格式的 RequestBody
        RequestBody body = RequestBody.create(JSON.toJSONString(requestBody), MediaType.parse("application/json; charset=utf-8"));
        // 构建 POST 请求
        Request request = buildRequest(url, body, null);
        // 执行请求并返回响应结果
        return executeRequest(request, responseClass);
    }

    /**
     * 发送带有请求头的 POST 请求
     * @param url 请求的 URL
     * @param headers 请求头
     * @param requestBody 请求体对象
     * @param responseClass 响应体的类型
     * @param <T> 响应体类型的泛型
     * @return 响应体对象
     * @throws IOException 如果请求失败抛出异常
     */
    @Async
    public  <T> T post(String url, Map<String, String> headers, Object requestBody, Class<T> responseClass) throws IOException {
        // 将请求体对象转换为 JSON 格式的 RequestBody
        RequestBody body = RequestBody.create(JSON.toJSONString(requestBody), MediaType.parse("application/json; charset=utf-8"));
        // 构建带有请求头的 POST 请求
        Request request = buildRequest(url, body, headers);
        // 执行请求并返回响应结果
        return executeRequest(request, responseClass);
    }

    /**
     * 构建 POST 请求
     * @param url 请求的 URL
     * @param body 请求体
     * @param headers 请求头，可为空
     * @return 构建好的 POST 请求
     */
    private static Request buildRequest(String url, RequestBody body, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);

        // 如果 headers 不为空，则添加请求头
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.addHeader(header.getKey(), header.getValue());
            }
        }

        return requestBuilder.build();
    }

    /**
     * 发送 GET 请求
     * @param url 请求的 URL
     * @param responseClass 响应体的类型
     * @param <T> 响应体类型的泛型
     * @return 响应体对象
     * @throws IOException 如果请求失败抛出异常
     */
    @Async
    public  <T> T get(String url, Class<T> responseClass) throws IOException {
        // 构建 GET 请求
        Request request = new Request.Builder().url(url).build();
        // 执行请求并返回响应结果
        return executeRequest(request, responseClass);
    }

    /**
     * 执行 HTTP 请求并处理响应
     * @param request 构建好的请求
     * @param responseClass 响应体的类型
     * @param <T> 响应体类型的泛型
     * @return 响应体对象
     * @throws IOException 如果请求失败抛出异常
     */
    public static <T> T executeRequest(Request request, Class<T> responseClass) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            // 检查响应是否成功
            if (!response.isSuccessful()) {
                log.error("Unexpected code: {}", response);
                throw new IOException("Unexpected code " + response);
            }
            // 获取响应体字符串
            String responseBody = response.body().string();
            log.info("Response received: {}", responseBody);

            // 将响应体字符串解析为响应类实例并返回
            return JSON.parseObject(responseBody, responseClass,
                    JSONReader.Feature.FieldBased,
                    JSONReader.Feature.SupportArrayToBean,
                    JSONReader.Feature.SupportSmartMatch);
        } catch (IOException e) {
            log.error("Request failed: ", e);
            throw e;
        }
    }
}