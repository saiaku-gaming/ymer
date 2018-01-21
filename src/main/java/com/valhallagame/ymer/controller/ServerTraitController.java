package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.traitserviceclient.message.AddTraitParameter;

@Controller
@RequestMapping("/v1/server-wardrobe")
public class ServerTraitController {

	@Autowired
	TraitServiceClient traitServiceClient;

	@RequestMapping(path = "/add-trait", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> addWardrobeItem(@RequestBody AddTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.addTrait(input));
	}

}
