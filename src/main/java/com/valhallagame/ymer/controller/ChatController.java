package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.chatserviceclient.message.ChatParameter;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.ymer.message.GeneralChatParameter;
import com.valhallagame.ymer.message.WhisperCharacterParameter;
import com.valhallagame.ymer.message.WhisperPersonParameter;

@Controller
@RequestMapping("/v1/chat")
public class ChatController {

	@Autowired
	ChatServiceClient chatServiceClient;

	@RequestMapping(path = "/whisper-character", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> whisperPerson(@RequestAttribute("username") String username,
			@RequestBody WhisperCharacterParameter input) throws IOException {

		RestResponse<String> result = chatServiceClient
				.whisperCharacter(new com.valhallagame.chatserviceclient.message.WhisperCharacterParameter(username,
						input.getMessage(), input.getTargetDisplayCharacterName()));
		return JS.message(result);
	}

	@RequestMapping(path = "/whisper-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> whisperPerson(@RequestAttribute("username") String username,
			@RequestBody WhisperPersonParameter input) throws IOException {
		RestResponse<String> result = chatServiceClient
				.whisperPerson(new com.valhallagame.chatserviceclient.message.WhisperPersonParameter(username,
						input.getMessage(), input.getTargetDisplayUsername()));
		return JS.message(result);
	}

	@RequestMapping(path = "/instance-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> instanceChat(@RequestAttribute("username") String username,
			@RequestBody ChatParameter chatParameter) throws IOException {
		chatParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.instanceChat(chatParameter);
		return JS.message(result);
	}

	@RequestMapping(path = "/general-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> generalChat(@RequestAttribute("username") String username,
			@RequestBody GeneralChatParameter input) throws IOException {
		ChatParameter chatParameter = new ChatParameter(username, input.getMessage());
		RestResponse<String> result = chatServiceClient.generalChat(chatParameter);

		return JS.message(result);
	}

	@RequestMapping(path = "/party-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> partyChat(@RequestAttribute("username") String username,
			@RequestBody ChatParameter chatParameter) throws IOException {
		chatParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.partyChat(chatParameter);
		return JS.message(result);
	}
}
