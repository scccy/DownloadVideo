package com.scccy.downloadvideo.common.core.annotation;

import com.scccy.downloadvideo.common.core.enums.WebSocketApiEndpoint;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebSocketApi {
    WebSocketApiEndpoint value();
    boolean needAuth() default false;
} 