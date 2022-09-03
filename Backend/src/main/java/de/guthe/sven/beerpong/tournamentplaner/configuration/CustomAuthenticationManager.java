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

	/**
	 * Custom authentication method to use the userRepository to fetch the user details by
	 * mail
	 * @param authentication Authentication object which holds the email (identifier) and
	 * the password
	 * @return an Authentication object if the authentication was successful
	 * @throws AuthenticationException if the account is disabled / banned or the
	 * credentials are wrong
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		// Trying to get the user details from the database
		User user = userRepository.findByEmail(email);

		// If the email does not exist in the system
		if (user == null) {
			throw new BadCredentialsException("1000");
		}
		// If the password does not match the password of the user in the database
		if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("1000");
		}
		// If the current user is not enabled
		if (!user.isEnabled()) {
			throw new DisabledException("1001");
		}

		// Return the Username Password Authentication Token (is needed for the
		// Authentication Manager)
		return new UsernamePasswordAuthenticationToken(password, password);
	}

}
