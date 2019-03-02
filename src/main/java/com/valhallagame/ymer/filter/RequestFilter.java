package com.valhallagame.ymer.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class RequestFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

     @Value("${spring.application.name}")
     private String appName;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            HttpServletResponse response = (HttpServletResponse)servletResponse;

            String requestId = UUID.randomUUID().toString();
            response.addHeader("X-REQUEST-ID", requestId);
            MDC.put("request_id", requestId);
            MDC.put("service_name", appName);
            String clientIp = request.getHeader("X-FORWARDED-FOR");
            MDC.put("request_ip", clientIp != null ? clientIp : request.getRemoteHost());
            if(!"/".equals(request.getRequestURI())) {
                logger.info("Received {} call on {}", request.getMethod(), request.getRequestURI());
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }
}
