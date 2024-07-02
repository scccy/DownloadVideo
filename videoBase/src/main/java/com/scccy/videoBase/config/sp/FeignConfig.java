package com.scccy.videoBase.config.sp;

import feign.Client;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Client feignClient(OkHttpClient okHttpFeignClient) {
        return new feign.okhttp.OkHttpClient(okHttpFeignClient);
    }
}
