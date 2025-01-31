package com.scccy.downloadvideo.platform.douyin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.downloadvideo.common.core.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 抖音应用配置表
 */
@Schema(description = "抖音应用配置表")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "douyin_download_config")
public class DouyinDownloadConfig extends BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * Cookie
     */
    @TableField(value = "cookie")
    @Schema(description = "Cookie")
    private String cookie;

    /**
     * 命名格式
     */
    @TableField(value = "naming")
    @Schema(description = "命名格式")
    private String naming;

    /**
     * 下载路径
     */
    @TableField(value = "`path`")
    @Schema(description = "下载路径")
    private String path;

    /**
     * 超时时间
     */
    @TableField(value = "timeout")
    @Schema(description = "超时时间")
    private Integer timeout;

    /**
     * 最大重试次数
     */
    @TableField(value = "max_retries")
    @Schema(description = "最大重试次数")
    private Integer maxRetries;

    /**
     * 是否下载歌词
     */
    @TableField(value = "lyric")
    @Schema(description = "是否下载歌词")
    private Boolean lyric;

    /**
     * 最大连接数
     */
    @TableField(value = "max_connections")
    @Schema(description = "最大连接数")
    private Integer maxConnections;

    /**
     * 最大下载数
     */
    @TableField(value = "max_counts")
    @Schema(description = "最大下载数")
    private Integer maxCounts;

    /**
     * 最大任务数
     */
    @TableField(value = "max_tasks")
    @Schema(description = "最大任务数")
    private Integer maxTasks;

    /**
     * 每页数量
     */
    @TableField(value = "page_counts")
    @Schema(description = "每页数量")
    private Integer pageCounts;

    private static final long serialVersionUID = 1L;
}