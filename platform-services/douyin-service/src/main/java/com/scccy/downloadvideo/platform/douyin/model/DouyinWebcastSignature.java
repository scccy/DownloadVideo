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
 * 抖音直播间签名表
 */
@Schema(description = "抖音直播间签名表")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "douyin_webcast_signature")
public class DouyinWebcastSignature extends BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 直播间ID
     */
    @TableField(value = "room_id")
    @Schema(description = "直播间ID")
    private String roomId;

    /**
     * 用户唯一标识
     */
    @TableField(value = "user_unique_id")
    @Schema(description = "用户唯一标识")
    private String userUniqueId;

    /**
     * 生成的签名
     */
    @TableField(value = "signature")
    @Schema(description = "生成的签名")
    private String signature;

    /**
     * 签名过期时间
     */
    @TableField(value = "expire_time")
    @Schema(description = "签名过期时间")
    private Date expireTime;

    /**
     * 是否已使用 (0:未使用 1:已使用)
     */
    @TableField(value = "used")
    @Schema(description = "是否已使用 (0:未使用 1:已使用)")
    private Boolean used;

    private static final long serialVersionUID = 1L;
}