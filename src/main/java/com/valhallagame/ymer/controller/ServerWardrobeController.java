package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import com.valhallagame.ymer.message.WardrobeItemParameter;

@Controller
@RequestMapping("/v1/server-wardrobe")
public class ServerWardrobeController {

	WardrobeServiceClient wardrobeServiceClient = WardrobeServiceClient.get();

	@RequestMapping(path = "/add-wardrobe-item", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> addWardrobeItem(@RequestBody WardrobeItemParameter input)
			throws IOException {
		return JS.message(wardrobeServiceClient.addWardrobeItem(input.getCharacterName(), input.getItemName()));
	}

}
