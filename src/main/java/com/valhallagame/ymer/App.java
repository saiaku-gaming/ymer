package com.valhallagame.ymer;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.valhallagame.ymer.security.PersonAuthenticationFilter;
import com.valhallagame.ymer.security.ServerAuthenticationFilter;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public FilterRegistrationBean personAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getPersonAuthenticationFilter());
		registration.addUrlPatterns("/v1/friend/*", "/v1/party/*", "/v1/character/*", "/v1/person/logout",
				"/v1/utils/user-data", "/v1/wardrobe/*");
		registration.setName("personAuthenticationFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean serverAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getServerAuthenticationFilter());
		registration.addUrlPatterns("/v1/server-wardrobe/*, /v1/server-character/*");
		registration.setName("serverAuthenticationFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean(name = "personAuthenticationFilter")
	public Filter getPersonAuthenticationFilter() {
		return new PersonAuthenticationFilter();
	}

	@Bean(name = "serverAuthenticationFilter")
	public Filter getServerAuthenticationFilter() {
		return new ServerAuthenticationFilter();
	}
}
