package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.ymer.message.trait.SaveTraitBarIndexParameter;

@Controller
@RequestMapping("/v1/trait")
public class TraitController {

	@Autowired
	private TraitServiceClient traitServiceClient;

	@RequestMapping(path = "/get-traits", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getTraits(@RequestAttribute("username") String username) throws IOException {
		return JS.message(traitServiceClient.getTraits(username));
	}

	@RequestMapping(path = "/save-trait-bar-index", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveTraitBarIndex(@RequestAttribute("username") String username,
			@RequestBody SaveTraitBarIndexParameter input) throws IOException {
		return JS.message(traitServiceClient.saveTraitBarIndex(username, input.getName(), input.getBarIndex()));
	}
}
