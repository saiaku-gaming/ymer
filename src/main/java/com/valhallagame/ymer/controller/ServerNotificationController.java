package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.common.JS;
import com.valhallagame.notificationserviceclient.NotificationServiceClient;
import com.valhallagame.notificationserviceclient.model.NotificationListenerParameter;

@Controller
@RequestMapping(path = "/v1/server-notification")
public class ServerNotificationController {

	@Autowired
	NotificationServiceClient notificationServiceClient;

	@RequestMapping(path = "register-notification-listener", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> registerNotificationListener(@RequestBody NotificationListenerParameter input)
			throws IOException {
		return JS.message(notificationServiceClient.registerNotificationListener(input.getAddress(), input.getPort()));
	}

	@RequestMapping(path = "unregister-notification-listener", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> unregisterNotificationListener(@RequestBody NotificationListenerParameter input)
			throws IOException {
		return JS
				.message(notificationServiceClient.unregisterNotificationListener(input.getAddress(), input.getPort()));
	}

}
