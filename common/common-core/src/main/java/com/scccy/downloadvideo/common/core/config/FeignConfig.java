package com.scccy.downloadvideo.common.core.config;

import feign.Logger;
import feign.Retryer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    private final OkHttpClient okHttpClient;

    // 通过构造方法注入 OkHttpClient，复用 OkHttpConfig 里的 Bean
    public FeignConfig(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(
                10, TimeUnit.SECONDS,  // 连接超时
                60, TimeUnit.SECONDS,  // 读取超时
                true                   // 跟随重定向
        );
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // 开启 Feign 完整日志
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 3); // 重试策略
    }
}