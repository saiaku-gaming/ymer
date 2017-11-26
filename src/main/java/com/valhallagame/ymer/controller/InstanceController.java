package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.instanceserviceclient.InstanceServiceClient;

@Controller
@RequestMapping("/v1/instance")
public class InstanceController {
	
	private static InstanceServiceClient instanceServiceClient = InstanceServiceClient.get();

	//Return the instance that the user should be in. NOT the instance the user is actually in.
	@RequestMapping(path = "/get-selected-instance", method = RequestMethod.POST)
	public ResponseEntity<?> getSelectedInstance(@RequestAttribute("username") String username) throws IOException {
		return JS.message(instanceServiceClient.getSelectedInstance(username));
	}
}
