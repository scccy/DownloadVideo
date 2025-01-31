package com.scccy.downloadvideo.common.core.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class BaseAspect {
    
    /**
     * 构建参数Map
     */
    protected Map<String, String> buildParams(String[] paramNames, Object[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                params.put(paramNames[i], String.valueOf(args[i]));
            }
        }
        return params;
    }
    
    /**
     * 处理HTTP响应
     */
    protected JSONObject handleResponse(ResponseEntity<JSONObject> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ServiceException(
                String.valueOf(response.getStatusCode().value()),
                "HTTP request failed"
            );
        }
        
        JSONObject body = response.getBody();
        if (body == null) {
            throw new ServiceException("Response body is null");
        }
        
        Integer statusCode = body.getInteger("status_code");
        if (statusCode != null && statusCode != 0) {
            throw new ServiceException(
                String.valueOf(statusCode),
                body.getString("status_msg")
            );
        }
        
        return body.getJSONObject("data");
    }
    
    /**
     * 统一的异常处理
     */
    protected Object handleException(String operation, Exception e) {
        log.error("{} failed: {}", operation, e.getMessage());
        throw new ServiceException(e.getMessage());
    }
} 