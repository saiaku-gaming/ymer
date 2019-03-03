package com.valhallagame.ymer;

import com.valhallagame.common.Properties;
import com.valhallagame.ymer.filter.FirstRequestFilter;
import com.valhallagame.ymer.filter.LastRequestFilter;
import com.valhallagame.ymer.security.PersonAuthenticationFilter;
import com.valhallagame.ymer.security.ServerAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class YmerApp {

	private static final Logger logger = LoggerFactory.getLogger(YmerApp.class);

	private static String appName;

	@Value("${spring.application.name}")
	public void setAppName(String appName) {
		YmerApp.appName = appName;
	}

	public static void main(String[] args) {
		Properties.load(args, logger);
		SpringApplication.run(YmerApp.class, args);

		MDC.put("service_name", appName);
	}

	@Bean
	public FilterRegistrationBean personAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getPersonAuthenticationFilter());
        registration.addUrlPatterns(
                "/v1/friend/*",
                "/v1/party/*",
                "/v1/character/*",
                "/v1/person/logout",
                "/v1/person/heartbeat",
                "/v1/utils/user-data",
                "/v1/wardrobe/*",
                "/v1/instance/*",
                "/v1/chat/*",
                "/v1/feat/*",
                "/v1/trait/*",
                "/v1/actionbar/*",
                "/v1/currency/*",
                "/v1/recipe/*"
        );
		registration.setName("personAuthenticationFilter");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public FilterRegistrationBean serverAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getServerAuthenticationFilter());
        registration.addUrlPatterns(
				"/v1/server-character/*",
				"/v1/server-bank/*",
                "/v1/server-recipe/*",
                "/v1/server-wardrobe/*",
                "/v1/server-character/*",
                "/v1/server-instance/*",
				"/v1/server-inventory/*",
                "/v1/server-feat/*",
                "/v1/server-statistics/*",
                "/v1/server-trait/*",
                "/v1/server-currency/*"
        );
		registration.setName("serverAuthenticationFilter");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public FilterRegistrationBean firstRequestFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getFirstRequestFilter());
		registration.addUrlPatterns(
				"/*",
				"/**"
		);
		registration.setName("firstRequestFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean lastRequestFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getLastRequestFilter());
		registration.addUrlPatterns(
				"/*",
				"/**"
		);
		registration.setName("lastRequestFilter");
		registration.setOrder(3);
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

	@Bean(name = "firstRequestFilter")
	public Filter getFirstRequestFilter() {
		return new FirstRequestFilter();
	}

	@Bean(name = "lastRequestFilter")
	public Filter getLastRequestFilter() {
		return new LastRequestFilter();
	}
}
