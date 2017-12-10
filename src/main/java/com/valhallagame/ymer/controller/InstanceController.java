package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.ymer.message.GetPlayerSessionAndConnectionParamater;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {

	private static InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	// Return the instance that the user should be in. NOT the instance the user
	// is actually in.
	@RequestMapping(path = "/get-player-session-and-connection", method = RequestMethod.POST)
	public ResponseEntity<?> getPlayerSessionAndConnection(@RequestAttribute("username") String username,
			@RequestBody GetPlayerSessionAndConnectionParamater input) throws IOException {
		return JS.message(instanceServiceClient.getPlayerSessionAndConnection(username, input.getVersion()));
	}
}
