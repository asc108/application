package user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
	// private final KeycloakLogoutHandler logoutHandler;

	/*
	 * @Bean protected SessionAuthenticationStrategy sessionAuthenticationStrategy()
	 * { return new RegisterSessionAuthenticationStrategy(new
	 * SessionRegistryImpl()); }
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(e -> e.disable()).authorizeHttpRequests(e -> 
			e.requestMatchers("api/users/register").permitAll().anyRequest().authenticated()
		).httpBasic(Customizer.withDefaults());
		return http.build();

	}

	/*
	 * @Order(1)
	 * 
	 * @Bean public SecurityFilterChain clientFilterChain(HttpSecurity http) throws
	 * Exception { http.authorizeHttpRequests(e ->
	 * e.requestMatchers("/").permitAll().anyRequest().authenticated())
	 * .oauth2Login(Customizer.withDefaults()) .logout((logout) ->
	 * logout.addLogoutHandler(logoutHandler).logoutSuccessUrl("/")); return
	 * http.build(); // http.oauth2Login(Customizer.withDefaults()); //
	 * http.logout(e -> e.addLogoutHandler(logoutHandler)));
	 * 
	 * }
	 * 
	 * @Order(2)
	 * 
	 * @Bean public SecurityFilterChain resourceServerFilterChain(HttpSecurity http)
	 * throws Exception { http.authorizeHttpRequests(e -> {
	 * e.requestMatchers("/api/users/register").permitAll();
	 * e.requestMatchers("/api/users/allUsers").hasRole("USER").anyRequest().
	 * authenticated(); });
	 * 
	 * http.oauth2ResourceServer(e -> e.jwt(Customizer.withDefaults())); return
	 * http.build(); }
	 */
	/*
	 * @Bean public AuthenticationManager authenticationManager(HttpSecurity http)
	 * throws Exception { return
	 * http.getSharedObject(AuthenticationManagerBuilder.class).build(); }
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
