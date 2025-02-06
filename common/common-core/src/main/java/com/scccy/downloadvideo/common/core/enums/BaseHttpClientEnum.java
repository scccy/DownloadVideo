package com.scccy.downloadvideo.common.core.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum BaseHttpClientEnum {
    ENCRYPTION("ab"),
    
    VERSION_CODE("290100"),
    VERSION_NAME("29.1.0"),
    
    BROWSER_LANGUAGE("zh-CN"),
    BROWSER_PLATFORM("Win32"),
    BROWSER_NAME("Edge"),
    BROWSER_VERSION("130.0.0.0"),
    
    ENGINE_NAME("Blink"),
    ENGINE_VERSION("130.0.0.0"),
    
    OS_NAME("Windows"),
    OS_VERSION("10"),
    
    USER_AGENT("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0"),
    REFERER("https://www.douyin.com/"),
    
    WSS_DOMAIN("localhost"),
    WSS_PORT("8765"),
    WSS_VERIFY("false");
    
    private final String value;
}
