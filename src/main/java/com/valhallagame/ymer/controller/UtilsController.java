package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.message.Character;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.friendserviceclient.model.FriendsData;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.partyserviceclient.model.PartyAndInvites;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping(path = "/v1/utils")
public class UtilsController {

	private static ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> ping() {
		return JS.message(HttpStatus.OK, "Pong");
	}

	@RequestMapping(path = "/user-data", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> userData(@RequestAttribute("username") String username) throws IOException {
		ObjectNode out = mapper.createObjectNode();

		// CHARACTER

		RestResponse<Character> selectedCharacter = CharacterServiceClient.get().getSelectedCharacter(username);
		Optional<Character> optCharacter = selectedCharacter.getResponse();
		String displayCharacterName = optCharacter.map(x -> x.getDisplayCharacterName()).orElse("");
		out.set("displayCharacterName", new TextNode(displayCharacterName));

		// USERNAME
		out.set("username", new TextNode(username));

		// ACHIEVEMENTS
		ObjectNode achievementsObj = mapper.createObjectNode();
		ArrayNode achievementsArr = mapper.createArrayNode();
		achievementsObj.set("achievements", achievementsArr);
		out.set("achievementData", achievementsObj);

		// FRIENDS

		FriendServiceClient friendServiceClient = FriendServiceClient.get();
		RestResponse<FriendsData> friendsDataResp;
		try {
			friendsDataResp = friendServiceClient.getFriendsData(username);
			if (friendsDataResp.isOk()) {
				FriendsData friendsData = friendsDataResp.getResponse().get();
				out.set("friendsData", mapper.valueToTree(friendsData));
			}
		} catch (IOException e2) {
			System.err.println("NO FRIENDS RUNNING");
		}

		Character character = optCharacter.get();
		List<EquippedItem> equippedItems = new ArrayList<>();
		equippedItems.add(new EquippedItem("Mainhand", character.getMainhandArmament(), null));
		equippedItems.add(new EquippedItem("Offhand", character.getOffHandArmament(), null));
		equippedItems.add(new EquippedItem("Chest", null, character.getChestItem()));

		ObjectNode itemHandlerObj = mapper.createObjectNode();
		itemHandlerObj.set("equippedItems", mapper.valueToTree(equippedItems));
		out.set("itemHandlerData", itemHandlerObj);

		// NOTIFICATIONS
		ObjectNode notificationsObj = mapper.createObjectNode();
		ArrayNode notificationsArr = mapper.createArrayNode();
		notificationsObj.set("notifications", notificationsArr);
		out.set("notificationData", notificationsObj);

		PartyServiceClient partyServiceClient = PartyServiceClient.get();
		RestResponse<PartyAndInvites> partyAndInvites;
		try {
			partyAndInvites = partyServiceClient.getPartyAndInvites(username);
			if (partyAndInvites.isOk()) {
				PartyAndInvites pai = partyAndInvites.getResponse().get();
				ObjectNode partyObj = mapper.createObjectNode();
				partyObj.set("party", mapper.valueToTree(pai.getParty().orElse(null)));
				partyObj.set("receivedInvites", mapper.valueToTree(pai.getReceivedInvites()));
				out.set("partyData", partyObj);
			}

		} catch (IOException e1) {
			System.err.println("NO PARTY RUNNING");
		}

		out.set("skillData", mapper.createObjectNode());

		// WARDROBE

		WardrobeServiceClient wardrobeServiceClient = WardrobeServiceClient.get();
		RestResponse<List<String>> wardrobeItems;
		try {
			wardrobeItems = wardrobeServiceClient.getWardrobeItems(displayCharacterName.toLowerCase());
			if (wardrobeItems.isOk()) {
				ObjectNode wardrobeObj = mapper.createObjectNode();
				wardrobeObj.set("wardrobe", mapper.valueToTree(wardrobeItems.getResponse().get()));
				out.set("wardrobeData", wardrobeObj);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("NO WARDROBE RUNNING");
		}

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
