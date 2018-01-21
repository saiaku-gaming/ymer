package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.traitserviceclient.message.UpdateTraitBarIndexParameter;

@Controller
@RequestMapping("/v1/trait")
public class TraitController {

	@Autowired
	TraitServiceClient traitServiceClient;

	@RequestMapping(path = "/get-traits", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getTraits(@RequestAttribute("username") String username) throws IOException {

		RestResponse<List<String>> traitsResp = traitServiceClient.getTraits(username);

		if (traitsResp.isOk()) {
			Optional<List<String>> opt = traitsResp.get();
			if (opt.isPresent()) {
				List<String> list = opt.get();
				Map<String, List<String>> out = new HashMap<>();
				out.put("traits", list);
				return JS.message(HttpStatus.OK, out);
			}
		}
		return JS.message(traitsResp);
	}

	@RequestMapping(path = "/update-trait-bar-index", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> updateTraitBarIndex(@RequestAttribute("username") String username,
			@RequestBody UpdateTraitBarIndexParameter input) throws IOException {
		input.setUsername(username);
		return JS.message(traitServiceClient.updateTraitBarIndex(input));
	}
}
