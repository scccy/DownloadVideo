package com.scccy.downloadDy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
@TableName(value = "biz_video")
public class BizVideo implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "videoaddr")
    private String videoaddr;

    @TableField(value = "videocover")
    private String videocover;

    @TableField(value = "videoname")
    private String videoname;

    @TableField(value = "videoplatform")
    private String videoplatform;

    @TableField(value = "createtime")
    private Date createtime;

    @TableField(value = "videodesc")
    private String videodesc;

    @TableField(value = "videounrealaddr")
    private String videounrealaddr;

    @TableField(value = "originaladdress")
    private String originaladdress;

    @TableField(value = "videoid")
    private String videoid;

    private static final long serialVersionUID = 1L;
}