package com.valhallagame.ymer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.common.JS;

@Controller
@RequestMapping(path = "/v1/util")
public class UtilsController {

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> ping() {
		return JS.message(HttpStatus.OK, "Pong");
	}
}
