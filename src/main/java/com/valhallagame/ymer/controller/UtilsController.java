package com.valhallagame.ymer.controller;

import java.io.IOException;
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
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.partyserviceclient.model.PartyAndInvites;

@Controller
@RequestMapping(path = "/v1/util")
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
		
		//CHARACTER
		
		RestResponse<Character> selectedCharacter = CharacterServiceClient.get().getSelectedCharacter(username);
		Optional<Character> response = selectedCharacter.getResponse();
		out.set("selectedCharacterName", new TextNode(response.map(x -> x.getCharacterName()).orElse("")));
		
		//USERNAME
		out.set("username", new TextNode(username));
		
		//ACHIEVEMENTS
		ObjectNode achievementsObj = mapper.createObjectNode();
		ArrayNode achievementsArr = mapper.createArrayNode();
		achievementsObj.set("achievements", achievementsArr);
		out.set("achievementData", achievementsObj);

		//FRIENDS
		ObjectNode friendsObj = mapper.createObjectNode();
		friendsObj.set("receivedInvites", mapper.createArrayNode());
		friendsObj.set("sentInvites", mapper.createArrayNode());
		friendsObj.set("friends", mapper.createArrayNode());
		out.set("friendsData", friendsObj);
		
		ObjectNode itemHandlerObj = mapper.createObjectNode();
		itemHandlerObj.set("equippedItems", mapper.createArrayNode());
		out.set("itemHandlerData", itemHandlerObj);
		
		//NOTIFICATIONS
		ObjectNode notificationsObj = mapper.createObjectNode();
		ArrayNode notificationsArr = mapper.createArrayNode();
		notificationsObj.set("notifications", notificationsArr);
		out.set("notificationData", notificationsArr);

		
		PartyServiceClient partyServiceClient = PartyServiceClient.get();
		RestResponse<PartyAndInvites> partyAndInvites = partyServiceClient.getPartyAndInvites(username);
		if(partyAndInvites.isOk()) {
			PartyAndInvites pai = partyAndInvites.getResponse().get();
			ObjectNode partyObj = mapper.createObjectNode();
			partyObj.set("party", mapper.valueToTree(pai.getParty()));
			partyObj.set("receivedInvites", mapper.valueToTree(pai.getInvites()));
			out.set("partyData", partyObj);
		}
		
		out.set("skillData", new TextNode("NOT IMPL"));
		
		//WARDROBE
		ObjectNode wardrobeObj = mapper.createObjectNode();
		ArrayNode wardrobeArr = mapper.createArrayNode();
		wardrobeObj.set("wardrobe", wardrobeArr);
		out.set("wardrobeData", wardrobeObj);
	
		return JS.message(HttpStatus.OK, out);
	}
}
