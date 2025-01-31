package com.scccy.downloadvideo.common.core.aspect;

import com.scccy.downloadvideo.common.core.annotation.WebSocketApi;


import com.scccy.downloadvideo.common.core.enums.WebSocketApiEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class WebSocketAspect extends BaseAspect {

    @Around("@annotation(webSocketApi)")
    public Object around(ProceedingJoinPoint point, WebSocketApi webSocketApi) throws Throwable {
        try {
            // 获取API端点和参数
            WebSocketApiEndpoint endpoint = webSocketApi.value();
            Map<String, String> params = buildParams(
                ((MethodSignature) point.getSignature()).getParameterNames(), 
                point.getArgs()
            );
            
            // 处理认证
            if (webSocketApi.needAuth()) {
                addAuthHeaders(params);
            }
            
            // 执行请求
            return point.proceed();
            
        } catch (Exception e) {
            return handleException("WebSocket request", e);
        }
    }
    
    private void addAuthHeaders(Map<String, String> params) {
        // TODO: 添加认证信息
    }
} 