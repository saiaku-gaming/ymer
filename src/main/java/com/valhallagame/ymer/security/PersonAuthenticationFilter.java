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

import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.Session;

@Component
public class PersonAuthenticationFilter extends GenericFilterBean {

	private static CharacterServiceClient characterServiceClient = CharacterServiceClient.get();

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
		final String token = request.getHeader("token");

		if (token == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} else {
			Optional<Session> userSession = personServiceClient.getSessionFromToken(token).getResponse();

			if (token.contains("debug") && !userSession.isPresent()) {
				RestResponse<Session> restPersonResponse = personServiceClient.createDebugPerson(token);
				if (!restPersonResponse.isOk()) {
					response.setStatus(restPersonResponse.getStatusCode().value());
					String string = JS.parse(JS.message(restPersonResponse.getErrorMessage())).toString();
					response.getWriter().write(string);
					return;
				}

				Session debugSession = restPersonResponse.getResponse().get();

				RestResponse<String> restCharacterResponse = characterServiceClient.createDebugCharacter(
						debugSession.getPerson().getUsername(), debugSession.getPerson().getUsername() + "-char");
				if (!restCharacterResponse.isOk()) {
					response.setStatus(restCharacterResponse.getStatusCode().value());
					String string = JS.parse(JS.message(restCharacterResponse.getErrorMessage())).toString();
					response.getWriter().write(string);
					return;
				}

				userSession = Optional.of(debugSession);
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
