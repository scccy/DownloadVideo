package com.scccy.downloadvideo.platform.douyin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.downloadvideo.common.core.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 抖音配置表
 */
@Schema(description="抖音配置表")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "douyin_config")
public class DouyinConfig extends BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description="主键ID")
    private Long id;

    /**
     * 加密参数
     */
    @TableField(value = "encryption")
    @Schema(description="加密参数")
    private String encryption;

    /**
     * 版本号
     */
    @TableField(value = "base_request_model_version_code")
    @Schema(description="版本号")
    private String baseRequestModelVersionCode;

    /**
     * 版本名称
     */
    @TableField(value = "base_request_model_version_name")
    @Schema(description="版本名称")
    private String baseRequestModelVersionName;

    /**
     * 浏览器语言
     */
    @TableField(value = "base_request_model_browser_language")
    @Schema(description="浏览器语言")
    private String baseRequestModelBrowserLanguage;

    /**
     * 浏览器平台
     */
    @TableField(value = "base_request_model_browser_platform")
    @Schema(description="浏览器平台")
    private String baseRequestModelBrowserPlatform;

    /**
     * 浏览器名称
     */
    @TableField(value = "base_request_model_browser_name")
    @Schema(description="浏览器名称")
    private String baseRequestModelBrowserName;

    /**
     * 浏览器版本
     */
    @TableField(value = "base_request_model_browser_version")
    @Schema(description="浏览器版本")
    private String baseRequestModelBrowserVersion;

    /**
     * 引擎名称
     */
    @TableField(value = "base_request_model_engine_name")
    @Schema(description="引擎名称")
    private String baseRequestModelEngineName;

    /**
     * 引擎版本
     */
    @TableField(value = "base_request_model_engine_version")
    @Schema(description="引擎版本")
    private String baseRequestModelEngineVersion;

    /**
     * 操作系统名称
     */
    @TableField(value = "base_request_model_os_name")
    @Schema(description="操作系统名称")
    private String baseRequestModelOsName;

    /**
     * 操作系统版本
     */
    @TableField(value = "base_request_model_os_version")
    @Schema(description="操作系统版本")
    private String baseRequestModelOsVersion;

    /**
     * User-Agent
     */
    @TableField(value = "headers_user_agent")
    @Schema(description="User-Agent")
    private String headersUserAgent;

    /**
     * Referer
     */
    @TableField(value = "headers_referer")
    @Schema(description="Referer")
    private String headersReferer;

    /**
     * HTTP代理
     */
    @TableField(value = "proxies_http")
    @Schema(description="HTTP代理")
    private String proxiesHttp;

    /**
     * HTTPS代理
     */
    @TableField(value = "proxies_https")
    @Schema(description="HTTPS代理")
    private String proxiesHttps;

    /**
     * WSS域名
     */
    @TableField(value = "wss_domain")
    @Schema(description="WSS域名")
    private String wssDomain;

    /**
     * WSS端口
     */
    @TableField(value = "wss_port")
    @Schema(description="WSS端口")
    private Integer wssPort;

    /**
     * WSS验证
     */
    @TableField(value = "wss_verify")
    @Schema(description="WSS验证")
    private Boolean wssVerify;

    /**
     * msToken URL
     */
    @TableField(value = "ms_token_url")
    @Schema(description="msToken URL")
    private String msTokenUrl;

    /**
     * msToken magic
     */
    @TableField(value = "ms_token_magic")
    @Schema(description="msToken magic")
    private Long msTokenMagic;

    /**
     * msToken 版本
     */
    @TableField(value = "ms_token_version")
    @Schema(description="msToken 版本")
    private Integer msTokenVersion;

    /**
     * msToken 数据类型
     */
    @TableField(value = "ms_token_data_type")
    @Schema(description="msToken 数据类型")
    private Integer msTokenDataType;

    /**
     * msToken 字符串数据
     */
    @TableField(value = "ms_token_str_data")
    @Schema(description="msToken 字符串数据")
    private String msTokenStrData;

    /**
     * ttwid URL
     */
    @TableField(value = "ttwid_url")
    @Schema(description="ttwid URL")
    private String ttwidUrl;

    /**
     * ttwid 数据
     */
    @TableField(value = "ttwid_data")
    @Schema(description="ttwid 数据")
    private String ttwidData;

    /**
     * webid URL
     */
    @TableField(value = "webid_url")
    @Schema(description="webid URL")
    private String webidUrl;

    /**
     * webid 应用ID
     */
    @TableField(value = "webid_app_id")
    @Schema(description="webid 应用ID")
    private Integer webidAppId;

    /**
     * webid SDK版本
     */
    @TableField(value = "webid_sdk_version")
    @Schema(description="webid SDK版本")
    private String webidSdkVersion;

    /**
     * webid 设备平台
     */
    @TableField(value = "webid_device_platform")
    @Schema(description="webid 设备平台")
    private String webidDevicePlatform;

    private static final long serialVersionUID = 1L;
}