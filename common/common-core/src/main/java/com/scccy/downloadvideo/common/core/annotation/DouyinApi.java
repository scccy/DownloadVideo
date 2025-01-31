package com.scccy.downloadvideo.common.core.annotation;

import com.scccy.downloadvideo.common.core.enums.DouyinApiEndpoint;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DouyinApi {
    DouyinApiEndpoint value();
    
//    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String path() default "";

    RequestMethod method() default RequestMethod.GET;
    boolean needXBogus() default true;
} 