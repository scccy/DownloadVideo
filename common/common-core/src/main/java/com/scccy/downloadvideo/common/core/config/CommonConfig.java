package com.scccy.downloadvideo.common.core.config;

import com.scccy.downloadvideo.common.core.utils.TraceIdUtil;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.StringUtils;
import feign.RequestInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
@Import({
    FastJson2Config.class,
    MybatisPlusConfig.class,
    Knife4jConfig.class,
    FeignConfig.class
})
@EnableFeignClients(basePackages = "com.scccy.downloadvideo")
public class CommonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String traceId = TraceIdUtil.getTraceId();
            if (StringUtils.hasText(traceId)) {
                requestTemplate.header("X-Trace-ID", traceId);
            }
        };
    }
} 