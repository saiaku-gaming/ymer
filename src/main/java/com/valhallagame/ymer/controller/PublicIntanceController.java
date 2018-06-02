package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;

@Controller
@RequestMapping(path = "/v1/public-instance")
public class PublicIntanceController {
	
	@Autowired
	private InstanceServiceClient instanceServiceClient;
	
	@RequestMapping(path = "/get-fleets", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> getFleets() throws IOException {
		return JS.message(instanceServiceClient.getFleets());
	}
}
