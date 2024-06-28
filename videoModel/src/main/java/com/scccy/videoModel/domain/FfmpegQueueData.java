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
@TableName(value = "ffmpeg_queue_data")
public class FfmpegQueueData implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "createtime")
    private String createtime;

    @TableField(value = "filepath")
    private String filepath;

    @TableField(value = "filetype")
    private String filetype;

    @TableField(value = "queueid")
    private Integer queueid;

    @TableField(value = "`status`")
    private String status;

    @TableField(value = "taskid")
    private String taskid;

    private static final long serialVersionUID = 1L;
}