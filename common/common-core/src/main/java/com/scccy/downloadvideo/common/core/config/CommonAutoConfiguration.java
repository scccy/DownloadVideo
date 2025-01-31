package com.scccy.downloadvideo.common.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scccy.downloadvideo.common.core.config.manager.XBogusManager;
import com.scccy.downloadvideo.common.core.config.properties.DownloadProperties;
import com.scccy.downloadvideo.common.core.config.properties.WebSocketProperties;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;

@Configuration
@EnableConfigurationProperties({
    DownloadProperties.class,
    WebSocketProperties.class
})
@ComponentScan(basePackages = "com.scccy.downloadvideo.common.core")
public class CommonAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public XBogusManager xBogusManager() {
        return new XBogusManager();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    public static ConfigurationPropertiesBindingPostProcessor configurationPropertiesBindingPostProcessor() {
        return new ConfigurationPropertiesBindingPostProcessor();
    }

} 