//package com.scccy.downloadvideo.common.core.aspect;
//
//import com.google.protobuf.ServiceException;
//import com.scccy.downloadvideo.common.core.annotation.ApiOperationDouyin;
//import com.scccy.downloadvideo.common.core.utils.manager.XBogusManager;
//import com.scccy.downloadvideo.common.core.context.ApiContext;
//import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.util.Map;
//
//@Slf4j
//@Aspect
//@Component
//public class ApiOperationAspect {
//
//    @Around("@annotation(apiOperationDouyin)")
//    public Object around(ProceedingJoinPoint point, ApiOperationDouyin apiOperationDouyin) throws Throwable {
//        Method method = ((MethodSignature) point.getSignature()).getMethod();
//        String methodName = method.getName();
//
//        try {
//            // 获取方法参数
//            Object[] args = point.getArgs();
//            BaseRequestModel config = findRequestConfig(method, args);
//
//            if (config == null) {
//                throw new ServiceException("No BaseRequestModel parameter found in method: " + methodName);
//            }
//
//            // 获取DouyinCrawler实例
//            Object target = point.getTarget();
//            String baseUrl = (String) target.getClass().getDeclaredField("apiBaseUrl").get(target);
//            XBogusManager bogusManager = (XBogusManager) target.getClass().getDeclaredField("bogusManager").get(target);
//            Map<String, String> headers = (Map<String, String>) target.getClass().getDeclaredField("headers").get(target);
//
//            // 合并headers到config
//            if (headers != null) {
//                config.getHeaders().putAll(headers);
//            }
//
//            // 构建endpoint
//            String endpoint = baseUrl + apiOperationDouyin.endpoint();
//            if (apiOperationDouyin.needXBogus()) {
//                BaseRequestModel params = config;
//                endpoint = bogusManager.generateEndpoint(endpoint, params, headers.get("User-Agent"));
//            }
//
//            log.debug("{} 接口地址: {}", apiOperationDouyin.value(), endpoint);
//
//            // 设置endpoint到上下文
//            ApiContext.setCurrentEndpoint(endpoint);
//
//            // 执行原方法
//            return point.proceed();
//
//        } catch (Exception e) {
//            log.error("Failed to execute {} with error: ", methodName, e);
//            throw new ServiceException(String.format("Failed to execute %s: %s", methodName, e.getMessage()));
//        } finally {
//            ApiContext.clear();
//        }
//    }
//
//    /**
//     * 从方法参数中查找BaseRequestModel类型的参数
//     */
//    private BaseRequestModel findRequestConfig(Method method, Object[] args) {
//        Parameter[] parameters = method.getParameters();
//        for (int i = 0; i < parameters.length; i++) {
//            if (BaseRequestModel.class.isAssignableFrom(parameters[i].getType())) {
//                return (BaseRequestModel) args[i];
//            }
//        }
//        return null;
//    }
//}