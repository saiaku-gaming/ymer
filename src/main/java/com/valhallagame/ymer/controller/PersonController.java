package com.valhallagame.ymer.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.PersonData;
import com.valhallagame.personserviceclient.model.SessionData;
import com.valhallagame.ymer.message.UsernameParameter;
import com.valhallagame.ymer.message.UsernamePasswordParameter;

@Controller
@RequestMapping(path = "/v1/person")
public class PersonController {

	@Autowired
	private PersonServiceClient personServiceClient;

	@RequestMapping(path = "/get-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> getPerson(@RequestBody UsernameParameter username) throws IOException {
		Optional<PersonData> optPerson = personServiceClient.getPerson(username.getUsername()).getResponse();
		return optPerson.map(p -> JS.message(HttpStatus.OK, p))
				.orElse(JS.message(HttpStatus.NOT_FOUND, "COULD NOT FIND IT :("));
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> signup(@Valid @RequestBody UsernamePasswordParameter input) throws IOException {
		return JS.message(personServiceClient.signup(input.getUsername(), input.getPassword()));
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> login(@RequestBody UsernamePasswordParameter input) throws IOException {
		RestResponse<SessionData> login = personServiceClient.login(input.getUsername(), input.getPassword());
		Optional<SessionData> optSession = login.getResponse();

		return optSession.map(s -> JS.message(HttpStatus.OK, s)).orElse(JS.message(login));
	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> logout(@RequestAttribute("username") String username) throws IOException {
		RestResponse<String> logout = personServiceClient.logout(username);
		return JS.message(logout);
	}

	@RequestMapping(path = "/check-login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> checkLogin(@RequestBody UsernameParameter input) throws IOException {
		boolean loggedIn = personServiceClient.checkLogin(input.getUsername()).isOk();

		return loggedIn ? JS.message(HttpStatus.OK, "User logged in")
				: JS.message(HttpStatus.CONFLICT, "User not logged in");
	}

	@RequestMapping(path = "/username-available", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> usernameAvailable(@RequestBody UsernameParameter input) throws IOException {
		boolean available = personServiceClient.isUsernameAvailable(input.getUsername()).isOk();

		return available ? JS.message(HttpStatus.OK, "Username available")
				: JS.message(HttpStatus.CONFLICT, "Username not available");
	}

	@RequestMapping(path = "/heartbeat", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> heartbeat(@RequestAttribute("username") String username) throws IOException {
		return JS.message(personServiceClient.heartbeat(username));
	}
}
