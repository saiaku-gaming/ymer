package com.valhallagame.ymer.controller;

import java.io.IOException;

import javax.validation.Valid;

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

	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> get(@RequestAttribute("username") String username) throws IOException {
		return JS.message(PartyServiceClient.get().getPartyAndInvites(username));
	}

	@RequestMapping(path = "/invite-person", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().invitePerson(username, input.getUsername()));
	}
	
	@RequestMapping(path = "/invite-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().inviteCharacter(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/cancel-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> cancelCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().cancelCharacterInvite(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/cancel-person-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> cancelPersonInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().cancelPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/accept-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> accpetInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody PartyIdParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().acceptInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/decline-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> declineInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody PartyIdParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().declineInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/leave-party", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> leaveParty(@RequestAttribute("username") String username) throws IOException {
		return JS.message(PartyServiceClient.get().leaveParty(username));
	}

	@RequestMapping(path = "/promote-person-to-leader", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> promotePersonToLeader(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().promotePersonToLeader(username, input.getUsername()));
	}
	
	@RequestMapping(path = "/promote-character-to-leader", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> promoteCharacterToLeader(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().promoteCharacterToLeader(username, input.getDisplayCharacterName().toLowerCase()));
	}
	
	@RequestMapping(path = "/kick-character-from-party", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> kickCharacterFromParty(@RequestAttribute("username") String username,
			@Valid @RequestBody DisplayCharacterNameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().kickCharacterFromParty(username, input.getDisplayCharacterName().toLowerCase()));
	}

	@RequestMapping(path = "/kick-person-from-party", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> kickPersonFromParty(@RequestAttribute("username") String username,
			@Valid @RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().kickPersonFromParty(username, input.getUsername()));
	}
}
