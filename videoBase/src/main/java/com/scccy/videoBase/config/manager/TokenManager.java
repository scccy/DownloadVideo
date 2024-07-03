package com.scccy.videoBase.config.manager;


import com.alibaba.fastjson2.JSONObject;
import com.scccy.videoBase.domain.ConfigAppConfig;
import com.scccy.videoBase.handlerExption.CustomExceptions;
import com.scccy.videoBase.handlerExption.CustomExceptions.CustomException;
import com.scccy.videoBase.untils.Untils;
import com.scccy.videoBase.untils.downloader.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.scccy.videoBase.untils.Untils.genRandomStr;

@Slf4j
public class TokenManager {

    private static ConfigAppConfig CONFIG_APP_CONFIG;
    private static OkHttpClientUtil okHttpClientUtil;

    public  TokenManager(ConfigAppConfig configAppConfig, OkHttpClientUtil okHttpClientUtil) {
        CONFIG_APP_CONFIG = configAppConfig;
        this.okHttpClientUtil = okHttpClientUtil;
    }

    public static String getRealMsToken() throws CustomExceptions.APIConnectionException, CustomExceptions.APIUnauthorizedException, CustomException, CustomExceptions.APITimeoutException, IOException {
        String payload = "{\"magic\":\"" + CONFIG_APP_CONFIG.getMsTokenMagic() + "\","
                + "\"version\":\"" + CONFIG_APP_CONFIG.getMsTokenVersion() + "\","
                + "\"dataType\":\"" + CONFIG_APP_CONFIG.getMsTokenDataType() + "\","
                + "\"strData\":\"" + CONFIG_APP_CONFIG.getMsTokenStrData() + "\","
                + "\"tspFromClient\":\"" + Untils.getTimestamp("milli") + "\"}";

        JSONObject response = okHttpClientUtil.post(CONFIG_APP_CONFIG.getMsTokenUrl(), payload, JSONObject.class);
        Optional<String> msToken = Optional.ofNullable(response.getString("msToken"));

        if (msToken.isEmpty() || (msToken.get().length() != 120 && msToken.get().length() != 128)) {
            throw new CustomException("msToken 内容不符合要求");
        }

        System.out.println("生成真实的 msToken");
        return msToken.get();
    }

    public String getFalseMsToken() {
        return genRandomStr(126) + "==";
    }

    public static String genTTWID() throws IOException {
        Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("User-Agent", CONFIG_APP_CONFIG.getHeadersUserAgent());
        String body = CONFIG_APP_CONFIG.getTtwidData();

        JSONObject response = okHttpClientUtil.post(
                CONFIG_APP_CONFIG.getTtwidUrl(),
                headers,
                body,
                JSONObject.class
        );

        String ttwid = response.getString("ttwid");

        if (ttwid == null) {
            log.error("ttwid: Check failed, please update ttwid in the configuration");
            throw new CustomException("ttwid: Check failed, please update ttwid in the configuration");
        }

        return ttwid;
    }

    /**
     * 生成个性化追踪webid
     *
     * @return 生成的webid
     */
    public String genWebId() throws  IOException {
        Map<String, String> body = Map.of(
                "app_id", String.valueOf(CONFIG_APP_CONFIG.getConfigAppId()),
                "referer", CONFIG_APP_CONFIG.getWebidBodyReferer(),
                "url", CONFIG_APP_CONFIG.getWebidUrl(),
                "user_agent", CONFIG_APP_CONFIG.getHeadersUserAgent(),
                "user_unique_id", CONFIG_APP_CONFIG.getWebidBodyUserUniqueId()
        );

        JSONObject response = okHttpClientUtil.post(CONFIG_APP_CONFIG.getWebidBodyUrl(), body, JSONObject.class);
        String webid = response.getString("web_id");

        if (webid == null) {
            throw new CustomException("webid 内容不符合要求");
        }

        return webid;
    }


}
