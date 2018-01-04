package com.valhallagame.ymer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.common.DefaultServicePortMappings;

@Configuration
@Profile("production")
public class ProdConfig {
	@Bean
	public CharacterServiceClient characterServiceClient() {
		CharacterServiceClient.init("http://character-service:" + DefaultServicePortMappings.CHARACTER_SERVICE_PORT);
		return CharacterServiceClient.get();
	}
}
