package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.notificationserviceclient.message.NotificationListenerParameter;
import com.valhallagame.notificationserviceclient.message.UnregisterNotificationListenerParameter;

@Controller
@RequestMapping(path = "/v1/server-notification")
public class ServerNotificationController {

	NotificationServiceClient notificationServiceClient = NotificationServiceClient.get();

	@RequestMapping(path = "register-notification-listener", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> registerNotificationListener(@RequestBody NotificationListenerParameter input)
			throws IOException {
		return JS.message(notificationServiceClient.registerNotificationListener(input.getGameSessionId(),
				input.getAddress(), input.getPort()));
	}

	@RequestMapping(path = "unregister-notification-listener", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> unregisterNotificationListener(@RequestBody UnregisterNotificationListenerParameter input)
			throws IOException {
		return JS.message(notificationServiceClient.unregisterNotificationListener(input.getGameSessionId()));
	}

}
