package com.scccy.downloadDy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleReqVo {

    private String text;         // 解析结果提示
    private String author;       // 作者昵称
    private String describe;     // 作品描述
    private Object download;     // 作品下载地址 (字符串或数组)
    private String music;        // 原声下载地址
    private String origin;       // 静态封面图地址
    private String dynamic;      // 动态封面图地址
    private String preview;      // 作品预览图地址
}
