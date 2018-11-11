package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.valhallagame.actionbarserviceclient.ActionbarServiceClient;
import com.valhallagame.actionbarserviceclient.model.ActionbarWrapper;
import com.valhallagame.bankserviceclient.BankServiceClient;
import com.valhallagame.bankserviceclient.message.BankItemResult;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.model.CharacterData;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.currencyserviceclient.CurrencyServiceClient;
import com.valhallagame.currencyserviceclient.message.CurrencyResult;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.featserviceclient.message.GetFeatsParameter;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.friendserviceclient.model.FriendsData;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.model.RelevantDungeonData;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.partyserviceclient.model.PartyAndInvitesData;
import com.valhallagame.recipeserviceclient.RecipeServiceClient;
import com.valhallagame.recipeserviceclient.model.RecipeData;
import com.valhallagame.statisticsserviceclient.StatisticsServiceClient;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.traitserviceclient.message.TraitData;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import com.valhallagame.ymer.message.VersionParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/v1/utils")
public class UtilsController {

	private static ObjectMapper mapper = new ObjectMapper();

	private final CharacterServiceClient characterServiceClient;
	private final InstanceServiceClient instanceServiceClient;
	private final WardrobeServiceClient wardrobeServiceClient;
	private final PartyServiceClient partyServiceClient;
	private final FriendServiceClient friendServiceClient;
	private final FeatServiceClient featServiceClient;
	private final TraitServiceClient traitServiceClient;
	private final ActionbarServiceClient actionbarServiceClient;
	private final CurrencyServiceClient currencyServiceClient;
	private final RecipeServiceClient recipeServiceClient;
	private final NotificationServiceClient notificationServiceClient;
	private final StatisticsServiceClient statisticsServiceClient;
	private final ChatServiceClient chatServiceClient;
	private final BankServiceClient bankServiceClient;

	@Autowired
	public UtilsController(
			CharacterServiceClient characterServiceClient,
			InstanceServiceClient instanceServiceClient,
			WardrobeServiceClient wardrobeServiceClient,
			PartyServiceClient partyServiceClient,
			FriendServiceClient friendServiceClient,
			FeatServiceClient featServiceClient,
			TraitServiceClient traitServiceClient,
			ActionbarServiceClient actionbarServiceClient,
			CurrencyServiceClient currencyServiceClient,
			RecipeServiceClient recipeServiceClient,
			NotificationServiceClient notificationServiceClient,
			StatisticsServiceClient statisticsServiceClient,
			ChatServiceClient chatServiceClient,
			BankServiceClient bankServiceClient
	) {
		this.characterServiceClient = characterServiceClient;
		this.instanceServiceClient = instanceServiceClient;
		this.wardrobeServiceClient = wardrobeServiceClient;
		this.partyServiceClient = partyServiceClient;
		this.friendServiceClient = friendServiceClient;
		this.featServiceClient = featServiceClient;
		this.traitServiceClient = traitServiceClient;
		this.actionbarServiceClient = actionbarServiceClient;
		this.currencyServiceClient = currencyServiceClient;
		this.recipeServiceClient = recipeServiceClient;
		this.notificationServiceClient = notificationServiceClient;
		this.statisticsServiceClient = statisticsServiceClient;
		this.chatServiceClient = chatServiceClient;
		this.bankServiceClient = bankServiceClient;
	}

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
		out.set("actionbarData", getActionbarData(character.getCharacterName()));
		out.set("currencyData", getCurrencyData(character.getCharacterName()));
		out.set("recipeData", getRecipeData(username));
		out.set("bankData", getBankData(character.getCharacterName()));
		return JS.message(HttpStatus.OK, out);
	}

	@RequestMapping(path = "/uberping", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> uberPing() throws IOException {
		ObjectNode out = mapper.createObjectNode();
		out.put("characterServiceClient", characterServiceClient.ping().getStatusCode().value());
		out.put("instanceServiceClient", instanceServiceClient.ping().getStatusCode().value());
		out.put("wardrobeServiceClient", wardrobeServiceClient.ping().getStatusCode().value());
		out.put("partyServiceClient", partyServiceClient.ping().getStatusCode().value());
		out.put("friendServiceClient", friendServiceClient.ping().getStatusCode().value());
		out.put("featServiceClient", featServiceClient.ping().getStatusCode().value());
		out.put("traitServiceClient", traitServiceClient.ping().getStatusCode().value());
		out.put("actionbarServiceClient", actionbarServiceClient.ping().getStatusCode().value());
		out.put("currencyServiceClient", currencyServiceClient.ping().getStatusCode().value());
		out.put("recipeServiceClient", recipeServiceClient.ping().getStatusCode().value());
		out.put("notificationServiceClient", notificationServiceClient.ping().getStatusCode().value());
		out.put("statisticsServiceClient", statisticsServiceClient.ping().getStatusCode().value());
		out.put("chatServiceClient", characterServiceClient.ping().getStatusCode().value());
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

	private ObjectNode getActionbarData(String characterName) throws IOException {
		RestResponse<ActionbarWrapper> actionbar = actionbarServiceClient.getActionbar(characterName);

		if (actionbar.isOk() && actionbar.get().isPresent()) {
			ActionbarWrapper actionbarWrapper = actionbar.get().get();
			return mapper.valueToTree(actionbarWrapper);
		} else {
			throw new IOException(actionbar.getErrorMessage());
		}
	}

	private ObjectNode getCurrencyData(String characterName) throws IOException {
		RestResponse<List<CurrencyResult>> currenciesResponse = currencyServiceClient.getCurrencies(characterName);

		if (currenciesResponse.get().isPresent()) {
			List<CurrencyResult> currencyResults = currenciesResponse.get().get();
			ObjectNode currenciesList = mapper.createObjectNode();
			currenciesList.set("currencies",  mapper.valueToTree(currencyResults));
			return currenciesList;
		} else {
			throw new IOException(currenciesResponse.getErrorMessage());
		}
	}

	private ObjectNode getRecipeData(String username) throws IOException {
		RestResponse<List<RecipeData>> recipesResp = recipeServiceClient.getRecipes(username);
		if (recipesResp.get().isPresent()) {
			List<RecipeData> recipeResults = recipesResp.get().get();
			ObjectNode recipeList = mapper.createObjectNode();
			recipeList.set("recipes", mapper.valueToTree(recipeResults));
			return recipeList;
		} else {
			throw new IOException(recipesResp.getErrorMessage());
		}
	}

	private ObjectNode getBankData(String characterName) throws IOException {
		RestResponse<List<BankItemResult>> bankItems = bankServiceClient.getBankItems(characterName);
		if(bankItems.get().isPresent()) {
			List<BankItemResult> bankItemResults = bankItems.get().get();
			ObjectNode bankItemList = mapper.createObjectNode();
			bankItemList.set("bankItems", mapper.valueToTree(bankItemResults));
			return bankItemList;
		} else {
			throw new IOException(bankItems.getErrorMessage());
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
		RestResponse<TraitData> traitsResp = traitServiceClient.getTraits(username);
		Optional<TraitData> traitsOpt = traitsResp.get();
		if (traitsOpt.isPresent()) {
			return mapper.valueToTree(traitsOpt.get());
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
