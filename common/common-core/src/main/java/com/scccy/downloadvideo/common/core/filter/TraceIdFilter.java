package com.scccy.downloadvideo.common.core.filter;

import com.scccy.downloadvideo.common.core.utils.TraceIdUtil;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(filterName = "traceIdFilter", urlPatterns = "/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceIdFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            // 尝试从请求头获取traceId
            String traceId = httpRequest.getHeader("X-Trace-Id");
            if (traceId == null || traceId.isEmpty()) {
                traceId = TraceIdUtil.generateTraceId();
            }
            TraceIdUtil.setTraceId(traceId);
            chain.doFilter(request, response);
        } finally {
            TraceIdUtil.removeTraceId();
        }
    }
} 