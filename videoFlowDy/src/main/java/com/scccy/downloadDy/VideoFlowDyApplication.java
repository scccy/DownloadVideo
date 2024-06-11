package com.scccy.downloadDy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableAspectJAutoProxy
@MapperScan("com.scccy.downloadDy.mapper")

public class VideoFlowDyApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoFlowDyApplication.class, args);
    }
//操你妈
}
