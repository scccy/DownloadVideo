package com.scccy.downloadvideo.common.core.model.dy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scccy.downloadvideo.common.core.enums.DouyinDownloadEnum;
import com.scccy.downloadvideo.common.core.model.BaseEntity;
import com.scccy.downloadvideo.common.core.utils.CookieUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class BaseRequestModel extends BaseEntity {

    // 下载路径配置
    private String path = DouyinDownloadEnum.DEFAULT_PATH.getValue();
    private String naming = DouyinDownloadEnum.DEFAULT_NAMING.getValue();

    // 设备和浏览器环境配置
    private String devicePlatform = DouyinDownloadEnum.DEVICE_PLATFORM.getValue();
    private String aid = DouyinDownloadEnum.AID.getValue();
    private String channel = DouyinDownloadEnum.CHANNEL.getValue();
    private Integer pcClientType = DouyinDownloadEnum.PC_CLIENT_TYPE.toInt();
    private Integer publishVideoStrategyType = DouyinDownloadEnum.PUBLISH_VIDEO_STRATEGY_TYPE.toInt();
    private String pcLibraDivert = DouyinDownloadEnum.PC_LIBRA_DIVERT.getValue();
    private Boolean cookieEnabled = DouyinDownloadEnum.COOKIE_ENABLED.toBoolean();
    private Integer screenWidth = DouyinDownloadEnum.SCREEN_WIDTH.toInt();
    private Integer screenHeight = DouyinDownloadEnum.SCREEN_HEIGHT.toInt();
    private String baseBrowserLanguage = DouyinDownloadEnum.BROWSER_LANGUAGE.getValue();
    private String baseBrowserPlatform = DouyinDownloadEnum.BROWSER_PLATFORM.getValue();
    private String baseBrowserName = DouyinDownloadEnum.BROWSER_NAME.getValue();
    private String baseBrowserVersion = DouyinDownloadEnum.BROWSER_VERSION.getValue();
    private Boolean browserOnline = DouyinDownloadEnum.BROWSER_ONLINE.toBoolean();
    private String engineName = DouyinDownloadEnum.ENGINE_NAME.getValue();
    private String engineVersion = DouyinDownloadEnum.ENGINE_VERSION.getValue();
    private String osName = DouyinDownloadEnum.OS_NAME.getValue();
    private String osVersion = DouyinDownloadEnum.OS_VERSION.getValue();
    private Integer cpuCoreNum = DouyinDownloadEnum.CPU_CORE_NUM.toInt();
    private Integer deviceMemory = DouyinDownloadEnum.DEVICE_MEMORY.toInt();
    private String platform = DouyinDownloadEnum.PLATFORM.getValue();
    private Integer downlink = DouyinDownloadEnum.DOWNLINK.toInt();
    private String effectiveType = DouyinDownloadEnum.EFFECTIVE_TYPE.getValue();
    private Integer roundTripTime = DouyinDownloadEnum.ROUND_TRIP_TIME.toInt();
    private String versionCode = DouyinDownloadEnum.VERSION_CODE.getValue();
    private String versionName = DouyinDownloadEnum.VERSION_NAME.getValue();

    // 请求头配置
    private String userAgent = DouyinDownloadEnum.USER_AGENT.getValue();
    private String referer = DouyinDownloadEnum.REFERER_DOUYIN.getValue();
    private String downloadUrl = "";
    private Map<String, String> headers = new HashMap<>();

    // 代理配置
    private String proxyHost;
    private Integer proxyPort;

    // 下载参数配置
    private Integer timeout = DouyinDownloadEnum.DEFAULT_TIMEOUT.toInt();
    private Integer maxRetries = DouyinDownloadEnum.DEFAULT_MAX_RETRIES.toInt();
    private Integer maxConnections = DouyinDownloadEnum.DEFAULT_MAX_CONNECTIONS.toInt();
    private Integer maxTasks = DouyinDownloadEnum.DEFAULT_MAX_TASKS.toInt();
    private Integer maxCounts = DouyinDownloadEnum.DEFAULT_MAX_COUNTS.toInt();
    private Integer pageCounts = DouyinDownloadEnum.DEFAULT_PAGE_COUNTS.toInt();
    private Integer corePoolSize = DouyinDownloadEnum.DEFAULT_CORE_POOL_SIZE.toInt();

    // 分片下载配置
    private Integer chunks = DouyinDownloadEnum.DEFAULT_CHUNKS.toInt();
    private Boolean enableChunks = true;
    private Integer chunkSize = DouyinDownloadEnum.DEFAULT_CHUNK_SIZE.toInt();

    private String MSTOKEN_URL = DouyinDownloadEnum.MSTOKEN_URL.getValue();
    private String MSTOKEN_MAGIC = DouyinDownloadEnum.MSTOKEN_MAGIC.getValue();
    private String MSTOKEN_VERSION = DouyinDownloadEnum.MSTOKEN_VERSION.getValue();
    private String MSTOKEN_DATATYPE = DouyinDownloadEnum.MSTOKEN_DATATYPE.getValue();
    private String MSTOKEN_STR_DATA = DouyinDownloadEnum.MSTOKEN_STR_DATA.getValue();

    @JsonProperty("msToken")
    private String msToken;

    public void initDefaultHeaders() {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.putIfAbsent("User-Agent", userAgent);
        if (referer != null && !referer.isEmpty()) {
            headers.putIfAbsent("Referer", referer);
        }
    }

    public void addCookie(String c) {
        CookieUtils.cookieToJson(c);
    }

    /**
     * 将对象转换为请求参数Map
     */
    public Map<String, String> toRequestParams() {
        Map<String, String> params = new HashMap<>();

        // 设备信息
        params.put("device_platform", devicePlatform);
        params.put("aid", aid);
        params.put("channel", channel);
        params.put("pc_client_type", String.valueOf(pcClientType));
        params.put("publish_video_strategy_type", String.valueOf(publishVideoStrategyType));
        params.put("pc_libra_divert", pcLibraDivert);
        params.put("cookie_enabled", String.valueOf(cookieEnabled));
        params.put("screen_width", String.valueOf(screenWidth));
        params.put("screen_height", String.valueOf(screenHeight));
        params.put("browser_language", baseBrowserLanguage);
        params.put("browser_platform", baseBrowserPlatform);
        params.put("browser_name", baseBrowserName);
        params.put("browser_version", baseBrowserVersion);
        params.put("browser_online", String.valueOf(browserOnline));
        params.put("engine_name", engineName);
        params.put("engine_version", engineVersion);
        params.put("os_name", osName);
        params.put("os_version", osVersion);
        params.put("cpu_core_num", String.valueOf(cpuCoreNum));
        params.put("device_memory", String.valueOf(deviceMemory));
        params.put("platform", platform);
        params.put("downlink", String.valueOf(downlink));
        params.put("effective_type", effectiveType);
        params.put("round_trip_time", String.valueOf(roundTripTime));
        params.put("version_code", versionCode);
        params.put("version_name", versionName);

        // msToken
        if (msToken != null) {
            params.put("msToken", msToken);
        }

        // 合并自定义headers
        if (headers != null) {
            params.putAll(headers);
        }

        // 移除空值
        params.values().removeIf(value -> value == null || value.isEmpty());

        return params;
    }
}