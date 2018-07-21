package com.valhallagame.ymer.controller;

import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    NotificationServiceClient notificationServiceClient;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> ping() throws IOException {
		try {
			notificationServiceClient.ping();
		} catch (IOException e){
			logger.warn("Notification service seems down", e);
			return JS.message(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		return JS.message(HttpStatus.OK, "pong");
	}
}
