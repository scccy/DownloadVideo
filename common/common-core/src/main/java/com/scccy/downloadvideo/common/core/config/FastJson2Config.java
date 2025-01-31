package com.scccy.downloadvideo.common.core.config;

import com.alibaba.fastjson2.support.spring6.data.reactive.FastJsonHttpMessageWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class FastJson2Config implements WebFluxConfigurer {

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        FastJsonHttpMessageWriter writer = new FastJsonHttpMessageWriter();
        // 配置FastJson
        writer.setFastJsonConfig(new com.alibaba.fastjson2.support.config.FastJsonConfig());
        configurer.defaultCodecs().jackson2JsonEncoder(writer);
    }
} 