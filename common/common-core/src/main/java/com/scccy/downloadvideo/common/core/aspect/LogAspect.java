package com.scccy.downloadvideo.common.core.aspect;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    
    @Pointcut("execution(public * com.video.platform..controller..*.*(..))")
    public void webLog() {}

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        // 打印请求信息
        log.info("Request URL: {}", request.getRequestURL().toString());
        log.info("HTTP Method: {}", request.getMethod());
        log.info("Class Method: {}.{}", className, methodName);
        log.info("IP: {}", request.getRemoteAddr());
        log.info("Request Args: {}", Arrays.toString(args));
        
        Object result;
        try {
            result = joinPoint.proceed();
            // 打印响应信息
            log.info("Response Args: {}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("Request Error: ", e);
            throw e;
        } finally {
            log.info("Time Consuming: {} ms", System.currentTimeMillis() - startTime);
        }
        
        return result;
    }
} 