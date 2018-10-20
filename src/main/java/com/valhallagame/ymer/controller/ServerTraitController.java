package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import com.valhallagame.traitserviceclient.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;

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

	@RequestMapping(path = "/specialize-trait", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> specializeTrait(@Valid @RequestBody SpecializeTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.specializeTrait(input));
	}

	@RequestMapping(path = "/unspecialize-trait", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> unspecializeTrait(@Valid @RequestBody UnspecializeTraitParameter input) throws IOException {
		return JS.message(traitServiceClient.unspecializeTrait(input));
	}
}
