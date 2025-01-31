package com.scccy.downloadvideo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
    exclude = {
        org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration.class
    }
)
@EnableFeignClients(basePackages = "com.scccy.downloadvideo")
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
} 