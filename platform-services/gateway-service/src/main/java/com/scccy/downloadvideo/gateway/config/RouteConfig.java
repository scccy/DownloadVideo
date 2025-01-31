package com.scccy.downloadvideo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Web服务路由
            .route("web-service", r -> r.path("/api/web/**")
                .filters(f -> f.stripPrefix(2))
                .uri("lb://web-service"))
            // 抖音服务路由
            .route("douyin-service", r -> r.path("/api/douyin/**")
                .filters(f -> f.stripPrefix(2))
                .uri("lb://douyin-service"))
            .build();
    }
} 