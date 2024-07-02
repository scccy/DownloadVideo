package com.scccy.videoBase.config.manager;

import com.scccy.videoBase.untils.XbogusUtil;

import java.util.HashMap;
import java.util.Map;

public class XBogusManager {

    public static String str_2_endpoint(String user_agent, String endpoint) {
        try {

            String final_endpoint = XbogusUtil.getXBogus(endpoint);
            return String.valueOf(final_endpoint.charAt(0)); // Assuming XB.getXBogus() returns a String
        } catch (Exception e) {
            e.printStackTrace(); // Logging or handling the exception as needed
            throw new RuntimeException("生成X-Bogus失败: " + e.getMessage());
        }
    }

    public static String model_2_endpoint(String user_agent, String base_endpoint, Map<String, String> params) {
        if (params == null || !(params instanceof HashMap)) {
            throw new IllegalArgumentException("参数必须是HashMap类型");
        }

        String param_str = String.join("&", params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .toArray(String[]::new));

        try {
            String xb_value = XbogusUtil.getXBogus(param_str);
            String separator = base_endpoint.contains("?") ? "&" : "?";
            String final_endpoint = base_endpoint + separator + param_str + "&X-Bogus=" + xb_value.charAt(1); // Assuming xb_value is a String
            return final_endpoint;
        } catch (Exception e) {
            e.printStackTrace(); // Logging or handling the exception as needed
            throw new RuntimeException("生成X-Bogus失败: " + e.getMessage());
        }
    }
}