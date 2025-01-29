package com.scccy.videobase.utils;

import com.scccy.videobase.feign.VideoApiClient;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HttpUtils {
    
    private final VideoApiClient videoApiClient;
    
    public String get(String url, Map<String, String> headers) {
        Response response = videoApiClient.get(url, headers);
        return handleResponse(response);
    }
    
    public String post(String url, Map<String, String> headers, Object body) {
        Response response = videoApiClient.post(url, headers, body);
        return handleResponse(response);
    }
    
    public byte[] getBytes(String url, Map<String, String> headers) {
        return videoApiClient.getBytes(url, headers);
    }
    
    private String handleResponse(Response response) {
        // 处理响应，包括错误处理
        if (!response.isSuccessful()) {
            throw new RuntimeException("HTTP request failed with status: " + response.status());
        }
        return response.body().toString();
    }
} 