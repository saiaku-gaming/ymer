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
import com.valhallagame.skillserviceclient.SkillServiceClient;
import com.valhallagame.skillserviceclient.message.AddSkillParameter;

@Controller
@RequestMapping("/v1/server-wardrobe")
public class ServerSkillController {

	@Autowired
	SkillServiceClient skillServiceClient;

	@RequestMapping(path = "/add-skill", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> addWardrobeItem(@RequestBody AddSkillParameter input) throws IOException {
		return JS.message(skillServiceClient.addSkill(input));
	}

}
