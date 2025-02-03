package com.scccy.downloadvideo.common.core.aspect;

import com.scccy.downloadvideo.common.core.annotation.ApiOperationDouyin;
import com.scccy.downloadvideo.common.core.utils.manager.XBogusManager;
import com.scccy.downloadvideo.common.core.context.ApiContext;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ApiOperationAspect {

    @Around("@annotation(apiOperationDouyin)")
    public Object around(ProceedingJoinPoint point, ApiOperationDouyin apiOperationDouyin) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String methodName = method.getName();
        
        try {
            // 获取方法参数
            Object[] args = point.getArgs();
            Map<String, String> params = buildParams(method, args);
            
            // 获取DouyinCrawler实例
            Object target = point.getTarget();
            String baseUrl = (String) target.getClass().getDeclaredField("apiBaseUrl").get(target);
            XBogusManager bogusManager = (XBogusManager) target.getClass().getDeclaredField("bogusManager").get(target);
            Map<String, String> headers = (Map<String, String>) target.getClass().getDeclaredField("headers").get(target);
            
            // 构建endpoint
            String endpoint = baseUrl + apiOperationDouyin.endpoint();
            if (apiOperationDouyin.needXBogus()) {
                endpoint = bogusManager.generateEndpoint(endpoint, params, headers.get("User-Agent"));
            }
            
            log.debug("{} 接口地址: {}", apiOperationDouyin.value(), endpoint);
            
            // 设置endpoint到上下文
            ApiContext.setCurrentEndpoint(endpoint);
            
            // 执行原方法
            return point.proceed();
            
        } catch (Exception e) {
            log.error("Failed to execute {} with error: ", methodName, e);
            throw new ServiceException(String.format("Failed to execute %s: %s", methodName, e.getMessage()));
        } finally {
            ApiContext.clear();
        }
    }
    
    private Map<String, String> buildParams(Method method, Object[] args) throws NoSuchMethodException {
        Map<String, String> params = new HashMap<>();
        String[] paramNames = ((MethodSignature) method.getDeclaringClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(org.aspectj.lang.annotation.Pointcut.class)).getParameterNames();
        
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                params.put(paramNames[i], String.valueOf(args[i]));
            }
        }
        
        return params;
    }
} 