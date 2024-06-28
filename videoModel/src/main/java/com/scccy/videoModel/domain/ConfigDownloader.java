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
@TableName(value = "config_downloader")
public class ConfigDownloader implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "downloadertype")
    private String downloadertype;

    @TableField(value = "downloaderlink")
    private String downloaderlink;

    @TableField(value = "username")
    private String username;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "token")
    private String token;

    @TableField(value = "downloaderport")
    private String downloaderport;

    @TableField(value = "downloadername")
    private String downloadername;

    @TableField(value = "`status`")
    private String status;

    @TableField(value = "downloadpath")
    private String downloadpath;

    private static final long serialVersionUID = 1L;
}