package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.valhallagame.featserviceclient.message.GetFeatsParameter;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.friendserviceclient.model.FriendsData;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.model.RelevantDungeonData;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.partyserviceclient.model.PartyAndInvitesData;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.traitserviceclient.message.TraitData;
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

	@Autowired
	private CharacterServiceClient characterServiceClient;

	@Autowired
	private InstanceServiceClient instanceServiceClient;

	@Autowired
	private WardrobeServiceClient wardrobeServiceClient;

	@Autowired
	private PartyServiceClient partyServiceClient;

	@Autowired
	private FriendServiceClient friendServiceClient;

	@Autowired
	private FeatServiceClient featServiceClient;

	@Autowired
	private TraitServiceClient traitServiceClient;

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

		out.set("username", new TextNode(username));

		CharacterData character = getCharacterData(username);
		out.set("displayCharacterName", new TextNode(character.getDisplayCharacterName()));
		out.set("characterName", new TextNode(character.getCharacterName()));
		
		out.set("itemHandlerData", getItemHanderData(character));
		out.set("wardrobeData", getWardrobeData(username));
		out.set("traitData", getTraitData(username));
		out.set("featData", getFeatsData(character));
		out.set("friendsData", getFriendsData(username));
		out.set("partyData", getPartyData(username));
		out.set("instanceData", getInstanceData(username, input));
		
		return JS.message(HttpStatus.OK, out);
	}

	private CharacterData getCharacterData(String username) throws IOException {
		RestResponse<CharacterData> characterResp = characterServiceClient.getSelectedCharacter(username);
		Optional<CharacterData> optCharacter = characterResp.get();
		if (optCharacter.isPresent()) {
			return optCharacter.get();
		} else {
			throw new IOException(characterResp.getErrorMessage());
		}
	}

	private ObjectNode getInstanceData(String username, VersionParameter input) throws IOException {
		RestResponse<RelevantDungeonData> relevantDungeonsResp = instanceServiceClient.getRelevantDungeons(username,
				input.getVersion());
		Optional<RelevantDungeonData> relevantDungeonDataOpt = relevantDungeonsResp.get();
		if (relevantDungeonDataOpt.isPresent()) {
			RelevantDungeonData relevantDungeonData = relevantDungeonDataOpt.get();
			ObjectNode partyObj = mapper.createObjectNode();
			partyObj.set("relevantDungeons", mapper.valueToTree(relevantDungeonData.getRelevantDungeons()));
			partyObj.set("queuePlacements", mapper.valueToTree(relevantDungeonData.getQueuePlacements()));
			return partyObj;
		} else {
			throw new IOException(relevantDungeonsResp.getErrorMessage());
		}
	}

	private ObjectNode getPartyData(String username) throws IOException {
		RestResponse<PartyAndInvitesData> partyAndInvitesResp = partyServiceClient.getPartyAndInvites(username);
		Optional<PartyAndInvitesData> partyAndInvitesOpt = partyAndInvitesResp.get();
		if (partyAndInvitesOpt.isPresent()) {
			PartyAndInvitesData pai = partyAndInvitesOpt.get();
			ObjectNode partyObj = mapper.createObjectNode();
			partyObj.set("party", mapper.valueToTree(pai.getParty().orElse(null)));
			partyObj.set("receivedInvites", mapper.valueToTree(pai.getReceivedInvites()));
			return partyObj;
		} else {
			throw new IOException(partyAndInvitesResp.getErrorMessage());
		}
	}

	private JsonNode getFriendsData(String username) throws IOException {
		RestResponse<FriendsData> friendsDataResp = friendServiceClient.getFriendData(username);
		Optional<FriendsData> friendsDataOpt = friendsDataResp.get();
		if (friendsDataOpt.isPresent()) {
			FriendsData friendsData = friendsDataOpt.get();
			return mapper.valueToTree(friendsData);
		} else {
			throw new IOException(friendsDataResp.getErrorMessage());
		}
	}

	private ObjectNode getFeatsData(CharacterData character) throws IOException {
		RestResponse<List<String>> featResp = featServiceClient
				.getFeats(new GetFeatsParameter(character.getCharacterName()));
		Optional<List<String>> featsOpt = featResp.get();
		if (featsOpt.isPresent()) {
			ObjectNode featObj = mapper.createObjectNode();
			featObj.set("feats", mapper.valueToTree(featsOpt.get()));
			return featObj;
		} else {
			throw new IOException(featResp.getErrorMessage());
		}
	}

	private ObjectNode getTraitData(String username) throws IOException {
		RestResponse<List<TraitData>> traitsResp = traitServiceClient.getTraits(username);
		Optional<List<TraitData>> traitsOpt = traitsResp.get();
		if (traitsOpt.isPresent()) {
			ObjectNode traitsObj = mapper.createObjectNode();
			traitsObj.set("traits", mapper.valueToTree(traitsOpt.get()));
			return traitsObj;
		} else {
			throw new IOException(traitsResp.getErrorMessage());
		}
	}

	private ObjectNode getWardrobeData(String username) throws IOException {
		RestResponse<List<String>> wardrobeItemsResp = wardrobeServiceClient.getWardrobeItems(username);
		Optional<List<String>> wardrobeItemOpt = wardrobeItemsResp.get();
		if (wardrobeItemOpt.isPresent()) {
			ObjectNode wardrobeObj = mapper.createObjectNode();
			wardrobeObj.set("wardrobe", mapper.valueToTree(wardrobeItemOpt.get()));
			return wardrobeObj;
		} else {
			throw new IOException(wardrobeItemsResp.getErrorMessage());
		}
	}

	private ObjectNode getItemHanderData(CharacterData character) {
		List<EquippedItem> equippedItems = new ArrayList<>();
		equippedItems.add(new EquippedItem("MAINHAND", character.getMainhandArmament(), null));
		equippedItems.add(new EquippedItem("OFFHAND", character.getOffHandArmament(), null));
		equippedItems.add(new EquippedItem("CHEST", null, character.getChestItem()));

		ObjectNode itemHandlerObj = mapper.createObjectNode();
		itemHandlerObj.set("equippedItems", mapper.valueToTree(equippedItems));
		return itemHandlerObj;
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
