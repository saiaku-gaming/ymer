package com.valhallagame.ymer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.valhallagame.actionbarserviceclient.ActionbarServiceClient;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.statisticsserviceclient.StatisticsServiceClient;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;

@Configuration
@Profile("development")
public class DevConfig {
	@Bean
	public CharacterServiceClient characterServiceClient() {
		CharacterServiceClient
				.init("http://dev-character-service:" + DefaultServicePortMappings.CHARACTER_SERVICE_PORT);
		return CharacterServiceClient.get();
	}

	@Bean
	public PersonServiceClient personServiceClient() {
		PersonServiceClient.init("http://dev-person-service:" + DefaultServicePortMappings.PERSON_SERVICE_PORT);
		return PersonServiceClient.get();
	}

	@Bean
	public ChatServiceClient chatServiceClient() {
		ChatServiceClient.init("http://dev-chat-service:" + DefaultServicePortMappings.CHAT_SERVICE_PORT);
		return ChatServiceClient.get();
	}

	@Bean
	public FeatServiceClient featServiceClient() {
		FeatServiceClient.init("http://dev-feat-service:" + DefaultServicePortMappings.FEAT_SERVICE_PORT);
		return FeatServiceClient.get();
	}

	@Bean
	public InstanceServiceClient instanceServiceClient() {
		InstanceServiceClient.init("http://dev-instance-service:" + DefaultServicePortMappings.INSTANCE_SERVICE_PORT);
		return InstanceServiceClient.get();
	}

	@Bean
	public PartyServiceClient partyServiceClient() {
		PartyServiceClient.init("http://dev-party-service:" + DefaultServicePortMappings.PARTY_SERVICE_PORT);
		return PartyServiceClient.get();
	}

	@Bean
	public NotificationServiceClient notificationServiceClient() {
		NotificationServiceClient
				.init("http://dev-notification-service:" + DefaultServicePortMappings.NOTIFICATION_SERVICE_PORT);
		return NotificationServiceClient.get();
	}

	@Bean
	public WardrobeServiceClient wardrobeServiceClient() {
		WardrobeServiceClient.init("http://dev-wardrobe-service:" + DefaultServicePortMappings.WARDROBE_SERVICE_PORT);
		return WardrobeServiceClient.get();
	}

	@Bean
	public FriendServiceClient friendServiceClient() {
		FriendServiceClient.init("http://dev-friend-service:" + DefaultServicePortMappings.FRIEND_SERVICE_PORT);
		return FriendServiceClient.get();
	}

	@Bean
	public StatisticsServiceClient statisticsServiceClient() {
		StatisticsServiceClient
				.init("http://dev-statistics-service:" + DefaultServicePortMappings.STATISTICS_SERVICE_PORT);
		return StatisticsServiceClient.get();
	}

	@Bean
	public TraitServiceClient traitServiceClient() {
		TraitServiceClient.init("http://dev-trait-service:" + DefaultServicePortMappings.TRAIT_SERVICE_PORT);
		return TraitServiceClient.get();
	}

	@Bean
	public ActionbarServiceClient actionbarServiceClient() {
		ActionbarServiceClient
				.init("http://dev-actionbar-service:" + DefaultServicePortMappings.ACTIONBAR_SERVICE_PORT);
		return ActionbarServiceClient.get();
	}
}
