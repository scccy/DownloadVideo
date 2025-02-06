package com.scccy.downloadvideo.common.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Aspect
@Component
public class DownloaderAspect {

    @Around("execution(* com.scccy.downloadvideo.common.download.downloader.BaseDownloader.*(..))")
    public Object aroundDownload(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        
        log.debug("Downloading method: {}, url: {}", methodName, args[0]);
        long startTime = System.currentTimeMillis();
        
        try {
            Object result = point.proceed();
            if (result instanceof Mono) {
                return ((Mono<?>) result).doOnSuccess(r -> 
                    log.debug("Download completed: {}, time: {}ms", 
                            methodName, System.currentTimeMillis() - startTime)
                ).doOnError(e -> 
                    log.error("Download failed: {}, error: {}", 
                            methodName, e.getMessage(), e)
                );
            }
            return result;
        } catch (Exception e) {
            log.error("Download error: {}, method: {}", e.getMessage(), methodName, e);
            throw e;
        }
    }
} 