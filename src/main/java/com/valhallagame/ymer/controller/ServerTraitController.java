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
import com.valhallagame.traitserviceclient.message.LockTraitParameter;
import com.valhallagame.traitserviceclient.message.UnlockTraitParameter;

@Controller
@RequestMapping("/v1/server-trait")
public class ServerTraitController {

	@Autowired
	TraitServiceClient traitServiceClient;

	@RequestMapping(path = "/unlock-trait", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> unlockTrait(@RequestBody UnlockTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.unlockTrait(input));
	}

	@RequestMapping(path = "/lock-trait", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> lockTrait(@RequestBody LockTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.lockTrait(input));
	}
}
