package com.scccy.downloadvideo.common.core.handler;

import com.scccy.downloadvideo.common.core.model.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ResultData>> handleValidationExceptions(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        return Mono.just(new ResponseEntity<>(
            ResultData.fail()
                .setMsg("Validation failed")
                .setData(errors),
            HttpStatus.BAD_REQUEST
        ));
    }
}
