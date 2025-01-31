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
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ResultData>> handleGlobalException(Exception ex) {
        log.error("Unexpected error: ", ex);
        return Mono.just(new ResponseEntity<>(
            ResultData.fail()
                .setMsg("An unexpected error occurred")
                .setData(ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
        ));
    }
}