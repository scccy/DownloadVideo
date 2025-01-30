package com.scccy.downloadvideo.common.core.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    
    SYSTEM_ERROR("500", "系统异常"),
    PARAM_ERROR("400", "参数错误"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "资源不存在"),
    METHOD_NOT_ALLOWED("405", "请求方法不允许"),
    CONFLICT("409", "资源冲突"),
    TOO_MANY_REQUESTS("429", "请求过于频繁"),
    
    // 业务错误码
    VIDEO_NOT_FOUND("1001", "视频不存在"),
    DOWNLOAD_FAILED("1002", "下载失败"),
    STORAGE_ERROR("1003", "存储异常"),
    PARSE_ERROR("1004", "解析失败");

    private final String code;
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
} 