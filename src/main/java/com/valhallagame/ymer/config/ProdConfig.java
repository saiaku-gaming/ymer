package com.valhallagame.ymer.config;

import com.valhallagame.actionbarserviceclient.ActionbarServiceClient;
import com.valhallagame.bankserviceclient.BankServiceClient;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.common.DefaultServicePortMappings;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"production", "development"})
public class ProdConfig {
	@Bean
	public CharacterServiceClient characterServiceClient() {
		CharacterServiceClient.init("http://character-service:" + DefaultServicePortMappings.CHARACTER_SERVICE_PORT);
		return CharacterServiceClient.get();
	}

	@Bean
	public PersonServiceClient personServiceClient() {
		PersonServiceClient.init("http://person-service:" + DefaultServicePortMappings.PERSON_SERVICE_PORT);
		return PersonServiceClient.get();
	}

	@Bean
	public ChatServiceClient chatServiceClient() {
		ChatServiceClient.init("http://chat-service:" + DefaultServicePortMappings.CHAT_SERVICE_PORT);
		return ChatServiceClient.get();
	}

	@Bean
	public FeatServiceClient featServiceClient() {
		FeatServiceClient.init("http://feat-service:" + DefaultServicePortMappings.FEAT_SERVICE_PORT);
		return FeatServiceClient.get();
	}

	@Bean
	public InstanceServiceClient instanceServiceClient() {
		InstanceServiceClient.init("http://instance-service:" + DefaultServicePortMappings.INSTANCE_SERVICE_PORT);
		return InstanceServiceClient.get();
	}

	@Bean
	public PartyServiceClient partyServiceClient() {
		PartyServiceClient.init("http://party-service:" + DefaultServicePortMappings.PARTY_SERVICE_PORT);
		return PartyServiceClient.get();
	}

	@Bean
	public NotificationServiceClient notificationServiceClient() {
		NotificationServiceClient
				.init("http://notification-service:" + DefaultServicePortMappings.NOTIFICATION_SERVICE_PORT);
		return NotificationServiceClient.get();
	}

	@Bean
	public WardrobeServiceClient wardrobeServiceClient() {
		WardrobeServiceClient.init("http://wardrobe-service:" + DefaultServicePortMappings.WARDROBE_SERVICE_PORT);
		return WardrobeServiceClient.get();
	}

	@Bean
	public FriendServiceClient friendServiceClient() {
		FriendServiceClient.init("http://friend-service:" + DefaultServicePortMappings.FRIEND_SERVICE_PORT);
		return FriendServiceClient.get();
	}

	@Bean
	public StatisticsServiceClient statisticsServiceClient() {
		StatisticsServiceClient.init("http://statistics-service:" + DefaultServicePortMappings.STATISTICS_SERVICE_PORT);
		return StatisticsServiceClient.get();
	}

	@Bean
	public TraitServiceClient traitServiceClient() {
		TraitServiceClient.init("http://trait-service:" + DefaultServicePortMappings.TRAIT_SERVICE_PORT);
		return TraitServiceClient.get();
	}

	@Bean
	public ActionbarServiceClient actionbarServiceClient() {
		ActionbarServiceClient
				.init("http://actionbar-service:" + DefaultServicePortMappings.ACTIONBAR_SERVICE_PORT);
		return ActionbarServiceClient.get();
	}

	@Bean
	public CurrencyServiceClient currencyServiceClient() {
		CurrencyServiceClient
				.init("http://currency-service:" + DefaultServicePortMappings.CURRENCY_SERVICE_PORT);
		return CurrencyServiceClient.get();
	}

    @Bean
    public RecipeServiceClient recipeServiceClient() {
        RecipeServiceClient
                .init("http://recipe-service:" + DefaultServicePortMappings.RECIPE_SERVICE_PORT);
        return RecipeServiceClient.get();
    }

	@Bean
	public BankServiceClient bankServiceClient() {
		BankServiceClient
				.init("http://bank-service:" + DefaultServicePortMappings.BANK_SERVICE_PORT);
		return BankServiceClient.get();
	}
}
