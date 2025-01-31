//package com.scccy.downloadvideo.common.websocket.listener;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.WebSocket;
//import org.springframework.stereotype.Component;
//
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Consumer;
//
//@Slf4j
//@Component
//public class DouyinLiveListener extends BaseWebSocketListener {
//
//    private final Map<String, Consumer<String>> messageHandlers = new HashMap<>();
//
//    public void addMessageHandler(String type, Consumer<String> handler) {
//        messageHandlers.put(type, handler);
//    }
//
//    @Override
//    protected void handleOpen(WebSocket webSocket) {
//        // 发送认证消息
//        JSONObject auth = new JSONObject();
//        auth.put("type", "auth");
//        auth.put("room_id", getRoomId());
//        webSocket.send(auth.toString());
//    }
//
//    @Override
//    protected void handleMessage(String message) {
//        try {
//            JSONObject json = JSON.parseObject(message);
//            String type = json.getString("type");
//
//            Consumer<String> handler = messageHandlers.get(type);
//            if (handler != null) {
//                handler.accept(message);
//            }
//
//        } catch (Exception e) {
//            log.error("Failed to handle message", e);
//        }
//    }
//
//    @Override
//    protected void handleClosing(int code, String reason) {
//        // 清理资源
//    }
//
//    @Override
//    protected void handleClosed(int code, String reason) {
//        // 清理资源
//    }
//
//    @Override
//    protected void handleFailure(Throwable t) {
//        log.error("WebSocket failure", t);
//    }
//}