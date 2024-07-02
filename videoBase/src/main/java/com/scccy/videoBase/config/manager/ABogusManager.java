package com.scccy.videoBase.config.manager;

import com.scccy.videoBase.untils.ABogusUtil;
import com.scccy.videoBase.untils.BrowserFingerprintGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ABogusManager {

    public static Map<Integer,String> strToEndpoint(String userAgent, String params, String requestType) throws RuntimeException {
        try {
            String browserFp = BrowserFingerprintGenerator.init("Edge");
            Map<Integer,String> finalEndpoint = new ABogusUtil(browserFp, userAgent).generateAbogus(params, requestType);
            return finalEndpoint;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成A-Bogus失败: " + e.getMessage());
        }
    }

    public static String modelToEndpoint(String userAgent, String baseEndpoint, Map<String, String> params, String requestType) throws RuntimeException {
        if (!(params instanceof Map)) {
            throw new IllegalArgumentException("参数必须是字典类型");
        }

        String paramStr = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        try {
            String browserFp = BrowserFingerprintGenerator.init("Edge");
            Map<Integer,String> abValue = new ABogusUtil(browserFp, userAgent).generateAbogus(paramStr, requestType);

            String separator = baseEndpoint.contains("?") ? "&" : "?";
            String finalEndpoint = baseEndpoint + separator + paramStr + "&a_bogus=" + abValue;
            return finalEndpoint;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成A-Bogus失败: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // Test strToEndpoint method
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36";
            String params = "device_platform=webapp&aid=6383";
            String requestType = "";

            Map<Integer,String> endpoint1 = strToEndpoint(userAgent, params, requestType);
            System.out.println("Endpoint 1: " + endpoint1);

            // Test modelToEndpoint method
            String baseEndpoint = "https://example.com/api";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("key1", "value1");
            paramMap.put("key2", "value2");

            String endpoint2 = modelToEndpoint(userAgent, baseEndpoint, paramMap, requestType);
            System.out.println("Endpoint 2: " + endpoint2);
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


