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
	private static PersonServiceClient personServiceClient = PersonServiceClient.get();

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;


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
				userSession = createDebugSession(response, token);
				if(!userSession.isPresent()){
					return;
				}
			} else {
				if (!userSession.isPresent()) {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return;
				}
			}
			request.setAttribute("username", userSession.get().getPerson().getUsername());
			chain.doFilter(req, res);
		}
	}

	private Optional<Session> createDebugSession(HttpServletResponse response, String token) throws IOException {
		RestResponse<Session> sessionResp = personServiceClient.createDebugPerson(token);
		Optional<Session> sessionOpt = sessionResp.get();
		if (!sessionOpt.isPresent()) {
			response.setStatus(sessionResp.getStatusCode().value());
			String string = JS.parse(JS.message(sessionResp.getErrorMessage())).toString();
			response.getWriter().write(string);
			return Optional.empty();
		}

		Session debugSession = sessionOpt.get();

		RestResponse<String> restCharacterResponse = characterServiceClient.createDebugCharacter(
				debugSession.getPerson().getUsername(), debugSession.getPerson().getUsername() + "-char");
		if (!restCharacterResponse.isOk()) {
			response.setStatus(restCharacterResponse.getStatusCode().value());
			String string = JS.parse(JS.message(restCharacterResponse.getErrorMessage())).toString();
			response.getWriter().write(string);
			return Optional.empty();
		}

		return Optional.of(debugSession);
	}

}
