package com.valhallagame.ymer;

import com.valhallagame.common.Properties;
import com.valhallagame.ymer.security.PersonAuthenticationFilter;
import com.valhallagame.ymer.security.ServerAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class YmerApp {

	private static final Logger logger = LoggerFactory.getLogger(YmerApp.class);

	public static void main(String[] args) {
		Properties.load(args, logger);
		SpringApplication.run(YmerApp.class, args);
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
		registration.setOrder(1);
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
                "/v1/server-feat/*",
                "/v1/server-statistics/*",
                "/v1/server-trait/*",
                "/v1/server-currency/*"
        );
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
