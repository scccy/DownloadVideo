//package com.scccy.downloadvideo.common.websocket.client;
//
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.Request;
//import okhttp3.WebSocketListener;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Component
//public class WebSocketClient {
//
//    private final WebSocketProperties properties;
//    private final OkHttpClient okHttpClient;
//    private final Map<String, WebSocket> webSockets = new ConcurrentHashMap<>();
//
//    public WebSocketClient(WebSocketProperties properties, OkHttpClient okHttpClient) {
//        this.properties = properties;
//        this.okHttpClient = okHttpClient;
//    }
//
//    public void connect(String url, WebSocketListener listener) {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        WebSocket webSocket = okHttpClient.newWebSocket(request, listener);
//        webSockets.put(url, webSocket);
//    }
//
//    public void send(String url, String message) {
//        WebSocket webSocket = webSockets.get(url);
//        if (webSocket != null) {
//            webSocket.send(message);
//        }
//    }
//
//    public void close(String url) {
//        WebSocket webSocket = webSockets.remove(url);
//        if (webSocket != null) {
//            webSocket.close(1000, "Normal closure");
//        }
//    }
//}