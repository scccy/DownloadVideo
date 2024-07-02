package com.scccy.videoBase.config.sp;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpFeignConfig {

    @Bean
    public okhttp3.OkHttpClient okHttpFeignClient() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(100);
        dispatcher.setMaxRequestsPerHost(20);

        ConnectionPool connectionPool = new ConnectionPool(50, 5, TimeUnit.MINUTES);

        return new Builder()
                .dispatcher(dispatcher)
                .connectionPool(connectionPool)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }
}
