package com.valhallagame.ymer.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ServerAuthenticationFilter extends GenericFilterBean {

	@Value("${server.secret}")
	private String serverSecret;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		final String token = request.getHeader("token");
		
		if (token == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} else {
			if(token.equals(serverSecret)) {
				chain.doFilter(req, res);
			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		}
	}

}
