package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.ymer.message.friend.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/v1/friend")
public class FriendController {
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	private FriendServiceClient friendServiceClient;

	@RequestMapping(path = "/get-friend-data", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getFriendData(@RequestAttribute("username") String username) throws IOException {
		logger.info("Get Friend Data called");
		return JS.message(friendServiceClient.getFriendData(username));
	}

	@RequestMapping(path = "/send-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody SendCharacterInviteParameter input) throws IOException {
		logger.info("Send Character Invite called with {}", input);
		return JS.message(
				friendServiceClient.sendCharacterInvite(username, input.getDisplayCharacterName()));
	}


	@RequestMapping(path = "/accept-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> acceptCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody AcceptCharacterInviteParameter input) throws IOException {
		logger.info("Accept Character Invite called with {}", input);
		return JS.message( 
				friendServiceClient.acceptCharacterInvite(username, input.getDisplayCharacterName()));
	}


	@RequestMapping(path = "/decline-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> declineCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DeclineCharacterParameter input) throws IOException {
		logger.info("Decline Character Invite called with {}", input);
		return JS.message(
				friendServiceClient.declineCharacterInvite(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/cancel-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> cancelCharacterInvite(@RequestAttribute("username") String username,
														  @Valid @RequestBody CancelCharacterInviteParameter input) throws IOException {
		logger.info("Cancel Character Invite called with {}", input);
		return JS.message(
				friendServiceClient.cancelCharacterInvite(username, input.getDisplayCharacterName()));
	}

	@RequestMapping(path = "/remove-character-friend", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> removeCharacterFriend(@RequestAttribute("username") String username,
			@Valid @RequestBody RemoveCharacterFriendParameter input) throws IOException {
		logger.info("Remove Character Friend called with {}", input);
		return JS.message(
				friendServiceClient.removeCharacterFriend(username, input.getDisplayCharacterName()));
	}
}
