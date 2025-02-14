package com.scccy.downloadvideo.platform.douyin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 抖音下载配置
 */
@Schema(description = "抖音下载配置")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName(value = "douyin_base_download_config")
public class DouyinBaseDownloadConfig extends BaseRequestModel {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @TableField(value = "encryption")
    @Schema(description = "加密参数")
    private String encryption;

    // 请求相关配置
    @TableField(value = "wss_domain")
    @Schema(description = "WebSocket服务域名")
    private String wssDomain;

    @TableField(value = "wss_port")
    @Schema(description = "WebSocket服务端口")
    private Integer wssPort;

    @TableField(value = "wss_verify")
    @Schema(description = "WebSocket验证开关")
    private Boolean wssVerify;

    // msToken相关配置
    @TableField(value = "ms_token_url")
    @Schema(description = "msToken获取地址")
    private String msTokenUrl;

    @TableField(value = "ms_token_magic")
    @Schema(description = "msToken魔数")
    private Long msTokenMagic;

    @TableField(value = "ms_token_version")
    @Schema(description = "msToken版本")
    private Integer msTokenVersion;

    @TableField(value = "ms_token_data_type")
    @Schema(description = "msToken数据类型")
    private Integer msTokenDataType;

    @TableField(value = "ms_token_str_data")
    @Schema(description = "msToken字符串数据")
    private String msTokenStrData;

    // ttwid相关配置
    @TableField(value = "ttwid_url")
    @Schema(description = "ttwid获取地址")
    private String ttwidUrl;

    @TableField(value = "ttwid_data")
    @Schema(description = "ttwid数据")
    private String ttwidData;

    // webid相关配置
    @TableField(value = "webid_url")
    @Schema(description = "webid获取地址")
    private String webidUrl;

    @TableField(value = "webid_app_id")
    @Schema(description = "webid应用ID")
    private Integer webidAppId;

    @TableField(value = "webid_sdk_version")
    @Schema(description = "webid SDK版本")
    private String webidSdkVersion;

    @TableField(value = "webid_device_platform")
    @Schema(description = "webid设备平台")
    private String webidDevicePlatform;

    // 代理配置
    @TableField(value = "proxies_http")
    @Schema(description = "HTTP代理地址")
    private String proxiesHttp;

    @TableField(value = "proxies_https")
    @Schema(description = "HTTPS代理地址")
    private String proxiesHttps;

    private static final long serialVersionUID = 1L;


}