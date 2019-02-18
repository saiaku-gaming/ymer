package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;
import com.valhallagame.instanceserviceclient.message.LatestVersionParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/v1/public-instance")
public class PublicInstanceController {
	private static final Logger logger = LoggerFactory.getLogger(PublicInstanceController.class);

	@Autowired
	private InstanceServiceClient instanceServiceClient;
	
	@RequestMapping(path = "/get-fleets", method = RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<JsonNode> getFleets() throws IOException {
		logger.info("Get Fleets called");
		return JS.message(HttpStatus.OK, instanceServiceClient.getFleets().get().orElse(new ArrayList<>()));
	}

	@RequestMapping(path = "/latest-version", method = RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<JsonNode> getLatestVersion(@Valid @RequestBody LatestVersionParameter input) throws IOException {
		logger.info("Get Latest Version called with {}", input);
        return JS.message(instanceServiceClient.latestVersion(input));
	}
}
