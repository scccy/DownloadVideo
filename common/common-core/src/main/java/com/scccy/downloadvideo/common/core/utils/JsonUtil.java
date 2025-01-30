package com.scccy.downloadvideo.common.core.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

import java.util.List;

public class JsonUtil {
    
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, JSONWriter.Feature.WriteMapNullValue);
    }
    
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
    
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
    
    public static JSONObject parseObject(String text) {
        return JSON.parseObject(text);
    }
    
    public static <T> T toJavaObject(JSONObject json, Class<T> clazz) {
        return json.toJavaObject(clazz);
    }
} 