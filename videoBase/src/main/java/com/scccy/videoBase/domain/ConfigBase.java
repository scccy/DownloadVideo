package com.scccy.videoBase.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)

@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "config_base")
public class ConfigBase extends BaseEntity {
    @TableId(value = "config_base_id", type = IdType.INPUT)
    private Integer configBaseId;

    @TableField(value = "naming")
    private String naming;

    @TableField(value = "`path`")
    private String path;

    @TableField(value = "lyric")
    private Object lyric;

    @TableField(value = "timeout")
    private Integer timeout;

    @TableField(value = "max_retries")
    private Integer maxRetries;

    @TableField(value = "max_connections")
    private Integer maxConnections;

    @TableField(value = "max_counts")
    private Integer maxCounts;

    @TableField(value = "max_tasks")
    private Integer maxTasks;

    @TableField(value = "page_counts")
    private Integer pageCounts;
}