package com.scccy.downloadvideo.platform.douyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.scccy.downloadvideo")
@MapperScan("com.scccy.downloadvideo.platform.douyin.mapper")
public class DouyinServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DouyinServiceApplication.class, args);
    }
} 