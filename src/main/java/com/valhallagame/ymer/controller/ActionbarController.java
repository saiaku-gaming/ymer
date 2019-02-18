package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.actionbarserviceclient.ActionbarServiceClient;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.model.CharacterData;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.ymer.message.actionbar.GetActionbarParameter;
import com.valhallagame.ymer.message.actionbar.RemoveActionbarActionParameter;
import com.valhallagame.ymer.message.actionbar.SetActionbarItemActionParameter;
import com.valhallagame.ymer.message.actionbar.SetActionbarTraitActionParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/v1/actionbar")
public class ActionbarController {
	private static final Logger logger = LoggerFactory.getLogger(ActionbarController.class);

	@Autowired
	private ActionbarServiceClient actionbarServiceClient;

	@Autowired
	private CharacterServiceClient characterServiceClient;

	@RequestMapping(path = "/set-actionbar-trait-action", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> setActionbarTraitAction(@RequestAttribute("username") String username,
			@RequestBody SetActionbarTraitActionParameter input) throws IOException {
		logger.info("Set Actionbar Trait Action called with {}", input);
		RestResponse<CharacterData> ownedCharacter = characterServiceClient.getOwnedCharacter(username,
				input.getCharacterName());
		if (!ownedCharacter.isOk()) {
			return JS.message(ownedCharacter);
		}

		return JS.message(
				actionbarServiceClient.setActionbarTraitAction(input.getCharacterName(), input.getIndex(),
						input.getTraitName()));
	}

	@RequestMapping(path = "/set-actionbar-item-action", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> setActionbarItemAction(@RequestAttribute("username") String username,
			@RequestBody SetActionbarItemActionParameter input) throws IOException {
		logger.info("Set Actionbar Item Action called with {}", input);
		RestResponse<CharacterData> ownedCharacter = characterServiceClient.getOwnedCharacter(username,
				input.getCharacterName());
		if (!ownedCharacter.isOk()) {
			return JS.message(ownedCharacter);
		}

		return JS.message(
				actionbarServiceClient.setActionbarItemAction(input.getCharacterName(), input.getIndex(),
						input.getItemName()));
	}

	@RequestMapping(path = "/remove-actionbar-action", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> removeActionbarAction(@RequestAttribute("username") String username,
			@RequestBody RemoveActionbarActionParameter input) throws IOException {
		logger.info("Remove Actionbar Action called with {}", input);
		RestResponse<CharacterData> ownedCharacter = characterServiceClient.getOwnedCharacter(username,
				input.getCharacterName());
		if (!ownedCharacter.isOk()) {
			return JS.message(ownedCharacter);
		}

		return JS.message(actionbarServiceClient.removeActionbarAction(input.getCharacterName(), input.getIndex()));
	}

	@RequestMapping(path = "/get-actionbar", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getActionbar(@RequestAttribute("username") String username,
			@RequestBody GetActionbarParameter input) throws IOException {
		logger.info("Get Actionbar called with {}", input);
		RestResponse<CharacterData> ownedCharacter = characterServiceClient.getOwnedCharacter(username,
				input.getCharacterName());
		if (!ownedCharacter.isOk()) {
			return JS.message(ownedCharacter);
		}

		return JS.message(actionbarServiceClient.getActionbar(input.getCharacterName()));
	}
}
