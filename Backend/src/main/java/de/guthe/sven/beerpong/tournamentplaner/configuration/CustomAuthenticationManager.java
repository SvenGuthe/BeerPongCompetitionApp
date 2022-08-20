package de.guthe.sven.beerpong.tournamentplaner.configuration;

import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new BadCredentialsException("1000");
		}
		if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("1000");
		}
		if (!user.isEnabled()) {
			throw new DisabledException("1001");
		}

		return new UsernamePasswordAuthenticationToken(password, password);
	}

}
