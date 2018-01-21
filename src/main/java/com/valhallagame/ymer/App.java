package com.valhallagame.ymer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.valhallagame.ymer.security.PersonAuthenticationFilter;
import com.valhallagame.ymer.security.ServerAuthenticationFilter;

@SpringBootApplication
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		if (args.length > 0) {
			if(logger.isInfoEnabled()) {
				logger.info("Args passed in: {}",  Arrays.asList(args).toString());
			}
			// override system properties with local properties

			for (String arg : args) {
				String[] split = arg.split("=");

				if (split.length == 2) {
					System.getProperties().setProperty(split[0], split[1]);
				} else {
					try (InputStream inputStream = new FileInputStream(args[0])) {
						System.getProperties().load(inputStream);
					} catch (IOException e) {
						logger.error("Failed to read input.", e);
					}
				}
			}

		} else {
			logger.info("No args passed to main");
		}

		SpringApplication.run(App.class, args);
	}

	@Bean
	public FilterRegistrationBean personAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getPersonAuthenticationFilter());
		registration.addUrlPatterns("/v1/friend/*", "/v1/party/*", "/v1/character/*", "/v1/person/logout",
				"/v1/person/heartbeat", "/v1/utils/user-data", "/v1/wardrobe/*", "/v1/instance/*", "/v1/chat/*",
				"/v1/feat/*", "/v1/trait/*");
		registration.setName("personAuthenticationFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean serverAuthenticationFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getServerAuthenticationFilter());
		registration.addUrlPatterns("/v1/server-wardrobe/*, /v1/server-character/*", "/v1/server-instance/*",
				"/v1/server-feat/*", "/v1/server-statistics/*", "/v1/server-trait/*");
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
