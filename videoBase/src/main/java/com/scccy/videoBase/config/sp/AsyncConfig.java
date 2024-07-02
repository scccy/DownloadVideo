package com.scccy.videoBase.config.sp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

//    接口使用
    @Bean(name = "generalTaskExecutor")
    public ThreadPoolTaskExecutor generalTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
// 下载使用
    @Bean(name = "feignTaskExecutor")
    public ThreadPoolTaskExecutor feignTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("Feign-");
        executor.initialize();
        return executor;
    }
}