package com.scccy.downloadvideo.common.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiOperation {
    String value() default "";  // API描述
    String endpoint() default "";  // API端点
    boolean needXBogus() default true;  // 是否需要X-Bogus
} 