package com.scccy.downloadvideo.common.core.model.entity;

import com.scccy.downloadvideo.common.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DouyinConfig extends BaseEntity {
    private String encryption;
    private String baseRequestModelVersionCode;
    private String baseRequestModelVersionName;
    private String baseRequestModelBrowserLanguage;
    private String baseRequestModelBrowserPlatform;
    private String baseRequestModelBrowserName;
    private String baseRequestModelBrowserVersion;
    private String baseRequestModelEngineName;
    private String baseRequestModelEngineVersion;
    private String baseRequestModelOsName;
    private String baseRequestModelOsVersion;
    private String headersUserAgent;
    private String headersReferer;
    private String proxiesHttp;
    private String proxiesHttps;
    private String wssDomain;
    private Integer wssPort;
    private Boolean wssVerify;
} 