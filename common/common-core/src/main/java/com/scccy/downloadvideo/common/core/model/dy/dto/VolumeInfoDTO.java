package com.scccy.downloadvideo.common.core.model.dy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VolumeInfoDTO {
    private Boolean isUserMute;
    private Boolean isMute;
    private Double volume;
}
