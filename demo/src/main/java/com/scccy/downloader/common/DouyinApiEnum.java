package com.scccy.downloader.common;

import lombok.Getter;

@Getter
public enum DouyinApiEnum {
    // 抖音域名 (Douyin Domain)
    DOUYIN_DOMAIN("https://www.douyin.com"),

    // 抖音短域名 (Short Domain)
    IESDOUYIN_DOMAIN("https://www.iesdouyin.com"),

    // 直播域名 (Live Domain)
    LIVE_DOMAIN("https://live.douyin.com"),

    // 直播域名2 (Live Domain 2)
    LIVE_DOMAIN2("https://webcast.amemv.com"),

    // SSO域名 (SSO Domain)
    SSO_DOMAIN("https://sso.douyin.com"),

    // WSS域名 (WSS Domain)
    WEBCAST_WSS_DOMAIN("wss://webcast5-ws-web-lf.douyin.com"),

    // 首页Feed (Home Feed)
    TAB_FEED(DOUYIN_DOMAIN.url + "/aweme/v1/web/tab/feed/"),

    // 用户短信息 (User Short Info)
    USER_SHORT_INFO(DOUYIN_DOMAIN.url + "/aweme/v1/web/im/user/info/"),

    // 用户详细信息 (User Detail Info)
    USER_DETAIL(DOUYIN_DOMAIN.url + "/aweme/v1/web/user/profile/other/"),

    // 作品基本 (Post Basic)
    BASE_AWEME(DOUYIN_DOMAIN.url + "/aweme/v1/web/aweme/"),

    // 用户作品 (User Post)
    USER_POST(DOUYIN_DOMAIN.url + "/aweme/v1/web/aweme/post/"),

    // 定位作品 (Post Local)
    LOCATE_POST(DOUYIN_DOMAIN.url + "/aweme/v1/web/locate/post/"),

    // 搜索作品 (Post Search)
    POST_SEARCH(DOUYIN_DOMAIN.url + "/aweme/v1/web/general/search/single/"),

    // 作品信息 (Post Detail)
    POST_DETAIL(DOUYIN_DOMAIN.url + "/aweme/v1/web/aweme/detail/"),

    // 用户喜欢A (User Like A)
    USER_FAVORITE_A(DOUYIN_DOMAIN.url + "/aweme/v1/web/aweme/favorite/"),

    // 用户喜欢B (User Like B)
    USER_FAVORITE_B(IESDOUYIN_DOMAIN.url + "/web/api/v2/aweme/like/"),

    // 关注用户 (User Following)
    USER_FOLLOWING(DOUYIN_DOMAIN.url + "/aweme/v1/web/user/following/list/"),

    // 粉丝用户 (User Follower)
    USER_FOLLOWER(DOUYIN_DOMAIN.url + "/aweme/v1/web/user/follower/list/"),

    // 合集作品 (Mix Aweme)
    MIX_AWEME(DOUYIN_DOMAIN.url + "/aweme/v1/web/mix/aweme/"),

    // 用户历史 (User History)
    USER_HISTORY(DOUYIN_DOMAIN.url + "/aweme/v1/web/history/read/"),

    // 用户收藏 (User Collection)
    USER_COLLECTION(DOUYIN_DOMAIN.url + "/aweme/v1/web/aweme/listcollection/"),

    // 用户收藏夹 (User Collects)
    USER_COLLECTS(DOUYIN_DOMAIN.url + "/aweme/v1/web/collects/list/"),

    // 用户收藏夹作品 (User Collects Posts)
    USER_COLLECTS_VIDEO(DOUYIN_DOMAIN.url + "/aweme/v1/web/collects/video/list/"),

    // 用户音乐收藏 (User Music Collection)
    USER_MUSIC_COLLECTION(DOUYIN_DOMAIN.url + "/aweme/v1/web/music/listcollection/"),

    // 首页朋友作品 (Friend Feed)
    FRIEND_FEED(DOUYIN_DOMAIN.url + "/aweme/v1/web/familiar/feed/"),

    // 关注用户作品 (Follow Feed)
    FOLLOW_FEED(DOUYIN_DOMAIN.url + "/aweme/v1/web/follow/feed/"),

    // 相关推荐 (Related Feed)
    POST_RELATED(DOUYIN_DOMAIN.url + "/aweme/v1/web/aweme/related/"),

    // 关注用户列表直播 (Follow User Live)
    FOLLOW_USER_LIVE(DOUYIN_DOMAIN.url + "/webcast/web/feed/follow/"),

    // 直播信息接口 (Live Info)
    LIVE_INFO(LIVE_DOMAIN.url + "/webcast/room/web/enter/"),

    // 直播信息接口2 (Live Info 2)
    LIVE_INFO_ROOM_ID(LIVE_DOMAIN2.url + "/webcast/room/reflow/info/"),

    // 直播用户信息 (Live User Info)
    LIVE_USER_INFO(LIVE_DOMAIN.url + "/webcast/user/me/"),

    // 推荐搜索词 (Suggest Words)
    SUGGEST_WORDS(DOUYIN_DOMAIN.url + "/aweme/v1/web/api/suggest_words/"),

    // SSO登录 (SSO Login)
    SSO_LOGIN_GET_QR(SSO_DOMAIN.url + "/get_qrcode/"),

    // 登录检查 (Login Check)
    SSO_LOGIN_CHECK_QR(SSO_DOMAIN.url + "/check_qrconnect/"),

    // 登录确认 (Login Confirm)
    SSO_LOGIN_CHECK_LOGIN(SSO_DOMAIN.url + "/check_login/"),

    // 登录重定向 (Login Redirect)
    SSO_LOGIN_REDIRECT(DOUYIN_DOMAIN.url + "/login/"),

    // 登录回调 (Login Callback)
    SSO_LOGIN_CALLBACK(DOUYIN_DOMAIN.url + "/passport/sso/login/callback/"),

    // 作品评论 (Post Comment)
    POST_COMMENT(DOUYIN_DOMAIN.url + "/aweme/v1/web/comment/list/"),

    // 回复评论 (Reply Comment)
    POST_COMMENT_PUBLISH(DOUYIN_DOMAIN.url + "/aweme/v1/web/comment/publish"),

    // 删除评论 (Delete Comment)
    POST_COMMENT_DELETE(DOUYIN_DOMAIN.url + "/aweme/v1/web/comment/delete/"),

    // 点赞评论 (Like Comment)
    POST_COMMENT_DIGG(DOUYIN_DOMAIN.url + "/aweme/v1/web/comment/digg");

    private final String url;

    DouyinApiEnum(String url) {
        this.url = url;
    }
}
