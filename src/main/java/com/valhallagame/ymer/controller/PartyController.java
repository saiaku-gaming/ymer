package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valhallagame.common.JS;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.ymer.message.PartyIdParameter;
import com.valhallagame.ymer.message.UsernameParameter;

@Controller
@RequestMapping("/v1/party")
public class PartyController {

	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> get(@RequestAttribute("username") String username) throws IOException {
		return JS.message(PartyServiceClient.get().getPartyAndInvites(username));
	}

	@RequestMapping(path = "/invite-person", method = RequestMethod.POST)
	public ResponseEntity<?> sendInvite(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().invitePerson(username, input.getUsername()));
	}

	@RequestMapping(path = "/cancel-person-invite", method = RequestMethod.POST)
	public ResponseEntity<?> cancelPersonInvite(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().cancelPersonInvite(username, input.getUsername()));
	}

	@RequestMapping(path = "/accept-invite", method = RequestMethod.POST)
	public ResponseEntity<?> accpetInvite(@RequestAttribute("username") String username,
			@RequestBody PartyIdParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().acceptInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/decline-invite", method = RequestMethod.POST)
	public ResponseEntity<?> declineInvite(@RequestAttribute("username") String username,
			@RequestBody PartyIdParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().declineInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/leave-party", method = RequestMethod.GET)
	public ResponseEntity<?> leaveParty(@RequestAttribute("username") String username) throws IOException {
		return JS.message(PartyServiceClient.get().leaveParty(username));
	}

	@RequestMapping(path = "/promote-to-leader", method = RequestMethod.GET)
	public ResponseEntity<?> leaveParty(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().promoteToLeader(username, input.getUsername()));
	}

	@RequestMapping(path = "/kick-from-party", method = RequestMethod.GET)
	public ResponseEntity<?> kickFromParty(@RequestAttribute("username") String username,
			@RequestBody UsernameParameter input) throws IOException {
		return JS.message(PartyServiceClient.get().kickFromParty(username, input.getUsername()));
	}
}
