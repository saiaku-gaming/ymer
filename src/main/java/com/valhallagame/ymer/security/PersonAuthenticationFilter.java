package com.valhallagame.ymer.security;

import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.model.CharacterData;
import com.valhallagame.common.JS;
import com.valhallagame.common.RestResponse;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.SessionData;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class PersonAuthenticationFilter extends GenericFilterBean {

	@Autowired
	private CharacterServiceClient characterServiceClient;

	@Autowired
	private PersonServiceClient personServiceClient;

    @Autowired
    private Environment environment;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		final String auth = request.getHeader("Authorization");
		if (auth != null) {
			int indexOf = auth.indexOf(':');
			String username = auth.substring(0, indexOf);
            String password = auth.substring(indexOf + 1);
			// unreal does a sha1 to make sure the password never leaves the
			// client.
			// So we need to do the same here.
			String sha1HexPass = DigestUtils.sha1Hex(password);
			if (personServiceClient.validateCredentials(username, sha1HexPass.toUpperCase()).isOk()) {
				MDC.put("username", username);
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
			Optional<SessionData> userSession = personServiceClient.getSessionFromToken(token).getResponse();

            boolean production = Arrays.asList(environment.getActiveProfiles()).contains("production");
            if (token.contains("debug") && !userSession.isPresent() && !production) {
				userSession = createDebugSession(response, token, request.getHeader("singleton"));
				if (!userSession.isPresent()) {
					return;
				}
			} else {
                if (token.contains("debug") && production) {
                    throw new ServletException("No debug tokens allowed in production environment!");
                }

				if (!userSession.isPresent()) {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return;
				}
			}
			request.setAttribute("username", userSession.get().getPerson().getUsername());
            MDC.put("username", userSession.get().getPerson().getUsername());
            MDC.put("token", token);

			chain.doFilter(req, res);
		}
	}

	private Optional<SessionData> createDebugSession(HttpServletResponse response, String token, String singleton) throws IOException {
		RestResponse<SessionData> sessionResp = personServiceClient.createDebugPerson(token, singleton);
		Optional<SessionData> sessionOpt = sessionResp.get();
		if (!sessionOpt.isPresent()) {
			response.setStatus(sessionResp.getStatusCode().value());
			String string = JS.parse(JS.message(sessionResp.getErrorMessage())).toString();
			response.getWriter().write(string);
			return Optional.empty();
		}

		SessionData debugSession = sessionOpt.get();

        RestResponse<CharacterData> restCharacterResponse = characterServiceClient.createDebugCharacter(
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
