package com.scccy.downloadvideo.common.core.handler;

import com.scccy.downloadvideo.common.core.model.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {
    // 处理自定义异常，通常用于业务逻辑错误，例如无效的用户操作等
    @ExceptionHandler(GlobalExceptionHandler.CustomException.class)
    public Mono<ResponseEntity<ResultData>> handleCustomException(GlobalExceptionHandler.CustomException ex) {
        log.error(ex.getMessage());
        return Mono.just(new ResponseEntity<>(
            ResultData.fail()
                .setCode(400)
                .setMsg(ex.getMessage()),
            HttpStatus.BAD_REQUEST
        ));
    }
}
