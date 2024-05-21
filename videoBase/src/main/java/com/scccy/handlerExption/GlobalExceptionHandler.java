package com.scccy.handlerExption;


import com.scccy.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //    针对数据库异常处理
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResultData> handleSQLException(SQLException ex) {
        String errorMessage = "Database error: SQL integrity constraint violation.";
        log.error(errorMessage, ex);
        return new ResponseEntity<>(ResultData.fail().setMsg(errorMessage).setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理所有未被前面特定异常处理器捕获的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultData> handleGlobalException(Exception ex) {
        // 通常是未预料的错误，返回一个INTERNAL_SERVER_ERROR(500)响应
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultData.fail().setMsg("An unexpected error occurred, please contact support.")
                .setData(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    // 自定义的异常类
    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }
}