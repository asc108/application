package user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import user.repository.UserRepository;
import user.security.SecurityUser;

@Service
@RequiredArgsConstructor
public class JPAUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var u = userRepository.findUserByUsername(username);
		return u.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

}
