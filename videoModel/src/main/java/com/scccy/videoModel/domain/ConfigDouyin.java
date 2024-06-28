package com.scccy.videoModel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Schema
@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "config_douyin")
public class ConfigDouyin implements Serializable {
    @TableField(value = "id")
    @Schema(description="")
    private Integer id;

    @TableField(value = "analysisserver")
    @Schema(description="")
    private String analysisserver;

    @TableField(value = "cookies")
    @Schema(description="")
    private String cookies;

    private static final long serialVersionUID = 1L;
}