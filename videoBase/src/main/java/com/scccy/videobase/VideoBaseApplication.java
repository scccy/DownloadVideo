package com.scccy.videobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VideoBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoBaseApplication.class, args);
    }
} 