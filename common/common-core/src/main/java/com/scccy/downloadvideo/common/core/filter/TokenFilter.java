package com.scccy.downloadvideo.common.core.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class TokenFilter implements WebFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        // 实现你的token验证逻辑
        if (path.startsWith("/user/login")) {
            // 处理token验证
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }
} 