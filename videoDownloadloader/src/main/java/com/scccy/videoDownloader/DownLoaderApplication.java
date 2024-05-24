package com.scccy.videoDownloader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync


public class DownLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownLoaderApplication.class, args);
    }
//操你妈
}
