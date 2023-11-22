/*package user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Component	
public class KeycloakLogoutHandler implements LogoutHandler{
	@Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
	@Autowired
	private final RestTemplate restTemplate;

	    public void logout(HttpServletRequest request, HttpServletResponse response, 
	      Authentication auth) {
	        logoutFromKeycloak((OidcUser) auth.getPrincipal());
	    }
	    private void logoutFromKeycloak(OidcUser user) {
	        String endSessionEndpoint = user.getIssuer() + "/protocol/openid-connect/logout";
	        UriComponentsBuilder builder = UriComponentsBuilder
	          .fromUriString(endSessionEndpoint)
	          .queryParam("id_token_hint", user.getIdToken().getTokenValue());

	        ResponseEntity<String> logoutResponse = restTemplate.getForEntity(
	        builder.toUriString(), String.class);
	        if (logoutResponse.getStatusCode().is2xxSuccessful()) {
	            log.info("Successfulley logged out from Keycloak");
	        } else {
	            log.error("Could not propagate logout to Keycloak");
	        }
	    }
}*/