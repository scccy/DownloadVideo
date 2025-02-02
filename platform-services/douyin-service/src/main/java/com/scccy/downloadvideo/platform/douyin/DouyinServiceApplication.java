package com.scccy.downloadvideo.platform.douyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.scccy.downloadvideo.common.download.feign"})
@MapperScan("com.scccy.downloadvideo.**.mapper")
public class DouyinServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DouyinServiceApplication.class, args);
    }
}