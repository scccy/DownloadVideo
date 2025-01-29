package com.scccy.videobase.feign;

import com.scccy.videobase.config.FeignConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import feign.Param;

import java.util.Map;

@FeignClient(name = "video-api", configuration = FeignConfig.class)
public interface VideoApiClient {
    
    @GetMapping
    Response get(@Param("url") String url, @RequestHeader Map<String, String> headers);
    
    @PostMapping
    Response post(@Param("url") String url, @RequestHeader Map<String, String> headers, @RequestBody Object body);
    
    @GetMapping
    byte[] getBytes(@Param("url") String url, @RequestHeader Map<String, String> headers);
} 