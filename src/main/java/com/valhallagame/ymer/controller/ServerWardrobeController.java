package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import com.valhallagame.wardrobeserviceclient.message.AddWardrobeItemParameter;
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
@RequestMapping("/v1/server-wardrobe")
public class ServerWardrobeController {
	private static final Logger logger = LoggerFactory.getLogger(ServerWardrobeController.class);

	@Autowired
	private WardrobeServiceClient wardrobeServiceClient;

	@RequestMapping(path = "/add-wardrobe-item", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> addWardrobeItem(@RequestBody AddWardrobeItemParameter input) throws IOException {
		logger.info("Add Wardrobe Item called with {}", input);
		return JS.message(wardrobeServiceClient.addWardrobeItem(input));
	}

}
