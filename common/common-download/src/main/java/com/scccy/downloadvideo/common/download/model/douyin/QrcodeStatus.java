package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

import java.util.Map;

@Data
public class QrcodeStatus {
    private String status; // new/scanned/confirmed/expired
    private String redirectUrl;
    private Map<String, String> cookies;
} 