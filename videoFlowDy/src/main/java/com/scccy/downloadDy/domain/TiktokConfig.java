package com.scccy.downloadDy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tiktok_config")
public class TiktokConfig implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "analysisserver")
    private String analysisserver;

    @TableField(value = "cookies")
    private String cookies;

    private static final long serialVersionUID = 1L;
}