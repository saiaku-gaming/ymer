package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.featserviceclient.message.GetFeatsParameter;
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
@RequestMapping("/v1/feat")
public class FeatController {
	private static final Logger logger = LoggerFactory.getLogger(FeatController.class);

	@Autowired
	private FeatServiceClient featServiceClient;

	@RequestMapping(path = "/get-feats", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getFeats(@RequestAttribute("username") String username, @RequestBody GetFeatsParameter input) throws IOException {
		logger.info("Get Feats called with {}", input);
		return JS.message(featServiceClient.getFeats(input));
	}
}
