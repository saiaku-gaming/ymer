package com.valhallagame.ymer.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.common.JS;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.Person;
import com.valhallagame.personserviceclient.model.Session;
import com.valhallagame.ymer.message.UsernameParameter;
import com.valhallagame.ymer.message.UsernamePasswordParameter;

@Controller
@RequestMapping(path = "/v1/person")
public class PersonController {

	@RequestMapping(path = "/get-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getPerson(@RequestBody UsernameParameter username) {
		Optional<Person> optPerson = PersonServiceClient.get().getPerson(username.getUsername()).getResponse();
		return optPerson.<ResponseEntity<?>>map(p -> JS.message(HttpStatus.OK, p))
				.orElse(JS.message(HttpStatus.NOT_FOUND, "COULD NOT FIND IT :("));
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> signup(@RequestBody UsernamePasswordParameter input) {
		Optional<Session> optSession = PersonServiceClient.get().signup(input.getUsername(), input.getPassword())
				.getResponse();

		return optSession.<ResponseEntity<?>>map(s -> JS.message(HttpStatus.OK, s))
				.orElse(JS.message(HttpStatus.CONFLICT, "The username is already taken"));
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody UsernamePasswordParameter input) {
		Optional<Session> optSession = PersonServiceClient.get().login(input.getUsername(), input.getPassword())
				.getResponse();

		return optSession.<ResponseEntity<?>>map(s -> JS.message(HttpStatus.OK, s))
				.orElse(JS.message(HttpStatus.NOT_FOUND, "The username and password combination was not accepted"));
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody UsernameParameter input) {
		boolean successful = PersonServiceClient.get().logout(input.getUsername()).isOk();

		return JS.message(HttpStatus.OK, "person logged out");
	}

	@RequestMapping(path = "/check-login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> checkLogin(@RequestBody UsernameParameter input) {
		boolean loggedIn = PersonServiceClient.get().checkLogin(input.getUsername()).isOk();

		return loggedIn ? JS.message(HttpStatus.OK, "User logged in")
				: JS.message(HttpStatus.CONFLICT, "User not logged in");
	}

	@RequestMapping(path = "/username-available", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> usernameAvailable(@RequestBody UsernameParameter input) {
		boolean available = PersonServiceClient.get().isUsernameAvailable(input.getUsername()).isOk();

		return available ? JS.message(HttpStatus.OK, "Username available")
				: JS.message(HttpStatus.CONFLICT, "Username not available");
	}
}
