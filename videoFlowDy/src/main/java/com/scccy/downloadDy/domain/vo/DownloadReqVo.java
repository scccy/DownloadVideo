package com.scccy.downloadDy.domain.vo;

import com.alibaba.fastjson2.support.spring6.data.redis.GenericFastJsonRedisSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.downloadDy.domain.BaseEntity;
import com.scccy.pojo.DownloadFather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadReqVo implements Serializable, DownloadFather {
    private Long id;
    private String nickname;
    private String desc;
    private String dynamicCover;
    private String originCover;
    private String downloads;

}
