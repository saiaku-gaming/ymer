package com.valhallagame.ymer.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LastRequestFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(LastRequestFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        if(!"/".equals(request.getRequestURI())) {
            logger.info("Received {} call on {}", request.getMethod(), request.getRequestURI());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
