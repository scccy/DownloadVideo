package com.scccy.downloadvideo.common.core.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Log4j2Config {
    
    @PostConstruct
    public void initLog4j2Async() {
        // 设置全局异步日志
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        
        // 设置异步日志的一些参数
        System.setProperty("AsyncLogger.RingBufferSize", "262144"); // 默认为256*1024
        System.setProperty("AsyncLoggerConfig.RingBufferSize", "262144");
        
        // 设置等待策略为休眠等待（可以降低CPU使用率）
        System.setProperty("AsyncLogger.WaitStrategy", "Sleep");
        System.setProperty("AsyncLoggerConfig.WaitStrategy", "Sleep");
        
        // 设置当队列满时的处理策略（默认丢弃）
        System.setProperty("log4j2.AsyncQueueFullPolicy", "Discard");
        
        // 设置异步日志的超时时间（毫秒）
        System.setProperty("AsyncLogger.Timeout", "1000");
        System.setProperty("AsyncLoggerConfig.Timeout", "1000");
    }
} 