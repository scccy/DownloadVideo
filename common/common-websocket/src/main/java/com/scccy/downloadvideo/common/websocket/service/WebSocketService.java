package com.scccy.downloadvideo.common.websocket.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.websocket.client.WebSocketClient;
import com.scccy.downloadvideo.common.websocket.listener.DouyinLiveListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebSocketService {
    
    private final WebSocketClient webSocketClient;
    private final DouyinLiveListener liveListener;
    
    public WebSocketService(WebSocketClient webSocketClient, DouyinLiveListener liveListener) {
        this.webSocketClient = webSocketClient;
        this.liveListener = liveListener;
        
        // 注册消息处理器
        liveListener.addMessageHandler("chat", this::handleChat);
        liveListener.addMessageHandler("gift", this::handleGift);
        liveListener.addMessageHandler("like", this::handleLike);
    }
    
    public void connectToLiveRoom(String roomId) {
        String url = String.format("wss://webcast5-ws-web-hl.douyin.com/webcast/im/push/v2/?room_id=%s", roomId);
        webSocketClient.connect(url, liveListener);
    }
    
    private void handleChat(String message) {
        JSONObject json = JSON.parseObject(message);
        // 处理聊天消息
    }
    
    private void handleGift(String message) {
        JSONObject json = JSON.parseObject(message);
        // 处理礼物消息
    }
    
    private void handleLike(String message) {
        JSONObject json = JSON.parseObject(message);
        // 处理点赞消息
    }
} 