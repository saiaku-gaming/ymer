package com.valhallagame.ymer.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.Session;

@Component
public class PersonAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		PersonServiceClient personServiceClient = PersonServiceClient.get();

		final String auth = request.getHeader("Authorization");
		if (auth != null) {
			int indexOf = auth.indexOf(':');
			String username = auth.substring(0, indexOf);
			String password = auth.substring(indexOf + 1, auth.length());
			// unreal does a sha1 to make sure the password never leaves the
			// client.
			// So we need to do the same here.
			String sha1HexPass = DigestUtils.sha1Hex(password);
			if (personServiceClient.validateCredentials(username, sha1HexPass.toUpperCase()).isOk()) {
				request.setAttribute("username", username);
				chain.doFilter(req, res);
				return;
			}
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		final String token = request.getHeader("session");

		if (token == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} else {
			Optional<Session> userSession = personServiceClient.getSessionFromToken(token).getResponse();

			if (token.contains("debug") && !userSession.isPresent()) {
				// TODO fix this in person and character service
				personServiceClient.createDebugPerson();
				// This is needed because debug tokens have a tendency to be
				// created with conflicts that aborts stuff.
				// Person p = personService.createNewDebugPerson();
				//
				// // In case of reused name
				// sessionService.deletePersonsSession(p);
				//
				// Char ch = p.getSelectedCharacter();
				// if (ch == null) {
				// // make sure that the debug name is available.
				// characterService.deleteCharacterByName(p.getUsername());
				//
				// ObjectNode on = JsonNodeFactory.instance.objectNode();
				// on.put("characterName", p.getUsername());
				// ch = new Char(p.getUsername(), p, on);
				// characterService.save(ch);
				//
				// List<EquippedItem> equippedItems = new ArrayList<>();
				//
				// EquippedItem leatherArmor = new EquippedItem();
				// leatherArmor.setCharacter(ch);
				// leatherArmor.setItemSlot("Chest");
				// leatherArmor.setArmor("LeatherArmor");
				// equippedItemService.saveEquippedItem(leatherArmor);
				// equippedItems.add(leatherArmor);
				//
				// EquippedItem sword = new EquippedItem();
				// sword.setCharacter(ch);
				// sword.setItemSlot("Main Hand");
				// sword.setArmament("Sword");
				// equippedItemService.saveEquippedItem(sword);
				// equippedItems.add(sword);
				//
				// EquippedItem shield = new EquippedItem();
				// shield.setCharacter(ch);
				// shield.setItemSlot("Offhand");
				// shield.setArmament("MediumShield");
				// equippedItemService.saveEquippedItem(shield);
				// equippedItems.add(shield);
				//
				// p.setSelectedCharacter(ch);
				// WardrobeItem mailArmor = new WardrobeItem();
				// mailArmor.setCharacter(ch);
				// mailArmor.setName("MailArmor");
				// wardrobeService.saveWardrobeItem(mailArmor);
				//
				// WardrobeItem bow = new WardrobeItem();
				// bow.setCharacter(ch);
				// bow.setName("Bow");
				// wardrobeService.saveWardrobeItem(bow);
				//
				// WardrobeItem warhammer = new WardrobeItem();
				// warhammer.setCharacter(ch);
				// warhammer.setName("Warhammer");
				// wardrobeService.saveWardrobeItem(warhammer);
				// }
				// p.setOnline(true);
				// p = personService.save(p);
				//
				// userSession = new Session(p, token);
				// sessionService.saveSession(userSession);
			} else {

				if (!userSession.isPresent()) {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return;
				}

				// TODO might need to move this to person service?
				// if
				// (userSession.getTimestamp().isBefore(Instant.now().minus(1,
				// ChronoUnit.HOURS))) {
				// // Session timed out.
				// sessionService.deleteSession(userSession);
				// response.setStatus(HttpStatus.UNAUTHORIZED.value());
				// } else {
				// Just so that we don't write to the database every
				// second
				// for every player. It logs way to much when debugging
				// and we want to keep writes low!
				// if
				// (userSession.getTimestamp().isBefore(Instant.now().minus(2,
				// ChronoUnit.MINUTES))) {
				// userSession.setTimestamp(Instant.now());
				// sessionService.saveSession(userSession);
				// }
				// }
			}
			request.setAttribute("username", userSession.get().getPerson().getUsername());
			chain.doFilter(req, res);
		}
	}

}
