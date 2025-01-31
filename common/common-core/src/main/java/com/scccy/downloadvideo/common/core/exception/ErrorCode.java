package com.scccy.downloadvideo.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUCCESS("0", "成功"),
    SYSTEM_ERROR("500", "系统错误"),
    PARAM_ERROR("400", "参数错误"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "资源不存在"),
    API_ERROR("1001", "接口调用失败"),
    DOWNLOAD_ERROR("1002", "下载失败"),
    WEBSOCKET_ERROR("1003", "WebSocket连接失败");
    
    private final String code;
    private final String message;
} 