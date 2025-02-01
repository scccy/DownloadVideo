package com.scccy.downloadvideo.platform.douyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication  // 修改扫描范围
@EnableDiscoveryClient
@MapperScan("com.scccy.downloadvideo.platform.douyin.mapper")  // 精确指定 Mapper 扫描路径
@EnableFeignClients(basePackages = {"com.scccy.downloadvideo.common.download.feign"})  // 添加这个配置
public class DouyinServiceApplication {
    
    public static void main(String[] args) {
        // 开启调试模式
        System.setProperty("debug", "true");
        SpringApplication app = new SpringApplication(DouyinServiceApplication.class);
        app.setAddCommandLineProperties(true);
        app.run(args);
    }
}