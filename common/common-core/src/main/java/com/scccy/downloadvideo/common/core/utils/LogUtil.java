package com.scccy.downloadvideo.common.core.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {
    
    public static void info(String format, Object... arguments) {
        log.info(appendTraceId(format), arguments);
    }
    
    public static void error(String format, Object... arguments) {
        log.error(appendTraceId(format), arguments);
    }
    
    public static void error(String message, Throwable throwable) {
        log.error(appendTraceId(message), throwable);
    }
    
    public static void debug(String format, Object... arguments) {
        log.debug(appendTraceId(format), arguments);
    }
    
    public static void warn(String format, Object... arguments) {
        log.warn(appendTraceId(format), arguments);
    }
    
    private static String appendTraceId(String message) {
        return String.format("[TraceId:%s] %s", TraceIdUtil.getTraceId(), message);
    }
} 