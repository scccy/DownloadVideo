package com.scccy.downloadDy.config;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import com.scccy.downloadDy.handlerInterceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//         添加拦截器，并指定拦截的路径
//        指定拦截目标
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/user/login") // 指定拦截器的URL路径匹配模式
//                .excludePathPatterns("/login", "/register") // 指定不拦截的路径，如登录或注册页面
//                .order(1)//拦截顺序
        ;
    }

    //开启全局跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowedMethods("*");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 开启驼峰转换
//        config.setSerializeConfig(SerializeConfig.globalInstance);
//        config.setParserFeatures(Feature.AllowUnQuotedFieldNames, Feature.AllowSingleQuotes);
//        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        converters.add(0, converter);
    }

}
