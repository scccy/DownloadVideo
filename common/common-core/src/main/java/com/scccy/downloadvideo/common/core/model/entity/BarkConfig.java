package com.scccy.downloadvideo.common.core.model.entity;


import com.scccy.downloadvideo.common.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BarkConfig extends BaseEntity {
    private String headersUserAgent;
    private String headersReferer;
    private String encryptionAlgorithm;
    private Boolean encryptionEnable;
    private String encryptionMode;
    private String encryptionPadding;
    private String encryptionKey;
    private String proxiesHttp;
    private String proxiesHttps;
}