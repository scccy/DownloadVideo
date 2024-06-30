package com.scccy.videoBase.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)

@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "config_app_config")
public class ConfigAppConfig extends BaseEntity {
    @TableId(value = "config_app_id", type = IdType.INPUT)
    private Integer configAppId;

    @TableField(value = "config_app_name")
    private String configAppName;

    @TableField(value = "base_request_model_version_code")
    private String baseRequestModelVersionCode;

    @TableField(value = "base_request_model_version_name")
    private String baseRequestModelVersionName;

    @TableField(value = "base_request_model_browser_language")
    private String baseRequestModelBrowserLanguage;

    @TableField(value = "base_request_model_browser_platform")
    private String baseRequestModelBrowserPlatform;

    @TableField(value = "base_request_model_browser_name")
    private String baseRequestModelBrowserName;

    @TableField(value = "base_request_model_browser_version")
    private String baseRequestModelBrowserVersion;

    @TableField(value = "base_request_model_engine_name")
    private String baseRequestModelEngineName;

    @TableField(value = "base_request_model_engine_version")
    private String baseRequestModelEngineVersion;

    @TableField(value = "base_request_model_os_name")
    private String baseRequestModelOsName;

    @TableField(value = "base_request_model_os_version")
    private String baseRequestModelOsVersion;

    @TableField(value = "base_request_model_region")
    private String baseRequestModelRegion;

    @TableField(value = "base_request_model_priority_region")
    private String baseRequestModelPriorityRegion;

    @TableField(value = "base_request_model_webcast_language")
    private String baseRequestModelWebcastLanguage;

    @TableField(value = "base_request_model_tz_name")
    private String baseRequestModelTzName;

    @TableField(value = "base_request_model_device_id")
    private String baseRequestModelDeviceId;

    @TableField(value = "base_request_model_device_platform")
    private String baseRequestModelDevicePlatform;

    @TableField(value = "base_live_model_language")
    private String baseLiveModelLanguage;

    @TableField(value = "base_live_model_browser_language")
    private String baseLiveModelBrowserLanguage;

    @TableField(value = "base_live_model_browser_platform")
    private String baseLiveModelBrowserPlatform;

    @TableField(value = "base_live_model_browser_name")
    private String baseLiveModelBrowserName;

    @TableField(value = "base_live_model_browser_version")
    private String baseLiveModelBrowserVersion;

    @TableField(value = "headers_user_agent")
    private String headersUserAgent;

    @TableField(value = "headers_referer")
    private String headersReferer;

    @TableField(value = "ms_token_url")
    private String msTokenUrl;

    @TableField(value = "ms_token_url2")
    private String msTokenUrl2;

    @TableField(value = "ms_token_magic")
    private Integer msTokenMagic;

    @TableField(value = "ms_token_version")
    private Integer msTokenVersion;

    @TableField(value = "ms_token_data_type")
    private Integer msTokenDataType;

    @TableField(value = "ms_token_str_data")
    private String msTokenStrData;

    @TableField(value = "ttwid_url")
    private String ttwidUrl;

    @TableField(value = "ttwid_data")
    private String ttwidData;

    @TableField(value = "ttwid_cookie")
    private String ttwidCookie;

    @TableField(value = "webid_url")
    private String webidUrl;

    @TableField(value = "webid_body_app_id")
    private Integer webidBodyAppId;

    @TableField(value = "webid_body_referer")
    private String webidBodyReferer;

    @TableField(value = "webid_body_url")
    private String webidBodyUrl;

    @TableField(value = "webid_body_user_agent")
    private String webidBodyUserAgent;

    @TableField(value = "webid_body_user_unique_id")
    private String webidBodyUserUniqueId;

    @TableField(value = "odin_tt_url")
    private String odinTtUrl;

    @TableField(value = "visitor_url")
    private String visitorUrl;

    @TableField(value = "visitor_cb")
    private String visitorCb;

    @TableField(value = "visitor_tid")
    private String visitorTid;

    @TableField(value = "visitor_from")
    private String visitorFrom;
}