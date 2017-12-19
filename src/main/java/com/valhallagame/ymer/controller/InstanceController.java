package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.message.GetHubParameter;
import com.valhallagame.instanceserviceclient.message.GetRelevantDungeonsParameter;
import com.valhallagame.ymer.message.StartDungeonParameter;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {

	private static InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	@RequestMapping(path = "/get-hub", method = RequestMethod.POST)
	public ResponseEntity<?> getPlayerSessionAndConnection(@RequestAttribute("username") String username,
			@RequestBody GetHubParameter input) throws IOException {
		return JS.message(instanceServiceClient.getHub(username, input.getVersion()));
	}

	@RequestMapping(path = "/start-dungeon", method = RequestMethod.POST)
	public ResponseEntity<?> startDungeon(@RequestAttribute("username") String username,
			@RequestBody StartDungeonParameter input) throws IOException {

		return JS.message(instanceServiceClient.startDungeon(username, input.getMap(), input.getVersion()));
	}

	@RequestMapping(path = "/get-relevant-dungeons", method = RequestMethod.POST)
	public ResponseEntity<?> getRelevantDungeons(@RequestAttribute("username") String username,
			@RequestBody GetRelevantDungeonsParameter input) throws IOException {

		RestResponse<List<String>> relevantDungeons = instanceServiceClient.getRelevantDungeons(input.getUsername(),
				input.getVersion());

		Map<String, Object> body = new HashMap<>();
		body.put("relevantDungeons", relevantDungeons);

		return JS.message(HttpStatus.OK, body);
	}
}
