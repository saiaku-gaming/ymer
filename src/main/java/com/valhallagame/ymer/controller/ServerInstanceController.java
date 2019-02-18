package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.message.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/v1/server-instance")
public class ServerInstanceController {
	private static final Logger logger = LoggerFactory.getLogger(ServerInstanceController.class);

	@Autowired
	private InstanceServiceClient instanceServiceClient;

	@RequestMapping(path = "/activate-instance", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> activateInstance(@RequestBody ActivateInstanceParameter input) throws IOException {
		logger.info("Activate Instance called with {}", input);
		return JS.message(
				instanceServiceClient.activateInstance(input.getGameSessionId(), input.getAddress(), input.getPort()));
	}

	@RequestMapping(path = "/update-instance-state", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> updateInstanceState(@RequestBody UpdateInstanceStateParameter input)
			throws IOException {
		logger.info("Update Instance State called with {}", input);
		return JS.message(instanceServiceClient.updateInstanceState(input.getGameSessionId(), input.getState()));
	}

	@RequestMapping(path = "/instance-player-login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> instancePlayerLogin(@RequestBody InstancePlayerLoginParameter input)
			throws IOException {
		return JS.message(instanceServiceClient.instancePlayerLogin(input.getToken(), input.getGameSessionId()));
	}

	@RequestMapping(path = "/instance-player-logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> instancePlayerLogout(@RequestBody InstancePlayerLogoutParameter input)
			throws IOException {
		logger.info("Instance Player Logout called with {}", input);
		return JS.message(instanceServiceClient.instancePlayerLogout(input.getUsername(), input.getGameSessionId()));
	}

	@RequestMapping(path = "/add-local-instance", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> addLocalInstance(@RequestBody AddLocalInstanceParameter input) throws IOException {
		logger.info("Add Local Instance called with {}", input);
		return JS.message(instanceServiceClient.addLocalInstance(input));
	}
}
