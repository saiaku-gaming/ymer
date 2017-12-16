package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.chatserviceclient.ChatServiceClient;
import com.valhallagame.chatserviceclient.model.ChatMessage;
import com.valhallagame.chatserviceclient.model.ChatParameter;
import com.valhallagame.chatserviceclient.model.WhisperCharacterParameter;
import com.valhallagame.chatserviceclient.model.WhisperPersonParameter;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;

@Controller
@RequestMapping("/v1/chat")
public class ChatController {

	ChatServiceClient chatServiceClient = ChatServiceClient.get();

	@RequestMapping(path = "/get-messages", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getMessages(@RequestAttribute("username") String username) throws IOException {
		RestResponse<List<ChatMessage>> chatMessages = chatServiceClient.getMessages(username);
		return JS.message(chatMessages);
	}

	@RequestMapping(path = "/whisper-character", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> whisperPerson(@RequestAttribute("username") String username,
			@RequestBody WhisperCharacterParameter whisperParameter) throws IOException {
		whisperParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.whisperCharacter(whisperParameter);
		return JS.message(result);
	}
	
	@RequestMapping(path = "/whisper-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> whisperPerson(@RequestAttribute("username") String username,
			@RequestBody WhisperPersonParameter whisperParameter) throws IOException {
		whisperParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.whisperPerson(whisperParameter);
		return JS.message(result);
	}

	@RequestMapping(path = "/instance-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> instanceChat(@RequestAttribute("username") String username,
			@RequestBody ChatParameter chatParameter) throws IOException {
		chatParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.instanceChat(chatParameter);
		return JS.message(result);
	}

	@RequestMapping(path = "/general-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> generalChat(@RequestAttribute("username") String username,
			@RequestBody ChatParameter chatParameter) throws IOException {
		chatParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.generalChat(chatParameter);

		return JS.message(result);
	}

	@RequestMapping(path = "/party-chat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> partyChat(@RequestAttribute("username") String username,
			@RequestBody ChatParameter chatParameter) throws IOException {
		chatParameter.setSenderUsername(username);
		RestResponse<String> result = chatServiceClient.partyChat(chatParameter);
		return JS.message(result);
	}
}
