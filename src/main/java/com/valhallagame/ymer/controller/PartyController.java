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

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.ymer.message.DisplayCharacterNameParameter;
import com.valhallagame.ymer.message.PartyIdParameter;
import com.valhallagame.ymer.message.UsernameParameter;

@Controller
@RequestMapping("/v1/party")
public class PartyController {

	@Autowired
	private PartyServiceClient partyServiceClient;

	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> get(@RequestAttribute("username") String username) throws IOException {
		return JS.message(partyServiceClient.getPartyAndInvites(username));
	}

	@RequestMapping(path = "/invite-person", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(partyServiceClient.invitePerson(username, input.getUsername()));
	}

	@RequestMapping(path = "/invite-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(partyServiceClient.inviteCharacter(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/cancel-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> cancelCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(
				partyServiceClient.cancelCharacterInvite(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/cancel-person-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> cancelPersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(partyServiceClient.cancelPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/accept-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> accpetInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody PartyIdParameter input) throws IOException {
		return JS.message(partyServiceClient.acceptInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/decline-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> declineInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody PartyIdParameter input) throws IOException {
		return JS.message(partyServiceClient.declineInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/leave-party", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> leaveParty(@RequestAttribute("username") String username) throws IOException {
		return JS.message(partyServiceClient.leaveParty(username));
	}

	@RequestMapping(path = "/promote-person-to-leader", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> promotePersonToLeader(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(partyServiceClient.promotePersonToLeader(username, input.getUsername()));
	}

	@RequestMapping(path = "/promote-character-to-leader", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> promoteCharacterToLeader(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(
				partyServiceClient.promoteCharacterToLeader(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/kick-character-from-party", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> kickCharacterFromParty(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(
				partyServiceClient.kickCharacterFromParty(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/kick-person-from-party", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> kickPersonFromParty(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(partyServiceClient.kickPersonFromParty(username, input.getUsername()));
	}
}
