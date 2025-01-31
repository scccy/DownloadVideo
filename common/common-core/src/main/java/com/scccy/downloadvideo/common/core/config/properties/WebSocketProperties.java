package com.scccy.downloadvideo.common.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "websocket")
@Component
public class WebSocketProperties {
    private String baseUrl;
    private Integer connectTimeout = 10000;
    private Integer readTimeout = 30000;
    private Integer writeTimeout = 10000;
    private Integer maxFramePayloadLength = 65536;
    private Boolean enableCompression = true;
} 