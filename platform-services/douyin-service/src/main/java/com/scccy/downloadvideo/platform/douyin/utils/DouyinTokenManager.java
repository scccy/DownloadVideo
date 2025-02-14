package com.scccy.downloadvideo.platform.douyin.utils;

import com.alibaba.fastjson2.JSON;
import com.scccy.downloadvideo.common.core.enums.DouyinApiEnum;
import com.scccy.downloadvideo.common.core.enums.DouyinDownloadEnum;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.common.download.core.BaseHttpClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;

@Slf4j
@Component
public class DouyinTokenManager {

    @Autowired
    private BaseHttpClient baseHttpClient;

    //    private static final Map<String, Object> tokenConf = ClientConfManager.msToken();
    BaseRequestModel tokenConf = new BaseRequestModel();

    public String genRealMsToken() {
        try {
            // 构造请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("User-Agent", DouyinDownloadEnum.USER_AGENT.getValue());

            // 构造请求体
            Map<String, Object> payload = new HashMap<>();
            payload.put("magic", tokenConf.getMSTOKEN_MAGIC());
            payload.put("version", tokenConf.getMSTOKEN_VERSION());
            payload.put("dataType", tokenConf.getMSTOKEN_DATATYPE());
            payload.put("strData", tokenConf.getMSTOKEN_STR_DATA());
            payload.put("tspFromClient", System.currentTimeMillis());

            // 发送请求
            Mono<Response> resultMono = baseHttpClient.postAsync(
                    tokenConf.getDownloadUrl().toString(),
                    JSON.toJSONString(payload),
                    headers,
                    null,  // proxyHost
                    null   // proxyPort
            );

            // 处理响应
            Response result = resultMono.block();
            if (result == null || result.isSuccessful()) {
                throw new RuntimeException("请求失败: " + (result != null ? result.request() : "无响应"));
            }

            // 从响应头中提取msToken
            String msToken = extractMsToken(result);
            if (msToken == null || (msToken.length() != 120 && msToken.length() != 128)) {
                throw new RuntimeException("msToken 内容不符合要求");
            }

            log.debug("生成真实的 msToken: {}", msToken);
            return msToken;

        } catch (Exception e) {
            log.error("生成 msToken 失败", e);
            return genFalseMsToken();
        }
    }

    private String extractMsToken(Response result) {
//        Map<String, String> headers = result.getHeaders();
        Headers headers = result.headers();
        String cookies = headers.get("Set-Cookie");
        for (String part : cookies.split(";")) {
            if (part.trim().startsWith("msToken=")) {
                return part.substring(8).trim();
                }
            }
        return null;
    }

    public String genFalseMsToken() {
        return "FAKE_MS_TOKEN_" + System.currentTimeMillis();
    }
}
