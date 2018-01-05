package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.model.CharacterData;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.friendserviceclient.model.FriendsData;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.model.RelevantDungeonData;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.partyserviceclient.model.PartyAndInvitesData;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import com.valhallagame.ymer.message.VersionParameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping(path = "/v1/utils")
public class UtilsController {

	private static final Logger logger = LoggerFactory.getLogger(UtilsController.class);

	private static ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> ping() {
		return JS.message(HttpStatus.OK, "Pong");
	}

	@RequestMapping(path = "/pong", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> pong() {
		return JS.message(HttpStatus.OK, "Ping");
	}

	@RequestMapping(path = "/user-data", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> userData(@RequestAttribute("username") String username,
			@RequestBody VersionParameter input) throws IOException {
		ObjectNode out = mapper.createObjectNode();

		// CHARACTER

		RestResponse<CharacterData> selectedCharacter = CharacterServiceClient.get().getSelectedCharacter(username);
		Optional<CharacterData> optCharacter = selectedCharacter.get();
		if (optCharacter.isPresent()) {
			String displayCharacterName = optCharacter.map(CharacterData::getDisplayCharacterName).orElse("");
			out.set("displayCharacterName", new TextNode(displayCharacterName));

			// ITEMS

			CharacterData character = optCharacter.get();
			List<EquippedItem> equippedItems = new ArrayList<>();
			equippedItems.add(new EquippedItem("MAINHAND", character.getMainhandArmament(), null));
			equippedItems.add(new EquippedItem("OFFHAND", character.getOffHandArmament(), null));
			equippedItems.add(new EquippedItem("CHEST", null, character.getChestItem()));

			ObjectNode itemHandlerObj = mapper.createObjectNode();
			itemHandlerObj.set("equippedItems", mapper.valueToTree(equippedItems));
			out.set("itemHandlerData", itemHandlerObj);

			// WARDROBE

			WardrobeServiceClient wardrobeServiceClient = WardrobeServiceClient.get();

			try {
				RestResponse<List<String>> wardrobeItemsResp = wardrobeServiceClient
						.getWardrobeItems(displayCharacterName.toLowerCase());
				Optional<List<String>> wardrobeItemOpt = wardrobeItemsResp.get();
				if (wardrobeItemOpt.isPresent()) {
					ObjectNode wardrobeObj = mapper.createObjectNode();
					wardrobeObj.set("wardrobe", mapper.valueToTree(wardrobeItemOpt.get()));
					out.set("wardrobeData", wardrobeObj);
				}
			} catch (IOException e) {
				logger.error("NO WARDROBE RUNNING");
			}

			// FEATS

			FeatServiceClient featsServiceClient = FeatServiceClient.get();

			try {
				RestResponse<List<String>> featResp = featsServiceClient.getFeats(displayCharacterName.toLowerCase());
				Optional<List<String>> featsOpt = featResp.get();
				if (featsOpt.isPresent()) {
					ObjectNode featObj = mapper.createObjectNode();
					featObj.set("feats", mapper.valueToTree(featsOpt.get()));
					out.set("featData", featObj);
				}
			} catch (IOException e) {
				logger.error("NO FEATS RUNNING");
			}
		} else {
			logger.error("Missing character!!!");
		}

		// USERNAME
		out.set("username", new TextNode(username));

		// ACH -- deprecated
		ObjectNode achievementsObj = mapper.createObjectNode();
		ArrayNode achievementsArr = mapper.createArrayNode();
		achievementsObj.set("achievements", achievementsArr);
		out.set("achievementData", achievementsObj);

		// FRIENDS

		FriendServiceClient friendServiceClient = FriendServiceClient.get();
		RestResponse<FriendsData> friendsDataResp;
		try {
			friendsDataResp = friendServiceClient.getFriendData(username);
			Optional<FriendsData> friendsDataOpt = friendsDataResp.get();
			if (friendsDataOpt.isPresent()) {
				FriendsData friendsData = friendsDataOpt.get();
				out.set("friendsData", mapper.valueToTree(friendsData));
			}
		} catch (IOException e2) {
			logger.error("NO FRIENDS RUNNING");
		}

		// NOTIFICATIONS
		ObjectNode notificationsObj = mapper.createObjectNode();
		ArrayNode notificationsArr = mapper.createArrayNode();
		notificationsObj.set("notifications", notificationsArr);
		out.set("notificationData", notificationsObj);

		// PARTY
		PartyServiceClient partyServiceClient = PartyServiceClient.get();

		RestResponse<PartyAndInvitesData> partyAndInvites = partyServiceClient.getPartyAndInvites(username);
		Optional<PartyAndInvitesData> partyAndInvitesOpt = partyAndInvites.get();
		if (partyAndInvitesOpt.isPresent()) {
			PartyAndInvitesData pai = partyAndInvitesOpt.get();
			ObjectNode partyObj = mapper.createObjectNode();
			partyObj.set("party", mapper.valueToTree(pai.getParty().orElse(null)));
			partyObj.set("receivedInvites", mapper.valueToTree(pai.getReceivedInvites()));
			out.set("partyData", partyObj);
		}

		// INSTANCE
		InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();
		RestResponse<RelevantDungeonData> relevantDungeons = instanceServiceClient.getRelevantDungeons(username,
				input.getVersion());
		Optional<RelevantDungeonData> relevantDungeonDataOpt = relevantDungeons.get();
		if (relevantDungeonDataOpt.isPresent()) {
			RelevantDungeonData relevantDungeonData = relevantDungeonDataOpt.get();
			ObjectNode partyObj = mapper.createObjectNode();
			partyObj.set("relevantDungeons", mapper.valueToTree(relevantDungeonData.getRelevantDungeons()));
			partyObj.set("queuePlacements", mapper.valueToTree(relevantDungeonData.getQueuePlacements()));
			out.set("instanceData", partyObj);
		}

		out.set("skillData", mapper.createObjectNode());
		return JS.message(HttpStatus.OK, out);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private class EquippedItem {
		private String itemSlot;
		private String armament;
		private String armor;
	}
}
