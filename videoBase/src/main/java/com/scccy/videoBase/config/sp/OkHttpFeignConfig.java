package com.scccy.videoBase.config.sp;

import feign.Client;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpFeignConfig {

    @Bean
    public Client feignClient() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(100);
        dispatcher.setMaxRequestsPerHost(20);

        ConnectionPool connectionPool = new ConnectionPool(50, 5, TimeUnit.MINUTES);

        okhttp3.OkHttpClient okHttpClient = new Builder()
                .dispatcher(dispatcher)
                .connectionPool(connectionPool)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return new OkHttpClient(okHttpClient);
    }
}
