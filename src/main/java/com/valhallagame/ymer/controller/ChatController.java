package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.ymer.message.chat.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/v1/chat")
public class ChatController {
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	ChatServiceClient chatServiceClient;

	@RequestMapping(path = "/whisper-character", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> whisperCharacter(@RequestAttribute("username") String username,
			@RequestBody WhisperCharacterParameter input) throws IOException {
		logger.info("Whisper Character called with {}", input);
		return JS.message(chatServiceClient.whisperCharacter(username, input.getMessage(), input.getTargetDisplayCharacterName()));
	}

	@RequestMapping(path = "/whisper-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> whisperPerson(@RequestAttribute("username") String username,
			@RequestBody WhisperPersonParameter input) throws IOException {
		logger.info("Whisper Person called with {}", input);
		RestResponse<String> result = chatServiceClient
				.whisperPerson(username, input.getMessage(), input.getTargetDisplayUsername());
		return JS.message(result);
	}

	@RequestMapping(path = "/instance-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> instanceChat(@RequestAttribute("username") String username,
			@RequestBody InstanceChatParameter input) throws IOException {
		logger.info("Instance Chat called with {}", input);
		RestResponse<String> result = chatServiceClient.instanceChat(username, input.getMessage());
		return JS.message(result);
	}

	@RequestMapping(path = "/general-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> generalChat(@RequestAttribute("username") String username,
			@RequestBody GeneralChatParameter input) throws IOException {
		logger.info("General Chat called with {}", input);
		RestResponse<String> result = chatServiceClient.generalChat(username, input.getMessage());

		return JS.message(result);
	}

	@RequestMapping(path = "/party-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> partyChat(@RequestAttribute("username") String username,
			@RequestBody PartyChatParameter input) throws IOException {
		logger.info("Party Chat called with {}", input);
		RestResponse<String> result = chatServiceClient.partyChat(username, input.getMessage());
		return JS.message(result);
	}
}
