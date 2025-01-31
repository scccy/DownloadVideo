package com.scccy.downloadvideo.common.core.model;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class DouyinResponse<T> {
    private Integer statusCode;
    private String statusMsg;
    private T data;
    
    public static <T> T parseData(String json, Class<T> clazz) {
        JSONObject response = JSONObject.parseObject(json);
        if (response.getInteger("status_code") != 0) {
            throw new RuntimeException("抖音接口返回错误: " + response.getString("status_msg"));
        }
        return response.getObject("data", clazz);
    }
} 