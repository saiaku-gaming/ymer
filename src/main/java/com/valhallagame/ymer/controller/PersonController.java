package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.ymer.message.person.LoginParameter;
import com.valhallagame.ymer.message.person.SignupParameter;
import com.valhallagame.ymer.message.person.SteamLoginParameter;
import com.valhallagame.ymer.message.person.UsernameAvailableParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(path = "/v1/person")
public class PersonController {
	@Autowired
	private PersonServiceClient personServiceClient;

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

    @RequestMapping(path = "/steam-login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonNode> login(@RequestBody SteamLoginParameter input) throws IOException {
        return JS.message(personServiceClient.steamLogin(input.getAuthSessionTicket()));
    }

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> logout(@RequestAttribute("username") String username) throws IOException {
		return JS.message(personServiceClient.logout(username));
	}

	@RequestMapping(path = "/username-available", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<JsonNode> usernameAvailable(@RequestBody UsernameAvailableParameter input)
			throws IOException {
		return JS.message(personServiceClient.usernameAvailable(input.getDisplayUsername()));
	}

	@RequestMapping(path = "/heartbeat", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonNode> heartbeat(@RequestAttribute("username") String username) throws IOException {
		return JS.message(personServiceClient.heartbeat(username));
	}
}
