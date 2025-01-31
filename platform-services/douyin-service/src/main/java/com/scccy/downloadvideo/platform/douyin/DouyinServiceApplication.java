package com.scccy.downloadvideo.platform.douyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@MapperScan("com.scccy.downloadvideo.platform.**.mapper") // 启用 MyBatis 的 Mapper 扫描
@EnableDiscoveryClient
@EnableFeignClients
public class DouyinServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DouyinServiceApplication.class, args);
    }
}