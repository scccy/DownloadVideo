package com.scccy.downloadvideo.platform.douyin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "nodejs")
public class NodeJsProperties {
    private String path = "node"; // 默认从 PATH 环境变量中查找
}