package com.scccy.downloadvideo.common.core.aspect;

import com.scccy.downloadvideo.common.core.config.manager.XBogusManager;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class XBogusAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object addXBogus(ProceedingJoinPoint point) throws Throwable {
        // 获取请求参数
        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String[] paramNames = signature.getParameterNames();
        
        // 构建参数Map
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                params.put(paramNames[i], String.valueOf(args[i]));
            }
        }
        
        // 获取请求URL
        String url = signature.getMethod().getAnnotation(RequestMapping.class).value()[0];
        
        // 生成X-Bogus
        XBogusManager bogusManager = new XBogusManager();
        String xbogus = bogusManager.generateEndpoint(url, params, "");
        
        // 使用OkHttp设置X-Bogus头
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request requestWithXbogus = originalRequest.newBuilder()
                        .header("X-Bogus", xbogus)
                        .build();
                    return chain.proceed(requestWithXbogus);
                }
            })
            .build();
        
        return point.proceed();
    }
}