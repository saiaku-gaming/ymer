package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.common.JS;
import com.valhallagame.ymer.message.character.CharacterAvailableParameter;
import com.valhallagame.ymer.message.character.CreateCharacterParameter;
import com.valhallagame.ymer.message.character.DeleteCharacterParameter;
import com.valhallagame.ymer.message.character.GetCharacterParameter;
import com.valhallagame.ymer.message.character.SaveEquippedItemsParameter;
import com.valhallagame.ymer.message.character.SelectCharacterParameter;

@Controller
@RequestMapping("/v1/character")
public class CharacterController {

	@Autowired
	private CharacterServiceClient characterServiceClient;

	@RequestMapping(path = "/get-owned-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getCharacter(@RequestAttribute("username") String username,
			@RequestBody GetCharacterParameter input) throws IOException {
		return JS.message(characterServiceClient.getOwnedCharacter(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/get-all-characters", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getAll(@RequestAttribute("username") String username) throws IOException {
		return JS.message(characterServiceClient.getAllCharacters(username));
	}

	@RequestMapping(path = "/create-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> create(@RequestAttribute("username") String username,
			@RequestBody CreateCharacterParameter input) throws IOException {
		return JS.message(characterServiceClient.createCharacter(username, input.getDisplayCharacterName()));
	}

	@RequestMapping(path = "/delete-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> delete(@RequestAttribute("username") String username,
			@RequestBody DeleteCharacterParameter input) throws IOException {
		return JS.message(characterServiceClient.deleteCharacter(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/character-available", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> characterAvailable(@RequestAttribute("username") String username,
			@RequestBody CharacterAvailableParameter input) throws IOException {
		return JS.message(characterServiceClient.characterAvailable(input.getCharacterName()));
	}

	@RequestMapping(path = "/select-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> selectCharacter(@RequestAttribute("username") String username,
			@RequestBody SelectCharacterParameter input) throws IOException {
		return JS.message(characterServiceClient.selectCharacter(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/save-equipped-items", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveEquippedItems(@RequestAttribute("username") String username,
			@RequestBody SaveEquippedItemsParameter input) throws IOException {
		return JS.message(characterServiceClient.saveEquippedItems(username, input.getCharacterName(),
				input.getEquippedItems()));
	}
}
