package com.scccy.downloadvideo.common.core.annotation;

import com.scccy.downloadvideo.common.core.enums.DouyinApiEnum;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DouyinApi {
    DouyinApiEnum value();
    
//    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String path() default "";

    RequestMethod method() default RequestMethod.GET;
    boolean needXBogus() default true;
} 