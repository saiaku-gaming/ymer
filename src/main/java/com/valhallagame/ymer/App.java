package com.valhallagame.ymer;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.valhallagame.ymer.security.PersonAuthenticationFilter;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public FilterRegistrationBean personAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getPersonAuthenticationFilter());
		registration.addUrlPatterns("/v1/friend/*", "/v1/party/*", "/v1/character/*");
		registration.setName("personAuthenticationFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean(name = "personAuthenticationFilter")
	public Filter getPersonAuthenticationFilter() {
		return new PersonAuthenticationFilter();
	}
}
