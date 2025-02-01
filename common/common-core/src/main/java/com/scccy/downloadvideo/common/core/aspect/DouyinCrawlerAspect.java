package com.scccy.downloadvideo.common.core.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.annotation.DouyinApi;
import com.scccy.downloadvideo.common.core.utils.manager.XBogusManager;
import com.scccy.downloadvideo.common.core.enums.DouyinApiEnum;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Aspect
@Component
public class DouyinCrawlerAspect extends BaseAspect {

    @Around("@annotation(douyinApi)")
    public Object around(ProceedingJoinPoint point, DouyinApi douyinApi) throws Throwable {
        try {
            // 获取API端点和参数
            DouyinApiEnum endpoint = douyinApi.value();
            Map<String, String> params = buildParams(
                ((MethodSignature) point.getSignature()).getParameterNames(), 
                point.getArgs()
            );
            
            // 生成X-Bogus
            if (douyinApi.needXBogus()) {
                params.put("X-Bogus", XBogusManager.generateXBogus(endpoint.getEndpoint(), params));
            }
            
            // 执行请求并处理响应
            return handleResponse((ResponseEntity<JSONObject>) point.proceed());
            
        } catch (Exception e) {
            return handleException("Douyin API request", e);
        }
    }
}