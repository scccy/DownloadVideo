package com.scccy.downloadvideo.common.core.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FORCELOGINDTO {
    private Integer videoConsumedRemainSeconds;
    private Integer isForcePopClose;
}
