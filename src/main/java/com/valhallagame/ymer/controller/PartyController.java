package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.partyserviceclient.PartyServiceClient;
import com.valhallagame.ymer.message.party.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/v1/party")
public class PartyController {
	private static final Logger logger = LoggerFactory.getLogger(PartyController.class);

	@Autowired
	private PartyServiceClient partyServiceClient;

	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> get(@RequestAttribute("username") String username) throws IOException {
		logger.info("Get Party called");
		return JS.message(partyServiceClient.getPartyAndInvites(username));
	}

	@RequestMapping(path = "/invite-person", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody InvitePersonParameter input) throws IOException {
		logger.info("Invite Person called with {}", input);
		return JS.message(partyServiceClient.invitePerson(username, input.getTargetUsername()));
	}

	@RequestMapping(path = "/invite-character", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> sendInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody InviteCharacterParameter input) throws IOException {
		logger.info("Invite Character called with {}", input);
		return JS.message(partyServiceClient.inviteCharacter(username, input.getDisplayCharacterName()));
	}

	@RequestMapping(path = "/cancel-character-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> cancelCharacterInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody CancelCharacterInviteParameter input) throws IOException {
		logger.info("Cancel Character Invite called with {}", input);
		return JS.message(partyServiceClient.cancelCharacterInvite(username, input.getDisplayCharacterName()));
	}

	@RequestMapping(path = "/accept-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> accpetInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody AcceptParameter input) throws IOException {
		logger.info("Accept Invite called with {}", input);
		return JS.message(partyServiceClient.acceptInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/decline-invite", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> declineInvite(@RequestAttribute("username") String username,
			@Valid @RequestBody DeclineParameter input) throws IOException {
		logger.info("Decline Invite called with {}", input);
		return JS.message(partyServiceClient.declineInvite(username, input.getPartyId()));
	}

	@RequestMapping(path = "/leave-party", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> leaveParty(@RequestAttribute("username") String username) throws IOException {
		logger.info("Leave Party called");
		return JS.message(partyServiceClient.leaveParty(username));
	}

	@RequestMapping(path = "/promote-character-to-leader", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> promoteCharacterToLeader(@RequestAttribute("username") String username,
			@Valid @RequestBody PromoteCharacterToLeaderParameter input) throws IOException {
		logger.info("Promote Character To Leader called with {}", input);
		return JS.message(partyServiceClient.promoteCharacterToLeader(username, input.getDisplayCharacterName()));
	}

	@RequestMapping(path = "/kick-character-from-party", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> kickCharacterFromParty(@RequestAttribute("username") String username,
			@Valid @RequestBody KickCharacterFromPartyParameter input) throws IOException {
		logger.info("Kick Character From Party called with {}", input);
		return JS.message(partyServiceClient.kickCharacterFromParty(username, input.getDisplayCharacterName()));
	}
}
