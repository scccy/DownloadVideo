package com.scccy.videoDownloader.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {



    @Around("execution(public * com.scccy.videoDownloader.untils.HttpUtil.*(..))")
    public Object manageHttpConnection(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        try {
            // 在方法执行前建立连接
            Object[] args = joinPoint.getArgs();
            result = joinPoint.proceed(args);
        }catch (Exception e){
            throw e;
        }
        return result;
    }
}
