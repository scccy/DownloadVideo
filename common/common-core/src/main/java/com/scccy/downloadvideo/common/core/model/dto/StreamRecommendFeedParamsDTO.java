package com.scccy.downloadvideo.common.core.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StreamRecommendFeedParamsDTO {
    private Boolean cookieEnabled;
    private Integer screenWidth;
    private Integer screenHeight;
    private Boolean browserOnline;
    private Integer cpuCoreNum;
    private Integer deviceMemory;
    private Integer downlink;
    private String effectiveType;
    private Integer roundTripTime;
}
