package com.scccy.downloadDy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDataReqVo {
    private String uid;
    private String nickName;
    private String desc;
    private String collectTimeStart;
    private String collectTimeEnd;
    private String type;
    private Integer pageNum;
    private Integer pageSize;
}
