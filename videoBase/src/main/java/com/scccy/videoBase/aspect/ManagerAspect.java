package com.scccy.videoBase.aspect;

import com.scccy.videoBase.handlerExption.CustomExceptions;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ManagerAspect {
    @AfterThrowing(pointcut = "execution(* com.scccy.videoBase.config.manager.*.*(..))", throwing = "ex")
    public void handleException(Exception ex) throws Exception {
        if (ex instanceof CustomExceptions.APITimeoutException) {
            log.error("APITimeoutException: ", ex);
            throw (CustomExceptions.APITimeoutException) ex;
        } else if (ex instanceof CustomExceptions.APIConnectionException) {
            log.error("APIConnectionException: ", ex);
            throw (CustomExceptions.APIConnectionException) ex;
        } else if (ex instanceof CustomExceptions.APIUnauthorizedException) {
            log.error("APIUnauthorizedException: ", ex);
            throw (CustomExceptions.APIUnauthorizedException) ex;
        } else {
            log.error("Unexpected Exception: ", ex);
            throw ex;
        }
    }
}
