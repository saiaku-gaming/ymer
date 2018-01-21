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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.skillserviceclient.SkillServiceClient;

@Controller
@RequestMapping("/v1/skill")
public class SkillController {

	@Autowired
	SkillServiceClient skillServiceClient;

	@RequestMapping(path = "/get-skills", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getSkills(@RequestAttribute("username") String username) throws IOException {
		
		RestResponse<List<String>> skillsResp = skillServiceClient.getSkills(username);
		
		if(skillsResp.isOk()) {
			Optional<List<String>> opt = skillsResp.get();
			if(opt.isPresent()) {
				List<String> list = opt.get();
				Map<String, List<String>> out = new HashMap<>();
				out.put("skills", list);
				return JS.message(HttpStatus.OK, out);	
			}
		} 
		return JS.message(skillsResp);
	}
}
