package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.notificationserviceclient.message.NotificationListenerParameter;
import com.valhallagame.notificationserviceclient.message.UnregisterNotificationListenerParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(path = "/v1/server-notification")
public class ServerNotificationController {
	private static final Logger logger = LoggerFactory.getLogger(ServerNotificationController.class);

	@Autowired
	private NotificationServiceClient notificationServiceClient;

	@RequestMapping(path = "register-notification-listener", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> registerNotificationListener(@RequestBody NotificationListenerParameter input)
			throws IOException {
		logger.info("Register Notification Listener called with {}", input);
		return JS.message(notificationServiceClient.registerNotificationListener(input.getGameSessionId(),
				input.getAddress(), input.getPort()));
	}

	@RequestMapping(path = "unregister-notification-listener", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> unregisterNotificationListener(
			@RequestBody UnregisterNotificationListenerParameter input) throws IOException {
		logger.info("Unregister Notification Listener called with {}", input);
		return JS.message(notificationServiceClient.unregisterNotificationListener(input.getGameSessionId()));
	}

}
