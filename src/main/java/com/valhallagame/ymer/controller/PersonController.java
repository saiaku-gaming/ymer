package com.valhallagame.ymer.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.message.CheckLoginParameter;
import com.valhallagame.ymer.message.person.GetPersonParameter;
import com.valhallagame.ymer.message.person.LoginParameter;
import com.valhallagame.ymer.message.person.SignupParameter;
import com.valhallagame.ymer.message.person.UsernameAvailableParameter;

@Controller
@RequestMapping(path = "/v1/person")
public class PersonController {

	@Autowired
	private PersonServiceClient personServiceClient;

	@RequestMapping(path = "/get-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> getPerson(@RequestBody GetPersonParameter input) throws IOException {
		return JS.message(personServiceClient.getPerson(input.getUsername()));
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> signup(@Valid @RequestBody SignupParameter input) throws IOException {
		return JS.message(personServiceClient.signup(input.getDisplayUsername(), input.getPassword()));
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> login(@RequestBody LoginParameter input) throws IOException {
		return JS.message(personServiceClient.login(input.getDisplayUsername(), input.getPassword()));
	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> logout(@RequestAttribute("username") String username) throws IOException {
		return JS.message(personServiceClient.logout(username));
	}

	@RequestMapping(path = "/check-login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> checkLogin(@RequestBody CheckLoginParameter input) throws IOException {
		return JS.message(personServiceClient.checkLogin(input.getUsername()));
	}

	@RequestMapping(path = "/username-available", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> usernameAvailable(@RequestBody UsernameAvailableParameter input) throws IOException {
		return JS.message(personServiceClient.usernameAvailable(input.getDisplayUsername()));
	}

	@RequestMapping(path = "/heartbeat", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> heartbeat(@RequestAttribute("username") String username) throws IOException {
		return JS.message(personServiceClient.heartbeat(username));
	}
}
