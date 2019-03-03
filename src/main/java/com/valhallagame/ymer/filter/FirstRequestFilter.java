package com.valhallagame.ymer.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class FirstRequestFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(FirstRequestFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            HttpServletResponse response = (HttpServletResponse)servletResponse;

            String requestId = UUID.randomUUID().toString();
            response.addHeader("X-REQUEST-ID", requestId);
            MDC.put("request_id", requestId);
            String clientIp = request.getHeader("X-FORWARDED-FOR");
            MDC.put("request_ip", clientIp != null ? clientIp : request.getRemoteHost());

            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove("request_id");
            MDC.remove("request_ip");
        }
    }
}
