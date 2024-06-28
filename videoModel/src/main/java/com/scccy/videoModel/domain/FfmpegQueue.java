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
@TableName(value = "ffmpeg_queue")
public class FfmpegQueue implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "audiostatus")
    private String audiostatus;

    @TableField(value = "createtime")
    private String createtime;

    @TableField(value = "filepath")
    private String filepath;

    @TableField(value = "pendingfolder")
    private String pendingfolder;

    @TableField(value = "`status`")
    private String status;

    @TableField(value = "videoid")
    private String videoid;

    @TableField(value = "videoname")
    private String videoname;

    @TableField(value = "videostatus")
    private String videostatus;

    private static final long serialVersionUID = 1L;
}