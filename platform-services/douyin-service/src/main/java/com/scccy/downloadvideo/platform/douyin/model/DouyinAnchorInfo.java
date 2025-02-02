package com.scccy.downloadvideo.platform.douyin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.downloadvideo.common.core.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "douyin_anchor_info")
public class DouyinAnchorInfo extends BaseEntity implements Serializable {
    @TableId(value = "sec_user_id", type = IdType.AUTO)
    @Schema(description="")
    private String secUserId;

    @TableField(value = "avatar_url")
    @Schema(description="")
    private String avatarUrl;

    @TableField(value = "aweme_count")
    @Schema(description="")
    private Integer awemeCount;

    @TableField(value = "city")
    @Schema(description="")
    private String city;

    @TableField(value = "country")
    @Schema(description="")
    private String country;

    @TableField(value = "favoriting_count")
    @Schema(description="")
    private Integer favoritingCount;

    @TableField(value = "follower_count")
    @Schema(description="")
    private Integer followerCount;

    @TableField(value = "following_count")
    @Schema(description="")
    private Integer followingCount;

    @TableField(value = "gender")
    @Schema(description="")
    private Integer gender;

    @TableField(value = "ip_location")
    @Schema(description="")
    private String ipLocation;

    @TableField(value = "is_ban")
    @Schema(description="")
    private Boolean isBan;

    @TableField(value = "is_block")
    @Schema(description="")
    private Boolean isBlock;

    @TableField(value = "is_blocked")
    @Schema(description="")
    private Boolean isBlocked;

    @TableField(value = "is_star")
    @Schema(description="")
    private Boolean isStar;

    @TableField(value = "live_status")
    @Schema(description="")
    private Integer liveStatus;

    @TableField(value = "mix_count")
    @Schema(description="")
    private Integer mixCount;

    @TableField(value = "mplatform_followers_count")
    @Schema(description="")
    private Integer mplatformFollowersCount;

    @TableField(value = "nickname")
    @Schema(description="")
    private String nickname;

    @TableField(value = "nickname_raw")
    @Schema(description="")
    private String nicknameRaw;

    @TableField(value = "room_id")
    @Schema(description="")
    private String roomId;

    @TableField(value = "school_name")
    @Schema(description="")
    private String schoolName;

    @TableField(value = "short_id")
    @Schema(description="")
    private String shortId;

    @TableField(value = "signature")
    @Schema(description="")
    private String signature;

    @TableField(value = "signature_raw")
    @Schema(description="")
    private String signatureRaw;

    @TableField(value = "total_favorited")
    @Schema(description="")
    private Integer totalFavorited;

    @TableField(value = "`uid`")
    @Schema(description="")
    private String uid;

    @TableField(value = "unique_id")
    @Schema(description="")
    private String uniqueId;

    @TableField(value = "user_age")
    @Schema(description="")
    private Integer userAge;

    @TableField(value = "last_aweme_id")
    @Schema(description="")
    private String lastAwemeId;

    private static final long serialVersionUID = 1L;
}