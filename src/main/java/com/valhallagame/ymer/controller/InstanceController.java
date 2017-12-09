package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.ymer.message.StartDungeonParameter;
import com.valhallagame.ymer.message.StopDungeonParameter;
import com.valhallagame.ymer.message.GetPlayerSessionParamater;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {
	
	private static InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	//Return the instance that the user should be in. NOT the instance the user is actually in.
	@RequestMapping(path = "/get-player-session", method = RequestMethod.POST)
	public ResponseEntity<?> getSelectedInstance(@RequestAttribute("username") String username, GetPlayerSessionParamater input) throws IOException {
		return JS.message(instanceServiceClient.getPlayerSession(username, input.getVersion()));
	}
	
	@RequestMapping(path = "/start-dungeon", method = RequestMethod.POST)
	public ResponseEntity<?> startDungeon(@RequestAttribute("username") String username, StartDungeonParameter input) throws IOException {
		return JS.message(instanceServiceClient.startDungeon(username, input.getMap(), input.getVersion()));
	}
	
	@RequestMapping(path = "/stop-dungeon", method = RequestMethod.POST)
	public ResponseEntity<?> stopDungeon(@RequestAttribute("username") String username, StopDungeonParameter input) throws IOException {
		return JS.message(instanceServiceClient.stopDungeon(username, input.getDungeonId()));
	}
}
