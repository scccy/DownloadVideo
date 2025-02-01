package com.scccy.downloadvideo.common.core.annotation;

import com.scccy.downloadvideo.common.core.enums.WebSocketApiEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebSocketApi {
    WebSocketApiEnum value();
    boolean needAuth() default false;
} 