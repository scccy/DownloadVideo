package com.scccy.videoFlowDouyin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.videoBase.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "info_user_web")
public class InfoUserWeb extends BaseEntity {
    @TableId(value = "sec_user_id", type = IdType.INPUT)
    private String secUserId;

    @TableField(value = "avatar_url")
    private String avatarUrl;

    @TableField(value = "aweme_count")
    private Integer awemeCount;

    @TableField(value = "city")
    private String city;

    @TableField(value = "country")
    private String country;

    @TableField(value = "favoriting_count")
    private Integer favoritingCount;

    @TableField(value = "follower_count")
    private Integer followerCount;

    @TableField(value = "following_count")
    private Integer followingCount;

    @TableField(value = "gender")
    private Integer gender;

    @TableField(value = "ip_location")
    private String ipLocation;

    @TableField(value = "is_ban")
    private Boolean isBan;

    @TableField(value = "is_block")
    private Boolean isBlock;

    @TableField(value = "is_blocked")
    private Boolean isBlocked;

    @TableField(value = "is_star")
    private Boolean isStar;

    @TableField(value = "live_status")
    private Integer liveStatus;

    @TableField(value = "mix_count")
    private Integer mixCount;

    @TableField(value = "mplatform_followers_count")
    private Integer mplatformFollowersCount;

    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "nickname_raw")
    private String nicknameRaw;

    @TableField(value = "room_id")
    private String roomId;

    @TableField(value = "school_name")
    private String schoolName;

    @TableField(value = "short_id")
    private String shortId;

    @TableField(value = "signature")
    private String signature;

    @TableField(value = "signature_raw")
    private String signatureRaw;

    @TableField(value = "total_favorited")
    private Integer totalFavorited;

    @TableField(value = "`uid`")
    private String uid;

    @TableField(value = "unique_id")
    private String uniqueId;

    @TableField(value = "user_age")
    private Integer userAge;

    @TableField(value = "last_aweme_id")
    private String lastAwemeId;
}