package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.message.GetHubParameter;
import com.valhallagame.instanceserviceclient.message.GetRelevantDungeonsParameter;
import com.valhallagame.instanceserviceclient.model.RelevantDungeonData;
import com.valhallagame.ymer.message.GetDungeonConnectionParameter;
import com.valhallagame.ymer.message.StartDungeonParameter;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {

	private static InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	@RequestMapping(path = "/get-hub", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getPlayerSessionAndConnection(@RequestAttribute("username") String username,
			@RequestBody GetHubParameter input) throws IOException {
		return JS.message(instanceServiceClient.getHub(username, input.getVersion()));
	}

	@RequestMapping(path = "/start-dungeon", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> startDungeon(@RequestAttribute("username") String username,
			@RequestBody StartDungeonParameter input) throws IOException {

		return JS.message(instanceServiceClient.startDungeon(username, input.getMap(), input.getVersion()));
	}

	@RequestMapping(path = "/get-dungeon-connection", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getDungeonConnection(@RequestAttribute("username") String username,
			@RequestBody GetDungeonConnectionParameter input) throws IOException {

		return JS.message(
				instanceServiceClient.getDungeonConnection(username, input.getGameSessionId(), input.getVersion()));
	}

	@RequestMapping(path = "/get-relevant-dungeons", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getRelevantDungeons(@RequestAttribute("username") String username,
			@RequestBody GetRelevantDungeonsParameter input) throws IOException {

		RestResponse<RelevantDungeonData> relevantDungeons = instanceServiceClient
				.getRelevantDungeons(input.getUsername(), input.getVersion());
		return JS.message(relevantDungeons);
	}
}
