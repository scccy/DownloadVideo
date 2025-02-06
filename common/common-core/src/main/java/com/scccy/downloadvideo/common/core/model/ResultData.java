package com.scccy.downloadvideo.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultData extends Throwable {
    private Integer code;
    private String msg;
    private Object data;

    public static ResultData ok() {
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setMsg("SUSSES");
        return resultData;
    }
    public static ResultData ok(Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setMsg("SUSSES");
        resultData.setData(data);
        return resultData;
    }
    public static ResultData ok(String mes,Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setMsg(mes);
        resultData.setData(data);
        return resultData;
    }

    public static ResultData fail() {
        ResultData resultData = new ResultData();
        resultData.setCode(500);
        resultData.setMsg("FAIL");
        return resultData;
    }
    public static ResultData fail(String code,String msg,Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(Integer.valueOf(code));
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    public static ResultData fail(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(500);
        resultData.setMsg(msg);
        return resultData;
    }

    public static ResultData fail(String code, String message) {
        ResultData resultData = new ResultData();
        resultData.setCode(Integer.valueOf(code));
        resultData.setMsg(message);
        return resultData;
    }
}
