package com.scccy.videoModel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName(value = "collect_data_detail")
public class CollectDataDetail implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "createtime")
    private String createtime;

    @TableField(value = "dataid")
    private Integer dataid;

    @TableField(value = "originaladdress")
    private String originaladdress;

    @TableField(value = "`status`")
    private String status;

    @TableField(value = "videoid")
    private String videoid;

    @TableField(value = "videoname")
    private String videoname;

    private static final long serialVersionUID = 1L;
}