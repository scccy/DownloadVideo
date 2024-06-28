package com.scccy.videoModel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName(value = "video")
public class Video implements Serializable {
    @TableField(value = "id")
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
    public static Video createVideo(String awemeId, String coverunaddr, String videofile, String videounrealaddr, String originaladdress) {
        return Video.builder()
                .videoid(awemeId)
                .videoplatform("抖音")
                .videocover(coverunaddr)
                .videoaddr(videofile)
                .videounrealaddr(videounrealaddr)
                .originaladdress(originaladdress)
                .build();
    }
}