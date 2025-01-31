//package com.scccy.downloadvideo.common.websocket.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.Response;
//import okhttp3.WebSocket;
//import okhttp3.WebSocketListener;
//
//@Slf4j
//public abstract class BaseWebSocketListener extends WebSocketListener {
//
//    @Override
//    public void onOpen(WebSocket webSocket, Response response) {
//        log.info("WebSocket connection opened");
//        handleOpen(webSocket);
//    }
//
//    @Override
//    public void onMessage(WebSocket webSocket, String text) {
//        log.debug("Received message: {}", text);
//        handleMessage(text);
//    }
//
//    @Override
//    public void onClosing(WebSocket webSocket, int code, String reason) {
//        log.info("WebSocket closing: {} - {}", code, reason);
//        handleClosing(code, reason);
//    }
//
//    @Override
//    public void onClosed(WebSocket webSocket, int code, String reason) {
//        log.info("WebSocket closed: {} - {}", code, reason);
//        handleClosed(code, reason);
//    }
//
//    @Override
//    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//        log.error("WebSocket failure", t);
//        handleFailure(t);
//    }
//
//    protected abstract void handleOpen(WebSocket webSocket);
//    protected abstract void handleMessage(String message);
//    protected abstract void handleClosing(int code, String reason);
//    protected abstract void handleClosed(int code, String reason);
//    protected abstract void handleFailure(Throwable t);
//}