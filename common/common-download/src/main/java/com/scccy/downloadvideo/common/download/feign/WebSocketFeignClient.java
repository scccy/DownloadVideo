package com.scccy.downloadvideo.common.download.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.scccy.downloadvideo.common.core.enums.WebSocketApiEndpoint;
import com.scccy.downloadvideo.common.core.annotation.WebSocketApi;

@FeignClient(name = "websocket-client", url = "${websocket.client.url:}")
public interface WebSocketFeignClient {
    
    @WebSocketApi(WebSocketApiEndpoint.CONNECT)
    @GetMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).CONNECT.getEndpoint()}")
    void connect(@RequestParam("url") String url, 
                @RequestHeader Map<String, String> headers);
    
    @WebSocketApi(WebSocketApiEndpoint.SEND)
    @PostMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).SEND.getEndpoint()}")
    void sendMessage(@RequestParam("url") String url,
                    @RequestHeader Map<String, String> headers,
                    @RequestBody String message);
    
    @WebSocketApi(WebSocketApiEndpoint.DISCONNECT)
    @DeleteMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).DISCONNECT.getEndpoint()}")
    void disconnect(@RequestParam("url") String url);
    
    // 抖音直播相关
    @WebSocketApi(value = WebSocketApiEndpoint.DOUYIN_LIVE_CONNECT, needAuth = true)
    @GetMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).DOUYIN_LIVE_CONNECT.getEndpoint()}")
    void connectLiveRoom(@RequestParam("room_id") String roomId,
                        @RequestHeader Map<String, String> headers);
    
    @WebSocketApi(WebSocketApiEndpoint.DOUYIN_LIVE_DANMAKU)
    @PostMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).DOUYIN_LIVE_DANMAKU.getEndpoint()}")
    void sendDanmaku(@RequestParam("room_id") String roomId,
                    @RequestHeader Map<String, String> headers,
                    @RequestBody String content);
}