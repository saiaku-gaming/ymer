package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;

@Controller
@RequestMapping("/v1/wardrobe")
public class WardrobeController {

	WardrobeServiceClient wardrobeServiceClient = WardrobeServiceClient.get();

	@RequestMapping(path = "/get-wardrobe-items", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getWardrobeItems(@RequestAttribute("username") String username) throws IOException {
		return JS.message(wardrobeServiceClient.getWardrobeItems(username));
	}
}
