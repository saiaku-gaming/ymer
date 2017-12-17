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
import com.valhallagame.ymer.message.StartDungeonParameter;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {

	private static InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	@RequestMapping(path = "/get-player-session-and-connection", method = RequestMethod.POST)
	public ResponseEntity<?> getPlayerSessionAndConnection(@RequestAttribute("username") String username,
			@RequestBody GetPlayerSessionAndConnectionParamater input) throws IOException {
		return JS.message(instanceServiceClient.getPlayerSessionAndConnection(username, input.getVersion()));
	}

	@RequestMapping(path = "/start-dungeon", method = RequestMethod.POST)
	public ResponseEntity<?> startDungeon(@RequestAttribute("username") String username,
			@RequestBody StartDungeonParameter input) throws IOException {
		return JS.message(instanceServiceClient.startDungeon(username, input.getMap(), input.getVersion()));
	}
}
