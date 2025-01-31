package com.scccy.downloadvideo.common.core.model;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;
    
    public static <T> T parseData(String json, Class<T> clazz) {
        try {
            JSONObject response = JSONObject.parseObject(json);
            
            // 抖音API返回格式
            if (response.containsKey("status_code")) {
                if (response.getInteger("status_code") != 0) {
                    throw new ServiceException(
                        String.valueOf(response.getInteger("status_code")),
                        response.getString("status_msg")
                    );
                }
                return response.getObject("data", clazz);
            }
            
            // TikTok API返回格式
            if (response.containsKey("code")) {
                if (response.getInteger("code") != 0) {
                    throw new ServiceException(
                        String.valueOf(response.getInteger("code")),
                        response.getString("message")
                    );
                }
                return response.getObject("data", clazz);
            }
            
            // 其他平台API返回格式可以继续添加
            
            throw new ServiceException("Unknown response format");
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new ServiceException("Failed to parse response: " + e.getMessage());
        }
    }
    
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(0);
        response.setMessage("success");
        response.setData(data);
        return response;
    }
    
    public static <T> ApiResponse<T> error(String code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(Integer.parseInt(code));
        response.setMessage(message);
        return response;
    }
} 