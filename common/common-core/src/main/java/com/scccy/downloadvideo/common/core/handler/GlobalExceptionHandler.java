package com.scccy.downloadvideo.common.core.handler;

import com.scccy.downloadvideo.common.core.exception.CustomExceptions;
import com.scccy.downloadvideo.common.core.model.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理缺少请求参数的异常
    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResultData> handleMissingServletRequestParameter(ServerWebInputException ex, ServerWebExchange exchange) {
        String message = String.format("Required parameter '%s' is not present", ex.getMessage());
        log.error(ex.getMessage());
        return Mono.just(ResultData.fail().setCode(400).setMsg(message));
    }

    // 处理请求数据字段验证失败的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResultData> handleValidationExceptions(MethodArgumentNotValidException ex, ServerWebExchange exchange) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(ex.getMessage());
        return Mono.just(ResultData.fail().setMsg("Validation failed").setData(errors));
    }

    // 针对数据库异常处理
    @ExceptionHandler(DataAccessException.class)
    public Mono<ResultData> handleSQLException(DataAccessException ex, ServerWebExchange exchange) {
        String errorMessage = "Database error: SQL integrity constraint violation.";
        log.error(errorMessage, ex);
        return Mono.just(ResultData.fail().setMsg(errorMessage).setData(ex.getMessage()));
    }

    // 处理自定义异常
    @ExceptionHandler(CustomExceptions.CustomException.class)
    public Mono<ResultData> handleCustomException(CustomExceptions.CustomException ex, ServerWebExchange exchange) {
        log.error(ex.getMessage());
        return Mono.just(ResultData.fail().setMsg(ex.getMessage()));
    }

    // 处理API超时异常
    @ExceptionHandler(CustomExceptions.APITimeoutException.class)
    public Mono<ResultData> handleAPITimeoutException(CustomExceptions.APITimeoutException ex, ServerWebExchange exchange) {
        log.error("API 超时异常: {}", ex.getMessage(), ex);
        return Mono.just(ResultData.fail().setMsg("API 请求超时错误。").setData(ex.getMessage()));
    }

    // 处理API连接异常
    @ExceptionHandler(CustomExceptions.APIConnectionException.class)
    public Mono<ResultData> handleAPIConnectionException(CustomExceptions.APIConnectionException ex, ServerWebExchange exchange) {
        log.error("API 连接异常: {}", ex.getMessage(), ex);
        return Mono.just(ResultData.fail().setMsg("API 网络连接错误。").setData(ex.getMessage()));
    }

    // 处理API未授权异常
    @ExceptionHandler(CustomExceptions.APIUnauthorizedException.class)
    public Mono<ResultData> handleAPIUnauthorizedException(CustomExceptions.APIUnauthorizedException ex, ServerWebExchange exchange) {
        log.error("API 未授权异常: {}", ex.getMessage(), ex);
        return Mono.just(ResultData.fail().setMsg("API 请求协议错误。").setData(ex.getMessage()));
    }

    // 处理API响应错误异常
    @ExceptionHandler(CustomExceptions.APIResponseError.class)
    public Mono<ResultData> handleAPIResponseError(CustomExceptions.APIResponseError ex, ServerWebExchange exchange) {
        log.error("API 响应错误异常: {}", ex.getMessage(), ex);
        return Mono.just(ResultData.fail().setMsg("API 响应错误。").setData(ex.getMessage()));
    }

    // 处理所有未被特定异常处理器捕获的异常
//    @ExceptionHandler(Exception.class)
//    public Mono<ResultData> handleGlobalException(Exception ex, ServerWebExchange exchange) {
//        log.error(ex.getMessage(), ex);
//        return Mono.just(ResultData.fail().setMsg("未知错误").setData(ex.getMessage()));
//    }
}