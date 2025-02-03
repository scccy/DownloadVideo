package com.scccy.downloadvideo.common.core.config;


import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    // 创建 OkHttpClient 并配置代理
    @Bean
    @Primary
    public OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                .retryOnConnectionFailure(true)  // 失败时自动重试
                .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))  // 连接池
                .connectTimeout(10, TimeUnit.SECONDS)  // 连接超时
                .readTimeout(10, TimeUnit.SECONDS)  // 读超时
                .writeTimeout(10, TimeUnit.SECONDS)  // 写超时
                .build();
    }


}