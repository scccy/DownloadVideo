package com.scccy.downloadvideo.common.download.feign;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "download-client", url = "${download.client.url:}")
public interface DownloadFeignClient {
    
    @GetMapping("/download")
    ResponseEntity<String> download(@RequestParam("url") String url,
                                  @RequestHeader Map<String, String> headers);
    
    @GetMapping("/download/bytes")
    ResponseEntity<byte[]> download2Byte(@RequestParam("url") String url,
                                       @RequestHeader Map<String, String> headers);
    
    @PostMapping("/download")
    ResponseEntity<String> downloadWithPost(@RequestParam("url") String url,
                                          @RequestHeader Map<String, String> headers,
                                          @RequestBody Map<String, Object> params);
    
    @GetMapping("/download/range")
    ResponseEntity<String> downloadWithRange(@RequestParam("url") String url,
                                           @RequestHeader Map<String, String> headers,
                                           @RequestHeader("Range") String range);

//    @PostMapping
//    ResponseEntity<byte[]> downloadWithPost2Byte(@RequestParam("url") String url,
//                                            @RequestHeader Map<String, String> headers,
//                                            @RequestBody Map<String, Object> params);
//
//    @GetMapping
//    ResponseEntity<byte[]> downloadWithRange2Byte(@RequestParam("url") String url,
//                                             @RequestHeader Map<String, String> headers,
//                                             @RequestHeader("Range") String range);
//
//    @GetMapping("/user/likes")
//    ResponseEntity<JSONObject> fetchUserLikes(@RequestParam("userId") String userId, @RequestParam("page") int page);

} 