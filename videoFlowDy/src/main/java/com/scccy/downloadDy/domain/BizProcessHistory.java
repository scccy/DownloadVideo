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
@TableName(value = "biz_process_history")
public class BizProcessHistory implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "createtime")
    private String createtime;

    @TableField(value = "originaladdress")
    private String originaladdress;

    @TableField(value = "\"status\"")
    private String status;

    @TableField(value = "videoplatform")
    private String videoplatform;

    private static final long serialVersionUID = 1L;
}