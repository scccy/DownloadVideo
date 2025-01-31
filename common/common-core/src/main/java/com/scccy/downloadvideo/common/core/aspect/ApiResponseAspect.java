package com.scccy.downloadvideo.common.core.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class ApiResponseAspect extends BaseAspect {

    @Around("execution(* com.scccy.downloadvideo.common.download.feign.CrawlerFeignClient.*(..))")
    public Object handleResponse(ProceedingJoinPoint point) throws Throwable {
        try {
            return handleResponse((ResponseEntity<JSONObject>) point.proceed());
        } catch (Exception e) {
            return handleException("API request", e);
        }
    }
} 