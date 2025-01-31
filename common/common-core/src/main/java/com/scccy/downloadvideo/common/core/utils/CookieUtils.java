package com.scccy.downloadvideo.common.core.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CookieUtils {

    /**
     * Cookie 字符串转 JSON
     *
     * @param cookieString Cookie 字符串
     * @return JSON 对象
     */
    public static JSONObject cookieToJson(String cookieString) {
        // 去除可能存在的花括号
        cookieString = cookieString.trim();
        if (cookieString.startsWith("{") && cookieString.endsWith("}")) {
            cookieString = cookieString.substring(1, cookieString.length() - 1);
        }

        // 使用 LinkedHashMap 保持顺序
        Map<String, Object> cookieMap = new LinkedHashMap<>();

        // 按分号分割
        String[] pairs = cookieString.split(";");
        for (String pair : pairs) {
            String[] keyValue = pair.trim().split("=", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                // 尝试解析嵌套的 JSON
                try {
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }
                    if (value.startsWith("{") && value.endsWith("}")) {
                        Object jsonValue = JSON.parseObject(value);
                        cookieMap.put(key, jsonValue);
                    } else {
                        cookieMap.put(key, value);
                    }
                } catch (Exception e) {
                    cookieMap.put(key, value);
                }
            }
        }

        return new JSONObject(cookieMap);
    }

    /**
     * JSON 转 Cookie 字符串
     *
     * @param jsonObject JSON 对象
     * @return Cookie 字符串
     */
    public static String jsonToCookie(JSONObject jsonObject) {
        return jsonObject.entrySet().stream()
            .map(entry -> {
                String key = entry.getKey();
                Object value = entry.getValue();
                
                // 如果值是复杂对象，转换为 JSON 字符串
                if (!(value instanceof String)) {
                    value = JSON.toJSONString(value);
                }
                
                return key + "=" + value;
            })
            .collect(Collectors.joining("; "));
    }

    public static void main(String[] args) {
        // 测试用例
        String cookieStr = "_ac_nonce=0679b87c9008d85c43fe6; __ac_signature=_02B4Z6wo00f012zJ2rAAAIDAPjb6q0kmHKds6d4AALy146; " +
            "stream_recommend_feed_params={\"cookie_enabled\":true,\"screen_width\":1920,\"screen_height\":1080}";

        // Cookie 字符串转 JSON
        JSONObject jsonObj = cookieToJson(cookieStr);
        System.out.println("Cookie to JSON:");
        System.out.println(jsonObj.toString());

        // JSON 转回 Cookie 字符串
        String backToCookie = jsonToCookie(jsonObj);
        System.out.println("\nJSON back to Cookie:");
        System.out.println(backToCookie);
    }
} 