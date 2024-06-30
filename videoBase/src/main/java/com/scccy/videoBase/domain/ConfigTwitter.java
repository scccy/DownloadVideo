package com.scccy.videoBase.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)

@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "config_twitter")
public class ConfigTwitter extends BaseEntity {
    @TableId(value = "config_twitter_id", type = IdType.INPUT)
    private Integer configTwitterId;

    @TableField(value = "cookie")
    private String cookie;

    @TableField(value = "config_base_id")
    private Integer configBaseId;

    @TableField(value = "config_app_id")
    private Integer configAppId;

    @TableField(value = "folderize")
    private Integer folderize;

    @TableField(value = "`mode`")
    private Integer mode;
}