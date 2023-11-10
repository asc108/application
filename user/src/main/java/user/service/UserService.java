package user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ampqp.RabbitMQMessageProducer;
import feignclients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.controller.UserRegistrationRequest;
import user.model.Users;
import user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final RabbitMQMessageProducer r;

	public void registerUser(UserRegistrationRequest registration) {
		Users user = Users.builder().firstName(registration.firstname()).lastName(registration.lastname())
				.email(registration.email()).username(registration.username()).build();
		user.setPassword(passwordEncoder().encode(registration.password()));
		user.setRole("USER");
		Optional<Users> userCheck = userRepository.findUserByUsername(user.getUsername());

		if (userCheck.isEmpty()) {
			userRepository.saveAndFlush(user);
			NotificationRequest n = new NotificationRequest(user.getUsername(),
					String.format("Hi %s,Welcome!", user.getFirstName()));
			r.publish(n, "internal.exchange", "internal.notification.routing-key");

		} else {
			throw new IllegalArgumentException("Already registered");
		}

	}

	public List<Users> getAllUsers() {
		List<Users> users = userRepository.findAll();
		return users;
	}

	public void removeUser(String username) {
		Optional<Users> user = userRepository.findUserByUsername(username);
		if (user.isPresent()) {
			log.info("found");
			userRepository.delete(user.get());
		} else {
			throw new IllegalArgumentException("Not found user with that username");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findUserByUsername(username).get();
		if(user == null) {
			throw new UsernameNotFoundException("User with username :" + username + "not found");
		}
		return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
		}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
