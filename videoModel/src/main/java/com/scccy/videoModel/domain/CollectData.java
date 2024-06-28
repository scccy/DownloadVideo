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
@TableName(value = "collect_data")
public class CollectData implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "carriedout")
    private String carriedout;

    @TableField(value = "`count`")
    private String count;

    @TableField(value = "createtime")
    private String createtime;

    @TableField(value = "endtime")
    private String endtime;

    @TableField(value = "monitoring")
    private String monitoring;

    @TableField(value = "originaladdress")
    private String originaladdress;

    @TableField(value = "platform")
    private String platform;

    @TableField(value = "taskid")
    private String taskid;

    @TableField(value = "taskname")
    private String taskname;

    @TableField(value = "taskstatus")
    private String taskstatus;

    private static final long serialVersionUID = 1L;
}