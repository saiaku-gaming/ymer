package com.valhallagame.ymer.controller;

import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.traitserviceclient.TraitServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;

import java.io.IOException;

@Controller
public class RootController {
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> ping() throws IOException {
		NotificationServiceClient.get().ping();
		return JS.message(HttpStatus.OK, "pong");
	}
}
