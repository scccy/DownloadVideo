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
 * Bark应用配置表
 */
@Schema(description="Bark应用配置表")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bark_app_config")
public class BarkAppConfig extends BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description="主键ID")
    private Long id;

    /**
     * 密钥
     */
    @TableField(value = "key_value")
    @Schema(description="密钥")
    private String keyValue;

    /**
     * 令牌
     */
    @TableField(value = "token")
    @Schema(description="令牌")
    private String token;

    /**
     * 模式
     */
    @TableField(value = "`mode`")
    @Schema(description="模式")
    private String mode;

    /**
     * URL
     */
    @TableField(value = "url")
    @Schema(description="URL")
    private String url;

    /**
     * 重试次数
     */
    @TableField(value = "retry")
    @Schema(description="重试次数")
    private Integer retry;

    /**
     * 铃声
     */
    @TableField(value = "ringtones")
    @Schema(description="铃声")
    private Integer ringtones;

    /**
     * 图标
     */
    @TableField(value = "icon")
    @Schema(description="图标")
    private String icon;

    /**
     * 级别
     */
    @TableField(value = "`level`")
    @Schema(description="级别")
    private String level;

    /**
     * 音量
     */
    @TableField(value = "volume")
    @Schema(description="音量")
    private Integer volume;

    /**
     * 是否呼叫
     */
    @TableField(value = "`call`")
    @Schema(description="是否呼叫")
    private Boolean call;

    /**
     * 是否归档
     */
    @TableField(value = "is_archive")
    @Schema(description="是否归档")
    private Boolean isArchive;

    /**
     * 声音
     */
    @TableField(value = "sound")
    @Schema(description="声音")
    private String sound;

    /**
     * 自动复制
     */
    @TableField(value = "auto_copy")
    @Schema(description="自动复制")
    private Boolean autoCopy;

    /**
     * 标题
     */
    @TableField(value = "title")
    @Schema(description="标题")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "body")
    @Schema(description="内容")
    private String body;

    /**
     * 复制内容
     */
    @TableField(value = "`copy`")
    @Schema(description="复制内容")
    private String copy;

    /**
     * 分组
     */
    @TableField(value = "group_name")
    @Schema(description="分组")
    private String groupName;

    /**
     * 角标
     */
    @TableField(value = "badge")
    @Schema(description="角标")
    private Integer badge;

    private static final long serialVersionUID = 1L;
}