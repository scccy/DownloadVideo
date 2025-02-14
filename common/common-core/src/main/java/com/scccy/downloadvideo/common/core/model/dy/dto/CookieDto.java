package com.scccy.downloadvideo.common.core.model.dy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CookieDto {
    private String acNonce;
    private String acSignature;
    private String ttwid;
    private String uifidTemp;
    private String _$DouyinCom222;// FIXME check this code
    private Integer deviceWebCpuCore;
    private Integer deviceWebMemorySize;
    private Boolean hevcSupported;
    private Integer dySwidth;
    private Integer dySheight;
    private StreamRecommendFeedParamsDTO streamRecommendFeedParams;
    private String sVWebId;
    private String csrfSessionId;
    private String fpk1;
    private String fpk2;
    private String strategyABtestKey;
    private FORCELOGINDTO forceLogin;
    private VolumeInfoDTO volumeInfo;
    private String xgplayerUserId;
    private String passportCsrfToken;
    private String passportCsrfTokenDefault;
    private String homeCanAddDy2Desktop;
    private String securityMc1SSdkCryptSdk;
    private String bdTicketGuardClientData;
    private Integer bdTicketGuardClientWebDomain;
    private String sdkSourceInfo;
    private String bitEnv;
    private String guluSourceRes;
    private String passportAuthMixState;
    private String uifid;
    private String passportAssistUser;
    private String nMh;
    private String ssoUidTt;
    private String ssoUidTtSs;
    private String toutiaoSsoUser;
    private String toutiaoSsoUserSs;
    private String sidUcpSsoV1;
    private String ssidUcpSsoV1;
    private String securityMc1SSdkSignDataKeySso;
    private String securityMc1SSdkCertKey;
    private Long loginTime;
    private String passportAuthStatus;
    private String passportAuthStatusSs;
    private String uidTt;
    private String uidTtSs;
    private String sidTt;
    private String sessionid;
    private String sessionidSs;
    private Boolean isStaffUser;
    private String publishBadgeShowInfo;
    private Integer isDashUser;
    private Integer bdTicketCryptDoamin;
    private String bdTicketCryptCookie;
    private String securityMc1SSdkSignDataKeyWebProtect;
    private Integer securityServerDataStatus;
    private List<?> selfTabRedDotControl;
    private String bizTraceId;
    private String storeregion;
    private String storeregionsrc;
    private String securityMc1SSdkSignDataKeyLogin;
    private String odinTt;
    private Boolean passportFeBeatingStatus;
    private String sidGuard;
    private String sidUcpV1;
    private String ssidUcpV1;
    private String streamPlayerStatusParams;
    private Boolean isDouyinActive;
    private Double xgDeviceScore;
    private Integer myRd;
}
