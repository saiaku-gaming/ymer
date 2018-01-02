package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.ymer.message.FeatParameter;

@Controller
@RequestMapping("/v1/feat")
public class FeatController {

	FeatServiceClient featServiceClient = FeatServiceClient.get();

	@RequestMapping(path = "/get-feats", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getFeats(@RequestAttribute("username") String username) throws IOException {
		return JS.message(featServiceClient.getFeats(username));
	}
	
	@RequestMapping(path = "/debug-add-feat", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> debugAddFeats(@RequestAttribute("username") String username, @RequestBody FeatParameter input)
			throws IOException {
		return JS.message(featServiceClient.debugAddFeat(username, input.getFeatName()));
	}
}
