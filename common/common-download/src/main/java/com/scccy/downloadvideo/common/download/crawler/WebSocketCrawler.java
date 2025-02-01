//package com.scccy.downloadvideo.common.download.crawler;
//
//import com.scccy.downloadvideo.common.core.exception.ServiceException;
//import com.scccy.downloadvideo.common.download.feign.WebSocketFeignClient;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.reactive.socket.WebSocketHandler;
//import org.springframework.web.reactive.socket.WebSocketSession;
//import org.springframework.web.reactive.socket.WebSocketMessage;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//
//@Slf4j
//public abstract class  WebSocketCrawler implements WebSocketHandler {
//
//    protected final WebSocketFeignClient webSocketClient;
//    protected final String url;
//    protected final Map<String, String> headers;
//    protected final int timeout;
//
//    public WebSocketCrawler(WebSocketFeignClient webSocketClient,
//                          String url,
//                          Map<String, String> headers,
//                          int timeout) {
//        this.webSocketClient = webSocketClient;
//        this.url = url;
//        this.headers = headers;
//        this.timeout = timeout;
//    }
//
//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//        return session.receive()
//            .doOnSubscribe(sub -> {
//                log.info("WebSocket connection established");
//                onOpen();
//            })
//            .doOnNext(message -> {
//                String payload = message.getPayloadAsText();
//                log.debug("Received message: {}", payload);
//                onMessage(payload);
//            })
//            .doOnError(error -> {
//                log.error("WebSocket error", error);
//                onError(new Exception(error));
//            })
//            .doOnComplete(() -> {
//                log.info("WebSocket connection closed");
//                onClose(1000, "Normal closure", true);
//            })
//            .then();
//    }
//
//    public void connect() {
//        try {
//            webSocketClient.connect(url, headers);
//            log.info("WebSocket connected to: {}", url);
//        } catch (Exception e) {
//            log.error("Failed to connect WebSocket", e);
//            throw new ServiceException("Failed to connect WebSocket: " + e.getMessage());
//        }
//    }
//
//    public void disconnect() {
//        try {
//            webSocketClient.disconnect(url);
//            log.info("WebSocket disconnected from: {}", url);
//        } catch (Exception e) {
//            log.error("Failed to disconnect WebSocket", e);
//            throw new ServiceException("Failed to disconnect WebSocket: " + e.getMessage());
//        }
//    }
//
//    public void sendMessage(String message) {
//        try {
//            webSocketClient.sendMessage(url, headers, message);
//            log.debug("Sent message: {}", message);
//        } catch (Exception e) {
//            log.error("Failed to send message", e);
//            throw new ServiceException("Failed to send message: " + e.getMessage());
//        }
//    }
//
//    protected abstract void onOpen();
//    protected abstract void onMessage(String message);
//    protected abstract void onClose(int code, String reason, boolean remote);
//    protected abstract void onError(Exception ex);
//}