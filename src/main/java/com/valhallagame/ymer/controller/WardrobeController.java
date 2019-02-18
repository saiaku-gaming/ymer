package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.wardrobeserviceclient.WardrobeServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/v1/wardrobe")
public class WardrobeController {
	private static final Logger logger = LoggerFactory.getLogger(WardrobeController.class);

	@Autowired
	private WardrobeServiceClient wardrobeServiceClient;

	@RequestMapping(path = "/get-wardrobe-items", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getWardrobeItems(@RequestAttribute("username") String username) throws IOException {
		logger.info("Get Wardrobe Items called");
		RestResponse<List<String>> wardrobeItemsResp = wardrobeServiceClient.getWardrobeItems(username);
		
		if(wardrobeItemsResp.isOk()) {
			Optional<List<String>> opt = wardrobeItemsResp.get();
			if(opt.isPresent()) {
				List<String> list = opt.get();
				Map<String, List<String>> out = new HashMap<>();
				out.put("wardrobe", list);
				return JS.message(HttpStatus.OK, out);	
			}
		} 
		return JS.message(wardrobeItemsResp);
	}
}
