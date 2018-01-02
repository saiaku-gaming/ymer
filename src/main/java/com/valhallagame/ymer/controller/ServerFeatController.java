package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.featserviceclient.FeatServiceClient;
import com.valhallagame.ymer.message.FeatParameter;

@Controller
@RequestMapping("/v1/server-feat")
public class ServerFeatController {

	FeatServiceClient featServiceClient = FeatServiceClient.get();

	@RequestMapping(path = "/add-feat", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> addFeat(@RequestBody FeatParameter input)
			throws IOException {
		return JS.message(featServiceClient.addFeat(input.getCharacterName(), input.getFeatName()));
	}

}
