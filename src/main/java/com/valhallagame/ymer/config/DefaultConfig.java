package com.valhallagame.ymer.config;

import com.valhallagame.actionbarserviceclient.ActionbarServiceClient;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.currencyserviceclient.CurrencyServiceClient;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.recipeserviceclient.RecipeServiceClient;
import com.valhallagame.statisticsserviceclient.StatisticsServiceClient;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import com.valhallagame.ymer.GenerateMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Profile("default")
public class DefaultConfig {

	@Bean
	public CharacterServiceClient characterServiceClient() {
		return CharacterServiceClient.get();
	}

	@Bean
	public PersonServiceClient personServiceClient() {
		return PersonServiceClient.get();
	}

	@Bean
	public ChatServiceClient chatServiceClient() {
		return ChatServiceClient.get();
	}

	@Bean
	public FeatServiceClient featServiceClient() {
		return FeatServiceClient.get();
	}

	@Bean
	public InstanceServiceClient instanceServiceClient() {
		return InstanceServiceClient.get();
	}

	@Bean
	public PartyServiceClient partyServiceClient() {
		return PartyServiceClient.get();
	}

	@Bean
	public NotificationServiceClient notificationServiceClient() {
		return NotificationServiceClient.get();
	}

	@Bean
	public WardrobeServiceClient wardrobeServiceClient() {
		return WardrobeServiceClient.get();
	}

	@Bean
	public FriendServiceClient friendServiceClient() {
		return FriendServiceClient.get();
	}

	@Bean
	public StatisticsServiceClient statisticsServiceClient() {
		return StatisticsServiceClient.get();
	}

	@Bean
	public TraitServiceClient traitServiceClient() {
		return TraitServiceClient.get();
	}

	@Bean
	public ActionbarServiceClient actionbarServiceClient() {
		return ActionbarServiceClient.get();
	}

	@Bean
	public CurrencyServiceClient currencyServiceClient() {
		return CurrencyServiceClient.get();
	}

    @Bean
    public RecipeServiceClient recipeServiceClient() {
        return RecipeServiceClient.get();
    }
}
