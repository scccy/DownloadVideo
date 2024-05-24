package com.scccy.videoDownloader.common;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum Global {
    USER_SESSION_KEY("user_login_session"),
    AJAX_SUCCESS("000001"),
    AJAX_URI_ERROR("999998"),
    AJAX_LOGIN_ERR("999997"),
    AJAX_LOGIN_SUCCESS_MESSAGE("登录成功"),
    AJAX_LOGIN_ERR_MESSAGE("您的账号或密码输入错误,请重新输入"),
    AJAX_URI_ERROR_MESSAGE("您的参数不完整,请检查提交参数"),
    AJAX_ADD_USER_ERR("999996"),
    AJAX_ADD_USER_ERR_MESSAGE("添加用户失败,用户已存在"),
    AJAX_ADD_USER_SUCCESS_MESSAGE("用户添加成功"),
    AJAX_OPTION_SUCCESS("操作成功"),
    AJAX_NAV_NO_RULE("333333"),
    AJAX_NAV_NO_RULE_MESSAGE("未对外开放"),
    DOWNTYPE("a2"),
    A2_LINK("http://localhost:6800/jsonrpc"),
    A2_TOKEN("123456"),
    DOWN_PATH("/app/resources"),
    APPTOKEN("123456"),
    BILI_COOKIES(""),
    BILI_MEMBER("false"),
    BILI_BITSTREAM("64"),
    OPEN_PROCESS_HISTORY("false"),
    TIKTOK_COOKIE(""),
    ANALYSIS_SERVER("https://spirit.lifeer.xyz"),
    WALLPAPER_ID("431960"),
    YOUTUBE_COOKIES(""),
    TWITTER_COOKIES("");

    private String value;

    Global(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

