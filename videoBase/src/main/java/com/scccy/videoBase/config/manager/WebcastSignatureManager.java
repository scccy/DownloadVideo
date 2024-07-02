package com.scccy.videoBase.config.manager;


import com.scccy.videoBase.apps.douyin.algorithm.WebcastSignature;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @implementation
 *
 * @author scccy
 * @date 2024/7/1上午9:41

 */
@Slf4j
public class WebcastSignatureManager {
    public static String model2Endpoint(String userAgent, String baseEndpoint, Map<String, String> params) {
        if (params == null) {
            throw new IllegalArgumentException("参数必须是字典类型");
        }
        StringJoiner paramStr = new StringJoiner(",");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramStr.add(entry.getKey() + "=" + entry.getValue());
        }
        String signature;
        WebcastSignature webcastSignature = new WebcastSignature(userAgent);
        try {
            signature = webcastSignature.getSignature(userAgent, paramStr.toString());
        } catch (Exception e) {
            log.warn("生成signature失败", e);
            throw new RuntimeException(String.format("生成signature失败: %s", e.getMessage()));
        }

        String separator = baseEndpoint.contains("?") ? "&" : "?";

        return String.format("%s%s%s&signature=%s", baseEndpoint, separator, paramStr.toString(), signature);
    }
}

