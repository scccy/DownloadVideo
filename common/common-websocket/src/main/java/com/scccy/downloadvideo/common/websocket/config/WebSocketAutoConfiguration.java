package com.scccy.downloadvideo.common.websocket.config;

import com.scccy.downloadvideo.common.websocket.listener.DouyinLiveListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public WebSocketClient webSocketClient(WebSocketProperties properties, OkHttpClient okHttpClient) {
        return new WebSocketClient(properties, okHttpClient);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public DouyinLiveListener douyinLiveListener() {
        return new DouyinLiveListener();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public WebSocketService webSocketService(WebSocketClient webSocketClient, DouyinLiveListener liveListener) {
        return new WebSocketService(webSocketClient, liveListener);
    }
} 