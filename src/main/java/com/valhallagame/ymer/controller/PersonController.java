package com.valhallagame.ymer.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.Person;
import com.valhallagame.ymer.message.UsernameParameter;
import com.valhallagame.ymer.util.JS;

@Controller
@RequestMapping(path = "/v1/person")
public class PersonController {

	@RequestMapping(path = "/get-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getPerson(@RequestBody UsernameParameter username) {
		Optional<Person> optPerson = PersonServiceClient.get().getPerson(username.getUsername());
		return optPerson.<ResponseEntity<?>>map(p -> JS.message(HttpStatus.OK, p))
				.orElse(JS.message(HttpStatus.NOT_FOUND, "COULD NOT FIND IT :("));
	}
}
