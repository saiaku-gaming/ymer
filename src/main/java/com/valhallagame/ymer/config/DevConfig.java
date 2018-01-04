package com.valhallagame.ymer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.valhallagame.characterserviceclient.CharacterServiceClient;

@Configuration
@Profile("default")
public class DevConfig {
	@Bean
	public CharacterServiceClient characterServiceClient() {
		return CharacterServiceClient.get();
	}
}
