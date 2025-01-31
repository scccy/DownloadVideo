package com.scccy.downloadvideo.common.core.context;

public class ApiContext {
    private static final ThreadLocal<String> currentEndpoint = new ThreadLocal<>();
    
    public static void setCurrentEndpoint(String endpoint) {
        currentEndpoint.set(endpoint);
    }
    
    public static String getCurrentEndpoint() {
        return currentEndpoint.get();
    }
    
    public static void clear() {
        currentEndpoint.remove();
    }
} 