package com.scccy.downloadvideo.common.core.config;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.http.CacheControl;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {


//    @Override
//    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//        configurer.registerDefaults(false);
//        //反序列化
//        configurer.customCodecs().register(new FastjsonConfig.CustomDecoder());
//        //序列化
//        configurer.customCodecs().register(new FastjsonConfig.CustomEncoder());
//    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}


