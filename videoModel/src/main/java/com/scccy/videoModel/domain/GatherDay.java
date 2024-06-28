package com.scccy.videoModel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.videoModel.vo.DownloadReqVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
    * 抖音视频数据表
    */
@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "gather_day")
public class GatherDay implements Serializable {
    /**
     * 视频 ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户 ID
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 收藏数量
     */
    @TableField(value = "collect_count")
    private String collectCount;

    /**
     * 收藏时间
     */
    @TableField(value = "collection_time")
    private Date collectionTime;

    /**
     * 评论数量
     */
    @TableField(value = "comment_count")
    private Integer commentCount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建时间戳
     */
    @TableField(value = "create_timestamp")
    private Long createTimestamp;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 点赞数量
     */
    @TableField(value = "digg_count")
    private String diggCount;

    /**
     * 下载链接
     */
    @TableField(value = "downloads")
    private String downloads;

    /**
     * 视频时长
     */
    @TableField(value = "duration")
    private String duration;

    /**
     * 动态封面
     */
    @TableField(value = "dynamic_cover")
    private String dynamicCover;

    /**
     * 额外信息
     */
    @TableField(value = "extra")
    private String extra;

    /**
     * 视频高度
     */
    @TableField(value = "height")
    private Integer height;

    /**
     * 标记
     */
    @TableField(value = "mark")
    private String mark;

    /**
     * 音乐作者
     */
    @TableField(value = "music_author")
    private String musicAuthor;

    /**
     * 音乐标题
     */
    @TableField(value = "music_title")
    private String musicTitle;

    /**
     * 音乐链接
     */
    @TableField(value = "music_url")
    private String musicUrl;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 原始封面
     */
    @TableField(value = "origin_cover")
    private String originCover;

    /**
     * 比例
     */
    @TableField(value = "ratio")
    private String ratio;

    /**
     * 用户唯一标识
     */
    @TableField(value = "sec_uid")
    private String secUid;

    /**
     * 分享数量
     */
    @TableField(value = "share_count")
    private Integer shareCount;

    /**
     * 分享链接
     */
    @TableField(value = "share_url")
    private String shareUrl;

    /**
     * 短 ID
     */
    @TableField(value = "short_id")
    private String shortId;

    /**
     * 签名
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 标签 1
     */
    @TableField(value = "tag_1")
    private String tag1;

    /**
     * 标签 2
     */
    @TableField(value = "tag_2")
    private String tag2;

    /**
     * 标签 3
     */
    @TableField(value = "tag_3")
    private String tag3;

    /**
     * 文本额外信息
     */
    @TableField(value = "text_extra")
    private String textExtra;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 唯一 ID
     */
    @TableField(value = "unique_id")
    private String uniqueId;

    /**
     * URI
     */
    @TableField(value = "uri")
    private String uri;

    /**
     * 用户年龄
     */
    @TableField(value = "user_age")
    private Integer userAge;

    /**
     * 视频宽度
     */
    @TableField(value = "width")
    private Integer width;

    private static final long serialVersionUID = 1L;
    public DownloadReqVo toDownloadReqVo() {
        DownloadReqVo downloadReqVo = new DownloadReqVo();
        downloadReqVo.setNickname(this.getNickname());
        downloadReqVo.setId(this.getId());
        downloadReqVo.setDesc(this.getDesc());
        downloadReqVo.setDynamicCover(this.getDynamicCover());
        downloadReqVo.setOriginCover(this.getOriginCover());
        downloadReqVo.setDownloads(this.getDownloads());
        return downloadReqVo;
    }
}