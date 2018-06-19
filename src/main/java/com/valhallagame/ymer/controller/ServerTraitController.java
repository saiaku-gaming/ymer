package com.valhallagame.ymer.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.traitserviceclient.message.LockTraitParameter;
import com.valhallagame.traitserviceclient.message.SkillTraitParameter;
import com.valhallagame.traitserviceclient.message.UnlockTraitParameter;
import com.valhallagame.traitserviceclient.message.UnskillTraitParameter;

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

	@RequestMapping(path = "/skill-trait", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> skillTrait(@Valid @RequestBody SkillTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.skillTrait(input));
	}

	@RequestMapping(path = "/unskill-trait", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> unskillTrait(@Valid @RequestBody UnskillTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.unskillTrait(input));
	}
}
