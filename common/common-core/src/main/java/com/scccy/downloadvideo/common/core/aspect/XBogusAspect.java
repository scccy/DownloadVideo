// package com.scccy.downloadvideo.common.core.aspect;

// import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;
// import com.scccy.downloadvideo.common.core.utils.manager.XBogusManager;
// import lombok.extern.slf4j.Slf4j;
// import okhttp3.Interceptor;
// import okhttp3.OkHttpClient;
// import okhttp3.Request;
// import okhttp3.Response;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import java.io.IOException;
// import java.lang.reflect.Method;
// import java.util.HashMap;
// import java.util.Map;

// @Slf4j
// @Aspect
// @Component
// public class XBogusAspect {

//     @Around("execution(* com.scccy.downloadvideo.common.download.feign.DownloadFeignClient.*(..))")
//     public Object addXBogus(ProceedingJoinPoint point) throws Throwable {
//         MethodSignature signature = (MethodSignature) point.getSignature();
//         String methodName = signature.getMethod().getName();
//         String url = getRequestUrl(signature.getMethod());
        
//         // 获取请求参数
//         Object[] args = point.getArgs();
//         String[] paramNames = signature.getParameterNames();

//         // 构建参数Map
//         BaseRequestModel params = new BaseRequestModel();
//         Map<String, String> headers = new HashMap<>();
        
//         // 处理参数和请求头
//         for (int i = 0; i < args.length; i++) {
//             if (args[i] != null) {
//                 if ("headers".equals(paramNames[i])) {
//                     headers.putAll((Map<String, String>) args[i]);
//                 } else {
//                     params.toRequestParams().put(paramNames[i], String.valueOf(args[i]));
//                 }
//             }
//         }

//         // 生成X-Bogus
//         XBogusManager bogusManager = new XBogusManager();
//         String xbogus = bogusManager.generateEndpoint(url, params, "");
        
//         // 将X-Bogus添加到请求头中
//         if (args.length > 1 && args[1] instanceof Map) {
//             ((Map<String, String>) args[1]).put("X-Bogus", xbogus);
//         }
        
//         log.debug("Method: {}, URL: {}, X-Bogus: {}", methodName, url, xbogus);
        
//         return point.proceed(args);
//     }
    
//     private String getRequestUrl(Method method) {
//         // 获取不同类型的注解
//         if (method.isAnnotationPresent(GetMapping.class)) {
//             GetMapping mapping = method.getAnnotation(GetMapping.class);
//             return mapping.value().length > 0 ? mapping.value()[0] : "";
//         }
        
//         if (method.isAnnotationPresent(PostMapping.class)) {
//             PostMapping mapping = method.getAnnotation(PostMapping.class);
//             return mapping.value().length > 0 ? mapping.value()[0] : "";
//         }
        
//         if (method.isAnnotationPresent(RequestMapping.class)) {
//             RequestMapping mapping = method.getAnnotation(RequestMapping.class);
//             return mapping.value().length > 0 ? mapping.value()[0] : "";
//         }
        
//         return "";
//     }
// }