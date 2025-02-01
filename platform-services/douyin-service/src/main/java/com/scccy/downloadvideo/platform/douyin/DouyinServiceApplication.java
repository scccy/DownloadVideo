package com.scccy.downloadvideo.platform.douyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.scccy.downloadvideo.common.download.feign"})
@MapperScan("com.scccy.downloadvideo.platform.douyin.mapper")
public class DouyinServiceApplication {


    public static void main(String[] args) {
        // 开启调试模式

        SpringApplication app = new SpringApplication(DouyinServiceApplication.class);
       app.run();

    }

//    /**
//     * 打印所有加载的 Bean
//     */
//    private static void printAllBeans(ApplicationContext ctx) {
//        logger.info("========== 加载的 Bean 列表 ==========");
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            logger.info(beanName);
//        }
//        logger.info("========== Bean 列表结束 ==========");
//    }
//
//    /**
//     * 打印环境变量
//     */
//    @Bean
//    public String printEnvironment(Environment env) {
//        logger.info("========== 环境变量 ==========");
//        logger.info("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
//        logger.info("Default profiles: " + Arrays.toString(env.getDefaultProfiles()));
//        logger.info("========== 环境变量结束 ==========");
//        return "Environment printed";
//    }
}