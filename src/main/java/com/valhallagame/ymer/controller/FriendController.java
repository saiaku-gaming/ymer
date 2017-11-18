package com.valhallagame.ymer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.friendserviceclient.FriendServiceClient;
import com.valhallagame.friendserviceclient.model.InviteParameter;

@Controller
@RequestMapping("/v1/friend")
public class FriendController {

	@RequestMapping(path = "/send-invite", method = RequestMethod.POST)
	public ResponseEntity<?> sendInvite(@RequestBody InviteParameter input) {
		if (FriendServiceClient.get().sendFriendInvite(input.getSender(), input.getReceiver())) {
			return JS.message(HttpStatus.OK, "Friend invite sent!");
		} else {
			return JS.message(HttpStatus.NOT_FOUND, "THINGS!");
		}
	}

}
