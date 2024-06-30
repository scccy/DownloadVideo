//package com.scccy.videoBase.websocket;
//
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHttpHeaders;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.handler.AbstractWebSocketHandler;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class WebSocketCrawler {
//
//    private WebSocketSession session;
//    private final WebSocketHttpHeaders headers;
//    private final String websocketUri;
//    private final Map<String, Runnable> callbacks;
//
//    public WebSocketCrawler(WebSocketHttpHeaders headers, String websocketUri, Map<String, Runnable> callbacks) {
//        this.headers = headers;
//        this.websocketUri = websocketUri;
//        this.callbacks = callbacks;
//    }
//
//    @PostConstruct
//    public void connect() {
//        StandardWebSocketClient client = new StandardWebSocketClient();
//        client.doHandshake(new WebSocketHandler(), headers, URI.create(websocketUri));
//    }
//
//    private class WebSocketHandler extends AbstractWebSocketHandler {
//
//        @Override
//        public void afterConnectionEstablished(WebSocketSession session) {
//            WebSocketCrawler.this.session = session;
//            log.info("WebSocket connection established");
//        }
//
//        @Override
//        protected void handleTextMessage(WebSocketSession session, TextMessage message) {
//            log.info("Received message: {}", message.getPayload());
//            // Handle message or call appropriate callback
//        }
//
//        @Override
//        public void handleTransportError(WebSocketSession session, Throwable exception) {
//            log.error("WebSocket transport error", exception);
//            // Handle error
//        }
//
//        @Override
//        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//            log.info("WebSocket connection closed: {}", status);
//            // Handle connection close
//        }
//    }
//
//    public void sendMessage(String message) throws IOException {
//        if (session != null && session.isOpen()) {
//            session.sendMessage(new TextMessage(message));
//        } else {
//            log.warn("WebSocket session is not open");
//        }
//    }
//
//    public void close() throws IOException {
//        if (session != null) {
//            session.close();
//        }
//    }
//}
