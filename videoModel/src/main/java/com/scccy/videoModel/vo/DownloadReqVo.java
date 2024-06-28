package com.scccy.videoModel.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadReqVo implements Serializable {
    private Long id;
    private String nickname;
    private String desc;
    private String dynamicCover;
    private String originCover;
    private String downloads;

}
