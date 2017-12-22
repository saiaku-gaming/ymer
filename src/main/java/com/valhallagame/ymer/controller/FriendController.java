package com.valhallagame.ymer.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.ymer.message.DisplayCharacterNameParameter;
import com.valhallagame.ymer.message.UsernameParameter;

@Controller
@RequestMapping("/v1/friend")
public class FriendController {

	@RequestMapping(path = "/get-friends-data", method = RequestMethod.GET)
	public ResponseEntity<?> getFriendsData(@RequestAttribute("username") String username) throws IOException {
		return JS.message(FriendServiceClient.get().getFriendsData(username));
	}

	@RequestMapping(path = "/send-person-invite", method = RequestMethod.POST)
	public ResponseEntity<?> sendPersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().sendPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/send-character-invite", method = RequestMethod.POST)
	public ResponseEntity<?> sendCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(
				FriendServiceClient.get().sendCharacterInvite(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/accept-person-invite", method = RequestMethod.POST)
	public ResponseEntity<?> acceptPersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().acceptPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/accept-character-invite", method = RequestMethod.POST)
	public ResponseEntity<?> acceptCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().acceptCharacterInvite(username,
				input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/decline-person-invite", method = RequestMethod.POST)
	public ResponseEntity<?> declinePersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().declinePersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/decline-character-invite", method = RequestMethod.POST)
	public ResponseEntity<?> declineCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().declineCharacterInvite(username,
				input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/remove-person-friend", method = RequestMethod.POST)
	public ResponseEntity<?> removePersonFriend(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().removePersonFriend(username, input.getUsername()));
	}

	@RequestMapping(path = "/remove-character-friend", method = RequestMethod.POST)
	public ResponseEntity<?> removeCharacterFriend(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(FriendServiceClient.get().removeCharacterFriend(username,
				input.getDisplayCharacterName().toLowerCase()));
	}
}
