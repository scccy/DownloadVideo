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
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "douyin_video_info")
public class DouyinVideoInfo extends BaseEntity implements Serializable {
    @TableId(value = "aweme_id", type = IdType.INPUT)
    @Schema(description = "")
    private String awemeId;

    @TableField(value = "api_status_code")
    @Schema(description = "")
    private String apiStatusCode;

    @TableField(value = "aweme_type")
    @Schema(description = "")
    private String awemeType;

    @TableField(value = "nickname")
    @Schema(description = "")
    private String nickname;

    @TableField(value = "nickname_raw")
    @Schema(description = "")
    private String nicknameRaw;

    @TableField(value = "sec_user_id")
    @Schema(description = "")
    private String secUserId;

    @TableField(value = "short_id")
    @Schema(description = "")
    private String shortId;

    @TableField(value = "`uid`")
    @Schema(description = "")
    private String uid;

    @TableField(value = "unique_id")
    @Schema(description = "")
    private String uniqueId;

    @TableField(value = "can_comment")
    @Schema(description = "")
    private String canComment;

    @TableField(value = "can_forward")
    @Schema(description = "")
    private String canForward;

    @TableField(value = "can_share")
    @Schema(description = "")
    private String canShare;

    @TableField(value = "can_show_comment")
    @Schema(description = "")
    private String canShowComment;

    @TableField(value = "comment_gid")
    @Schema(description = "")
    private String commentGid;

    @TableField(value = "`desc`")
    @Schema(description = "")
    private String desc;

    @TableField(value = "desc_raw")
    @Schema(description = "")
    private String descRaw;

    @TableField(value = "duration")
    @Schema(description = "")
    private String duration;

    @TableField(value = "is_ads")
    @Schema(description = "")
    private String isAds;

    @TableField(value = "is_story")
    @Schema(description = "")
    private String isStory;

    @TableField(value = "is_top")
    @Schema(description = "")
    private String isTop;

    @TableField(value = "video_bit_rate")
    @Schema(description = "")
    private String videoBitRate;

    @TableField(value = "video_play_addr")
    @Schema(description = "")
    private String videoPlayAddr;

    @TableField(value = "images")
    @Schema(description = "")
    private String images;

    @TableField(value = "animated_cover")
    @Schema(description = "")
    private String animatedCover;

    @TableField(value = "cover")
    @Schema(description = "")
    private String cover;

    @TableField(value = "part_see")
    @Schema(description = "")
    private String partSee;

    @TableField(value = "private_status")
    @Schema(description = "")
    private String privateStatus;

    @TableField(value = "is_delete")
    @Schema(description = "")
    private String isDelete;

    @TableField(value = "is_prohibited")
    @Schema(description = "")
    private String isProhibited;

    @TableField(value = "is_long_video")
    @Schema(description = "")
    private String isLongVideo;

    @TableField(value = "media_type")
    @Schema(description = "")
    private String mediaType;

    @TableField(value = "mix_desc")
    @Schema(description = "")
    private String mixDesc;

    @TableField(value = "mix_desc_raw")
    @Schema(description = "")
    private String mixDescRaw;

    @TableField(value = "mix_create_time")
    @Schema(description = "")
    private String mixCreateTime;

    @TableField(value = "mix_id")
    @Schema(description = "")
    private String mixId;

    @TableField(value = "mix_name")
    @Schema(description = "")
    private String mixName;

    @TableField(value = "mix_pic_type")
    @Schema(description = "")
    private String mixPicType;

    @TableField(value = "mix_type")
    @Schema(description = "")
    private String mixType;

    @TableField(value = "mix_share_url")
    @Schema(description = "")
    private String mixShareUrl;

    @TableField(value = "mix_update_time")
    @Schema(description = "")
    private String mixUpdateTime;

    @TableField(value = "is_commerce_music")
    @Schema(description = "")
    private String isCommerceMusic;

    @TableField(value = "is_original")
    @Schema(description = "")
    private String isOriginal;

    @TableField(value = "is_original_sound")
    @Schema(description = "")
    private String isOriginalSound;

    @TableField(value = "is_pgc")
    @Schema(description = "")
    private String isPgc;

    @TableField(value = "music_author")
    @Schema(description = "")
    private String musicAuthor;

    @TableField(value = "music_author_raw")
    @Schema(description = "")
    private String musicAuthorRaw;

    @TableField(value = "music_author_deleted")
    @Schema(description = "")
    private String musicAuthorDeleted;

    @TableField(value = "music_duration")
    @Schema(description = "")
    private String musicDuration;

    @TableField(value = "music_id")
    @Schema(description = "")
    private String musicId;

    @TableField(value = "music_mid")
    @Schema(description = "")
    private String musicMid;

    @TableField(value = "pgc_author")
    @Schema(description = "")
    private String pgcAuthor;

    @TableField(value = "pgc_author_raw")
    @Schema(description = "")
    private String pgcAuthorRaw;

    @TableField(value = "pgc_author_title")
    @Schema(description = "")
    private String pgcAuthorTitle;

    @TableField(value = "pgc_author_title_raw")
    @Schema(description = "")
    private String pgcAuthorTitleRaw;

    @TableField(value = "pgc_music_type")
    @Schema(description = "")
    private String pgcMusicType;

    @TableField(value = "music_status")
    @Schema(description = "")
    private String musicStatus;

    @TableField(value = "music_owner_handle")
    @Schema(description = "")
    private String musicOwnerHandle;

    @TableField(value = "music_owner_handle_raw")
    @Schema(description = "")
    private String musicOwnerHandleRaw;

    @TableField(value = "music_owner_id")
    @Schema(description = "")
    private String musicOwnerId;

    @TableField(value = "music_owner_nickname")
    @Schema(description = "")
    private String musicOwnerNickname;

    @TableField(value = "music_owner_nickname_raw")
    @Schema(description = "")
    private String musicOwnerNicknameRaw;

    @TableField(value = "music_play_url")
    @Schema(description = "")
    private String musicPlayUrl;

    @TableField(value = "`position`")
    @Schema(description = "")
    private String position;

    @TableField(value = "region")
    @Schema(description = "")
    private String region;

    @TableField(value = "seo_ocr_content")
    @Schema(description = "")
    private String seoOcrContent;

    @TableField(value = "allow_douplus")
    @Schema(description = "")
    private String allowDouplus;

    @TableField(value = "download_setting")
    @Schema(description = "")
    private String downloadSetting;

    @TableField(value = "allow_share")
    @Schema(description = "")
    private String allowShare;

    @TableField(value = "admire_count")
    @Schema(description = "")
    private String admireCount;

    @TableField(value = "collect_count")
    @Schema(description = "")
    private String collectCount;

    @TableField(value = "comment_count")
    @Schema(description = "")
    private String commentCount;

    @TableField(value = "digg_count")
    @Schema(description = "")
    private String diggCount;

    @TableField(value = "share_count")
    @Schema(description = "")
    private String shareCount;

    @TableField(value = "hashtag_ids")
    @Schema(description = "")
    private String hashtagIds;

    @TableField(value = "hashtag_names")
    @Schema(description = "")
    private String hashtagNames;

    private static final long serialVersionUID = 1L;
}