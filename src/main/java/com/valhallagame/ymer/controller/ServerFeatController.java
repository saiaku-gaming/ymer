package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.featserviceclient.message.AddFeatParameter;
import com.valhallagame.featserviceclient.message.RemoveFeatParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/v1/server-feat")
public class ServerFeatController {
	private static final Logger logger = LoggerFactory.getLogger(ServerFeatController.class);

	@Autowired
	private FeatServiceClient featServiceClient;

	@RequestMapping(path = "/add-feat", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> addFeat(@RequestBody AddFeatParameter input) throws IOException {
		logger.info("Add Feat called with {}", input);
		return JS.message(featServiceClient.addFeat(input));
	}

	@RequestMapping(path = "/remove-feat", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> removeFeat(@RequestBody RemoveFeatParameter input) throws IOException {
		logger.info("Remove Feat called with {}", input);
		return JS.message(featServiceClient.removeFeat(input));
	}
}
