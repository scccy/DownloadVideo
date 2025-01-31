package com.scccy.downloadvideo.common.core.config.manager;

import com.scccy.downloadvideo.common.core.utils.XbogusUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class XBogusManager {
    private final XbogusUtil xbogusUtil;

    public XBogusManager() {
        this.xbogusUtil = new XbogusUtil(null); // 使用默认UA
    }

    public XBogusManager(String userAgent) {
        this.xbogusUtil = new XbogusUtil(userAgent);
    }




    /**
     * 直接处理URL字符串
     */
    public String str_2_endpoint(String user_agent, String endpoint) {
        try {
            String xb_value = XbogusUtil.getXBogus(endpoint);
            String separator = endpoint.contains("?") ? "&" : "?";
            return endpoint + separator + "X-Bogus=" + xb_value;
        } catch (Exception e) {
            log.error("生成X-Bogus失败", e);
            throw new RuntimeException("生成X-Bogus失败: " + e.getMessage());
        }
    }

    /**
     * 处理带参数的URL
     */
    public String model_2_endpoint(String user_agent, String base_endpoint, Map<String, String> params) {
        if (params == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        // 构建参数字符串
        String param_str = params.entrySet().stream()
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .collect(Collectors.joining("&"));

        try {
            // 生成X-Bogus
            String xb_value = XbogusUtil.getXBogus(param_str);
            
            // 处理URL分隔符
            String separator = base_endpoint.contains("?") ? "&" : "?";
            
            // 构建最终URL
            return base_endpoint + separator + param_str + "&X-Bogus=" + xb_value;
        } catch (Exception e) {
            log.error("生成X-Bogus失败: {}", param_str, e);
            throw new RuntimeException("生成X-Bogus失败: " + e.getMessage());
        }
    }

    /**
     * 便捷方法，直接生成完整URL
     */
    public String generateEndpoint(String base_endpoint, Map<String, String> params, String user_agent) {
        return model_2_endpoint(user_agent, base_endpoint, params);
    }
    public static String generateXBogus(String endpoint, Map<String, String> params) {
        try {
            String paramStr = params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            return XbogusUtil.getXBogus(paramStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成X-Bogus失败: " + e.getMessage());
        }
    }
}