package com.scccy.downloadDy.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultData {
    private Integer code;
    private String msg;
    private Object data;

    public static ResultData ok() {
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setMsg("SUSSES");
        return resultData;
    }


    public static ResultData fail() {
        ResultData resultData = new ResultData();
        resultData.setCode(500);
        resultData.setMsg("FAIL");
        return resultData;
    }



}
