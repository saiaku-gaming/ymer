package com.valhallagame.ymer.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.ymer.message.friend.AcceptCharacterInviteParameter;
import com.valhallagame.ymer.message.friend.AcceptPersonInviteParameter;
import com.valhallagame.ymer.message.friend.DeclineCharacterParameter;
import com.valhallagame.ymer.message.friend.DeclinePersonInviteParameter;
import com.valhallagame.ymer.message.friend.InviteCharacterParameter;
import com.valhallagame.ymer.message.friend.InvitePersonParameter;
import com.valhallagame.ymer.message.friend.RemoveCharacterFriendParameter;
import com.valhallagame.ymer.message.friend.RemovePersonFriendParameter;

@Controller
@RequestMapping("/v1/friend")
public class FriendController {

	@Autowired
	private FriendServiceClient friendServiceClient;

	@RequestMapping(path = "/get-friend-data", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getFriendData(@RequestAttribute("username") String username) throws IOException {
		return JS.message(friendServiceClient.getFriendData(username));
	}

	@RequestMapping(path = "/send-person-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendPersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody InvitePersonParameter input) throws IOException {
		return JS.message(friendServiceClient.sendPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/send-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody InviteCharacterParameter input) throws IOException {
		return JS.message(
				friendServiceClient.sendCharacterInvite(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/accept-person-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> acceptPersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody AcceptPersonInviteParameter input) throws IOException {
		return JS.message(friendServiceClient.acceptPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/accept-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> acceptCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody AcceptCharacterInviteParameter input) throws IOException {
		return JS.message( 
				friendServiceClient.acceptCharacterInvite(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/decline-person-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> declinePersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DeclinePersonInviteParameter input) throws IOException {
		return JS.message(friendServiceClient.declinePersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/decline-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> declineCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DeclineCharacterParameter input) throws IOException {
		return JS.message(
				friendServiceClient.declineCharacterInvite(username, input.getCharacterName()));
	}

	@RequestMapping(path = "/remove-person-friend", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> removePersonFriend(@RequestAttribute("username") String username,
			@Valid @RequestBody RemovePersonFriendParameter input) throws IOException {
		return JS.message(friendServiceClient.removePersonFriend(username, input.getUsername()));
	}

	@RequestMapping(path = "/remove-character-friend", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> removeCharacterFriend(@RequestAttribute("username") String username,
			@Valid @RequestBody RemoveCharacterFriendParameter input) throws IOException {
		return JS.message(
				friendServiceClient.removeCharacterFriend(username, input.getCharacterName()));
	}
}
