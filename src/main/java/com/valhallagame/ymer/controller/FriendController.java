package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.ymer.message.UsernameParameter;

@Controller
@RequestMapping("/v1/friend")
public class FriendController {

	@RequestMapping(path = "/send-invite", method = RequestMethod.POST)
	public ResponseEntity<?> sendInvite(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		if (FriendServiceClient.get().sendFriendInvite(username, input.getUsername()).isOk()) {
			return JS.message(HttpStatus.OK, "Friend invite sent!");
		} else {
			return JS.message(HttpStatus.NOT_FOUND, "THINGS!");
		}
	}

	@RequestMapping(path = "/accept", method = RequestMethod.POST)
	public ResponseEntity<?> accept(@RequestAttribute("username") String username, @RequestBody UsernameParameter input)
			throws IOException {
		if (FriendServiceClient.get().acceptInvite(username, input.getUsername()).isOk()) {
			return JS.message(HttpStatus.OK, "Friend invite accepted!");
		} else {
			return JS.message(HttpStatus.NOT_FOUND, "THINGS!");
		}
	}

	@RequestMapping(path = "/decline", method = RequestMethod.POST)
	public ResponseEntity<?> decline(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		if (FriendServiceClient.get().declineInvite(username, input.getUsername()).isOk()) {
			return JS.message(HttpStatus.OK, "Friend invite declined!");
		} else {
			return JS.message(HttpStatus.NOT_FOUND, "THINGS!");
		}
	}

	@RequestMapping(path = "/remove-friend", method = RequestMethod.POST)
	public ResponseEntity<?> removeFriend(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		if (FriendServiceClient.get().removeFriend(username, input.getUsername()).isOk()) {
			return JS.message(HttpStatus.OK, "Friend removed!");
		} else {
			return JS.message(HttpStatus.NOT_FOUND, "THINGS!");
		}
	}
}
