package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.message.ActivateInstanceParameter;
import com.valhallagame.instanceserviceclient.message.AddLocalInstanceParameter;
import com.valhallagame.instanceserviceclient.message.InstancePlayerLoginParameter;
import com.valhallagame.instanceserviceclient.message.InstancePlayerLogoutParameter;
import com.valhallagame.instanceserviceclient.message.UpdateInstanceStateParameter;

@Controller
@RequestMapping("/v1/server-instance")
public class ServerInstanceController {
	InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	@RequestMapping(path = "/activate-instance", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> activateInstance(@RequestBody ActivateInstanceParameter input) throws IOException {
		return JS.message(
				instanceServiceClient.activateInstance(input.getGameSessionId(), input.getAddress(), input.getPort()));
	}

	@RequestMapping(path = "/update-instance-state", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateInstanceState(@RequestBody UpdateInstanceStateParameter input) throws IOException {
		return JS.message(instanceServiceClient.updateInstanceState(input.getGameSessionId(), input.getState()));
	}

	@RequestMapping(path = "/instance-player-login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> instancePlayerLogin(@RequestBody InstancePlayerLoginParameter input) throws IOException {
		return JS.message(instanceServiceClient.instancePlayerLogin(input.getToken(), input.getGameSessionId()));
	}

	@RequestMapping(path = "/instance-player-logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> instancePlayerLogout(@RequestBody InstancePlayerLogoutParameter input) throws IOException {
		return JS.message(instanceServiceClient.instancePlayerLogout(input.getUsername(), input.getGameSessionId()));
	}

	@RequestMapping(path = "/add-local-instance", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addLocalInstance(@RequestBody AddLocalInstanceParameter input) throws IOException {
		return JS.message(instanceServiceClient.addLocalInstance(input));
	}
}
