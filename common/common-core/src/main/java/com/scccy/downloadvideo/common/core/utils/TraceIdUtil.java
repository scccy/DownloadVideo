package com.scccy.downloadvideo.common.core.utils;

import org.slf4j.MDC;
import java.util.UUID;

public class TraceIdUtil {
    private static final String TRACE_ID = "traceId";
    
    public static String generateTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }
    
    public static String getTraceId() {
        String traceId = MDC.get(TRACE_ID);
        if (traceId == null) {
            traceId = generateTraceId();
            setTraceId(traceId);
        }
        return traceId;
    }
    
    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
    }
} 