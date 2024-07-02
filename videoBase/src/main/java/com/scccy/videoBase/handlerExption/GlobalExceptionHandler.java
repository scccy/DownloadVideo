package com.scccy.videoBase.handlerExption;

import com.scccy.videoBase.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理缺少请求参数的异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResultData> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        String message = String.format("Required parameter '%s' is not present", ex.getParameterName());
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultData.fail().setCode(400).setMsg(message), HttpStatus.BAD_REQUEST);
    }

    // 处理请求数据字段验证失败的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultData.fail().setMsg("Validation failed").setData(errors), HttpStatus.BAD_REQUEST);
    }

    // 针对数据库异常处理
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResultData> handleSQLException(SQLException ex) {
        String errorMessage = "Database error: SQL integrity constraint violation.";
        log.error(errorMessage, ex);
        return new ResponseEntity<>(ResultData.fail().setMsg(errorMessage).setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理自定义异常
    @ExceptionHandler(CustomExceptions.CustomException.class)
    public ResponseEntity<ResultData> handleCustomException(CustomExceptions.CustomException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultData.fail().setMsg(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 处理API超时异常
    @ExceptionHandler(CustomExceptions.APITimeoutException.class)
    public ResponseEntity<ResultData> handleAPITimeoutException(CustomExceptions.APITimeoutException ex) {
        log.error("API 超时异常: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ResultData.fail().setMsg("API 请求超时错误。").setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理API连接异常
    @ExceptionHandler(CustomExceptions.APIConnectionException.class)
    public ResponseEntity<ResultData> handleAPIConnectionException(CustomExceptions.APIConnectionException ex) {
        log.error("API 连接异常: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ResultData.fail().setMsg("API 网络连接错误。").setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理API未授权异常
    @ExceptionHandler(CustomExceptions.APIUnauthorizedException.class)
    public ResponseEntity<ResultData> handleAPIUnauthorizedException(CustomExceptions.APIUnauthorizedException ex) {
        log.error("API 未授权异常: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ResultData.fail().setMsg("API 请求协议错误。").setData(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    // 处理API响应错误异常
    @ExceptionHandler(CustomExceptions.APIResponseError.class)
    public ResponseEntity<ResultData> handleAPIResponseError(CustomExceptions.APIResponseError ex) {
        log.error("API 响应错误异常: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ResultData.fail().setMsg("API 响应错误。").setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理所有未被特定异常处理器捕获的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultData> handleGlobalException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ResultData.fail().setMsg("An unexpected error occurred, please contact support.").setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}