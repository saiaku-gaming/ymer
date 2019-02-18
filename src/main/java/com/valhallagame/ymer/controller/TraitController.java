package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/v1/trait")
public class TraitController {
	private static final Logger logger = LoggerFactory.getLogger(TraitController.class);

	@Autowired
	private TraitServiceClient traitServiceClient;

	@RequestMapping(path = "/get-traits", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getTraits(@RequestAttribute("username") String username) throws IOException {
		logger.info("Get Traits called");
		return JS.message(traitServiceClient.getTraits(username));
	}
}
