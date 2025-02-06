package com.scccy.downloadvideo.common.download.feign;//package com.scccy.downloadvideo.common.download.feign;
//
//import com.scccy.downloadvideo.common.core.config.FeignConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//import com.scccy.downloadvideo.common.core.enums.WebSocketApiEnum;
//import com.scccy.downloadvideo.common.core.annotation.WebSocketApi;
//
//
//@FeignClient(
//    name = "websocket-client",
//    url = "www.baidu.com",
//    configuration = FeignConfig.class
//)
//public interface WebSocketFeignClient {
//
//    @WebSocketApi(WebSocketApiEnum.CONNECT)
//    @GetMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).CONNECT.getEndpoint()}")
//    void connect(@RequestParam("url") String url,
//                @RequestHeader Map<String, String> headers);
//
//    @WebSocketApi(WebSocketApiEnum.SEND)
//    @PostMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).SEND.getEndpoint()}")
//    void sendMessage(@RequestParam("url") String url,
//                    @RequestHeader Map<String, String> headers,
//                    @RequestBody String message);
//
//    @WebSocketApi(WebSocketApiEnum.DISCONNECT)
//    @DeleteMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).DISCONNECT.getEndpoint()}")
//    void disconnect(@RequestParam("url") String url);
//
//    // 抖音直播相关
//    @WebSocketApi(value = WebSocketApiEnum.DOUYIN_LIVE_CONNECT, needAuth = true)
//    @GetMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).DOUYIN_LIVE_CONNECT.getEndpoint()}")
//    void connectLiveRoom(@RequestParam("room_id") String roomId,
//                        @RequestHeader Map<String, String> headers);
//
//    @WebSocketApi(WebSocketApiEnum.DOUYIN_LIVE_DANMAKU)
//    @PostMapping("#{T(com.scccy.downloadvideo.common.core.constant.WebSocketApiEndpoint).DOUYIN_LIVE_DANMAKU.getEndpoint()}")
//    void sendDanmaku(@RequestParam("room_id") String roomId,
//                    @RequestHeader Map<String, String> headers,
//                    @RequestBody String content);
//}