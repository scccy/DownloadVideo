package com.scccy.videoModel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "config")
public class Config implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "apptoken")
    private String apptoken;

    @TableField(value = "ipauth")
    private String ipauth;
    @TableField(value = "openprocesshistory")
    private String openprocesshistory;

//    @TableField(value = "taskcron"))
    private String taskcron;

    private static final long serialVersionUID = 1L;
}