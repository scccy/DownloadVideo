package com.scccy.downloadvideo.common.core.config;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Feign;


@Configuration
@Slf4j
public class FeignConfig {

    private final OkHttpClient okHttpClient;

    // 通过构造方法注入 OkHttpClient，复用 OkHttpConfig 里的 Bean
    public FeignConfig(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    // 检查是否有其他 FactoryBean 相关的配置
}
