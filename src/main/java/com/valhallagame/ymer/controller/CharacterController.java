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
import com.valhallagame.characterserviceclient.message.CharacterNameParameter;
import com.valhallagame.characterserviceclient.message.EqippedItemsParameter;
import com.valhallagame.characterserviceclient.model.CharacterData;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;

@Controller
@RequestMapping("/v1/character")
public class CharacterController {

	@Autowired
	private CharacterServiceClient characterServiceClient;

	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getCharacter(@RequestAttribute("username") String username,
			@RequestBody CharacterNameParameter input) throws IOException {
		RestResponse<CharacterData> resp = characterServiceClient.getCharacter(username, input.getCharacterName());
		return JS.message(resp);
	}

	@RequestMapping(path = "/get-all", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getAll(@RequestAttribute("username") String username) throws IOException {
		return JS.message(characterServiceClient.getAll(username));
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> create(@RequestAttribute("username") String username,
			@RequestBody CharacterNameParameter input) throws IOException {
		return JS.message(characterServiceClient.create(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> delete(@RequestAttribute("username") String username,
			@RequestBody CharacterNameParameter input) throws IOException {
		return JS.message(characterServiceClient.delete(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/character-available", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> characterAvailable(@RequestAttribute("username") String username,
			@RequestBody CharacterNameParameter input) throws IOException {
		return JS.message(characterServiceClient.characterAvailable(input.getCharacterName()));
	}

	@RequestMapping(path = "/select-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> selectCharacter(@RequestAttribute("username") String username,
			@RequestBody CharacterNameParameter input) throws IOException {
		return JS.message(characterServiceClient.selectCharacter(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/get-selected-character", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getSelectedCharacter(@RequestAttribute("username") String username)
			throws IOException {
		return JS.message(characterServiceClient.getSelectedCharacter(username));
	}

	@RequestMapping(path = "/save-equipped-items", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveEquippedItems(@RequestBody EqippedItemsParameter input) throws IOException {
		return JS.message(characterServiceClient.saveEquippedItems(input));
	}
}
