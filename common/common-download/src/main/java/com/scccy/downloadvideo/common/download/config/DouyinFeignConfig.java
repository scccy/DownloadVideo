package com.scccy.downloadvideo.common.download.config;

import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class DouyinFeignConfig {

    // 根据代理配置动态创建 OkHttpClient
    private okhttp3.OkHttpClient createOkHttpClientWithProxy(String proxyHost, int proxyPort) {
        return new okhttp3.OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    // 创建 Feign 客户端
    public DownloadFeignClient createFeignClientWithProxy(String proxyHost, int proxyPort) {
        // 创建带代理的 OkHttpClient
        okhttp3.OkHttpClient okHttpClient = createOkHttpClientWithProxy(proxyHost, proxyPort);

        // 获取 FeignClient 注解中的 URL
        String targetUrl = getFeignClientUrl(DownloadFeignClient.class);

        // 创建 Feign 客户端
        return Feign.builder()
                .client(new OkHttpClient(okHttpClient))  // 使用包装后的 feign.Client
                .requestInterceptor(template -> {
                    // 设置额外的请求头
                    template.header("Proxy-Host", proxyHost);
                    template.header("Proxy-Port", String.valueOf(proxyPort));
                })
                .target(DownloadFeignClient.class, targetUrl);
    }

    // 获取 FeignClient 注解中的 URL
    private String getFeignClientUrl(Class<?> feignClientType) {
        FeignClient feignClientAnnotation = feignClientType.getAnnotation(FeignClient.class);
        if (feignClientAnnotation != null && !feignClientAnnotation.url().isEmpty()) {
            return feignClientAnnotation.url();
        }
        return "https://www.douyin.com";  // 默认 URL
    }
}
