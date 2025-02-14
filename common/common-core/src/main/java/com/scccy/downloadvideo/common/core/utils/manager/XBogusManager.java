package com.scccy.downloadvideo.common.core.utils.manager;

import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;
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
    public static String str_2_endpoint(String user_agent, String endpoint) {
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
     * 处理带配置的URL
     */
    public static String model_2_endpoint(String user_agent, String base_endpoint, BaseRequestModel config) {
        if (config == null) {
            throw new IllegalArgumentException("配置不能为空");
        }

        // 构建参数字符串
        String param_str = buildParamString(config);

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
    public String generateEndpoint(String base_endpoint, BaseRequestModel config, String user_agent) {
        return model_2_endpoint(user_agent, base_endpoint, config);
    }

    /**
     * 直接生成X-Bogus值
     */
    public static String generateXBogus(String endpoint, BaseRequestModel config) {
        try {
            String paramStr = buildParamString(config);
            return XbogusUtil.getXBogus(paramStr);
        } catch (Exception e) {
            log.error("生成X-Bogus失败", e);
            throw new RuntimeException("生成X-Bogus失败: " + e.getMessage());
        }
    }

    /**
     * 从配置构建参数字符串
     */
    private static String buildParamString(BaseRequestModel config) {
        StringBuilder paramBuilder = new StringBuilder();
        
        // 添加设备信息
        paramBuilder.append("device_platform=").append(config.getDevicePlatform())
                .append("&aid=").append(config.getAid())
                .append("&channel=").append(config.getChannel())
                .append("&pc_client_type=").append(config.getPcClientType())
                .append("&publish_video_strategy_type=").append(config.getPublishVideoStrategyType())
                .append("&pc_libra_divert=").append(config.getPcLibraDivert())
                .append("&cookie_enabled=").append(config.getCookieEnabled())
                .append("&screen_width=").append(config.getScreenWidth())
                .append("&screen_height=").append(config.getScreenHeight())
                .append("&browser_online=").append(config.getBrowserOnline())
                .append("&cpu_core_num=").append(config.getCpuCoreNum())
                .append("&device_memory=").append(config.getDeviceMemory())
                .append("&platform=").append(config.getPlatform())
                .append("&downlink=").append(config.getDownlink())
                .append("&effective_type=").append(config.getEffectiveType())
                .append("&round_trip_time=").append(config.getRoundTripTime());

        // 添加其他自定义headers
        if (config.getHeaders() != null && !config.getHeaders().isEmpty()) {
            config.getHeaders().forEach((key, value) -> 
                paramBuilder.append("&").append(key).append("=").append(value));
        }

        return paramBuilder.toString();
    }
}