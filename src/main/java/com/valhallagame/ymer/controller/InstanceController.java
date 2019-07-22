package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.model.RelevantDungeonData;
import com.valhallagame.ymer.message.instance.GetDungeonConnectionParameter;
import com.valhallagame.ymer.message.instance.GetHubParameter;
import com.valhallagame.ymer.message.instance.GetRelevantDungeonsParameter;
import com.valhallagame.ymer.message.instance.StartDungeonParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {
	private static final Logger logger = LoggerFactory.getLogger(InstanceController.class);

	@Autowired
	private InstanceServiceClient instanceServiceClient;

	@RequestMapping(path = "/get-hub", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getPlayerSessionAndConnection(@RequestAttribute("username") String username,
			@RequestBody GetHubParameter input) throws IOException {
		logger.info("Get Hub called with {}", input);
		return JS.message(instanceServiceClient.getHub(username, input.getVersion()));
	}

	@RequestMapping(path = "/start-dungeon", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> startDungeon(@RequestAttribute("username") String username,
			@RequestBody StartDungeonParameter input) throws IOException {
		logger.info("Start Dungeon called with {}", input);
		return JS.message(instanceServiceClient.startDungeon(username, input.getMap(), input.getVersion()));
	}

	@RequestMapping(path = "/get-dungeon-connection", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getDungeonConnection(@RequestAttribute("username") String username,
			@RequestBody GetDungeonConnectionParameter input) throws IOException {
		logger.info("Get Dungeon Connection called with {}", input);
		return JS.message(
                instanceServiceClient.getDungeonConnection(username, input.getInstanceId(), input.getVersion()));
	}

	@RequestMapping(path = "/get-relevant-dungeons", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getRelevantDungeons(@RequestAttribute("username") String username,
			@RequestBody GetRelevantDungeonsParameter input) throws IOException {
		logger.info("Get Relevant Dungeons called with {}", input);
		RestResponse<RelevantDungeonData> relevantDungeons = instanceServiceClient
                .getRelevantDungeons(username, input.getVersion());
		return JS.message(relevantDungeons);
	}
}
