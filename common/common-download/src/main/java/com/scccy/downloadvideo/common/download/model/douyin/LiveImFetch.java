package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;

@Data
public class LiveImFetch {
    private String cursor;
    private String internalExt;
    private String routeParams;
    private boolean fetchSuccess;
} 