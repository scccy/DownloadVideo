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
@TableName(value = "info_video")
public class InfoVideo extends BaseEntity {
    @TableId(value = "aweme_id", type = IdType.INPUT)
    private String awemeId;

    @TableField(value = "api_status_code")
    private String apiStatusCode;

    @TableField(value = "aweme_type")
    private String awemeType;

    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "nickname_raw")
    private String nicknameRaw;

    @TableField(value = "sec_user_id")
    private String secUserId;

    @TableField(value = "short_id")
    private String shortId;

    @TableField(value = "`uid`")
    private String uid;

    @TableField(value = "unique_id")
    private String uniqueId;

    @TableField(value = "can_comment")
    private String canComment;

    @TableField(value = "can_forward")
    private String canForward;

    @TableField(value = "can_share")
    private String canShare;

    @TableField(value = "can_show_comment")
    private String canShowComment;

    @TableField(value = "comment_gid")
    private String commentGid;

    @TableField(value = "create_time")
    private String createTime;

    @TableField(value = "`desc`")
    private String desc;

    @TableField(value = "desc_raw")
    private String descRaw;

    @TableField(value = "duration")
    private String duration;

    @TableField(value = "is_ads")
    private String isAds;

    @TableField(value = "is_story")
    private String isStory;

    @TableField(value = "is_top")
    private String isTop;

    @TableField(value = "video_bit_rate")
    private String videoBitRate;

    @TableField(value = "video_play_addr")
    private String videoPlayAddr;

    @TableField(value = "images")
    private String images;

    @TableField(value = "animated_cover")
    private String animatedCover;

    @TableField(value = "cover")
    private String cover;

    @TableField(value = "part_see")
    private String partSee;

    @TableField(value = "private_status")
    private String privateStatus;

    @TableField(value = "is_delete")
    private String isDelete;

    @TableField(value = "is_prohibited")
    private String isProhibited;

    @TableField(value = "is_long_video")
    private String isLongVideo;

    @TableField(value = "media_type")
    private String mediaType;

    @TableField(value = "mix_desc")
    private String mixDesc;

    @TableField(value = "mix_desc_raw")
    private String mixDescRaw;

    @TableField(value = "mix_create_time")
    private String mixCreateTime;

    @TableField(value = "mix_id")
    private String mixId;

    @TableField(value = "mix_name")
    private String mixName;

    @TableField(value = "mix_pic_type")
    private String mixPicType;

    @TableField(value = "mix_type")
    private String mixType;

    @TableField(value = "mix_share_url")
    private String mixShareUrl;

    @TableField(value = "mix_update_time")
    private String mixUpdateTime;

    @TableField(value = "is_commerce_music")
    private String isCommerceMusic;

    @TableField(value = "is_original")
    private String isOriginal;

    @TableField(value = "is_original_sound")
    private String isOriginalSound;

    @TableField(value = "is_pgc")
    private String isPgc;

    @TableField(value = "music_author")
    private String musicAuthor;

    @TableField(value = "music_author_raw")
    private String musicAuthorRaw;

    @TableField(value = "music_author_deleted")
    private String musicAuthorDeleted;

    @TableField(value = "music_duration")
    private String musicDuration;

    @TableField(value = "music_id")
    private String musicId;

    @TableField(value = "music_mid")
    private String musicMid;

    @TableField(value = "pgc_author")
    private String pgcAuthor;

    @TableField(value = "pgc_author_raw")
    private String pgcAuthorRaw;

    @TableField(value = "pgc_author_title")
    private String pgcAuthorTitle;

    @TableField(value = "pgc_author_title_raw")
    private String pgcAuthorTitleRaw;

    @TableField(value = "pgc_music_type")
    private String pgcMusicType;

    @TableField(value = "music_status")
    private String musicStatus;

    @TableField(value = "music_owner_handle")
    private String musicOwnerHandle;

    @TableField(value = "music_owner_handle_raw")
    private String musicOwnerHandleRaw;

    @TableField(value = "music_owner_id")
    private String musicOwnerId;

    @TableField(value = "music_owner_nickname")
    private String musicOwnerNickname;

    @TableField(value = "music_owner_nickname_raw")
    private String musicOwnerNicknameRaw;

    @TableField(value = "music_play_url")
    private String musicPlayUrl;

    @TableField(value = "`position`")
    private String position;

    @TableField(value = "region")
    private String region;

    @TableField(value = "seo_ocr_content")
    private String seoOcrContent;

    @TableField(value = "allow_douplus")
    private String allowDouplus;

    @TableField(value = "download_setting")
    private String downloadSetting;

    @TableField(value = "allow_share")
    private String allowShare;

    @TableField(value = "admire_count")
    private Integer admireCount;

    @TableField(value = "collect_count")
    private Integer collectCount;

    @TableField(value = "comment_count")
    private Integer commentCount;

    @TableField(value = "digg_count")
    private Integer diggCount;

    @TableField(value = "share_count")
    private Integer shareCount;

    @TableField(value = "hashtag_ids")
    private String hashtagIds;

    @TableField(value = "hashtag_names")
    private String hashtagNames;
}