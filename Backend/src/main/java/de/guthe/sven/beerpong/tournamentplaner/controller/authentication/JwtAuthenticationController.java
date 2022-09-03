package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.configuration.JwtTokenUtil;
import de.guthe.sven.beerpong.tournamentplaner.configuration.MyUserDetailsService;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.JwtRequestDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.JwtResponseDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication")
public class JwtAuthenticationController {

	final private JwtTokenUtil jwtTokenUtil;

	final private MyUserDetailsService userDetailsService;

	final private UserRepository userRepository;

	final private AuthenticationManager authenticationManager;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param jwtTokenUtil service to handle everything regarding the json web tokens
	 * @param userDetailsService service to handle all the transformations / database
	 * queries regarding the user details
	 * @param userRepository jpa repository to handle the database queries directly in
	 * this controller
	 * @param authenticationManager authentication manager (in our case the
	 * CustomAuthenticationManager to handle the authentication TODO: Think about the
	 * possibility to move this logic to a service
	 */
	@Autowired
	public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil, MyUserDetailsService userDetailsService,
			UserRepository userRepository, AuthenticationManager authenticationManager) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
	}

	final private Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	/**
	 * Route to log in
	 * @param authenticationRequest Data Transfer Object consists of a username and a
	 * password
	 * @return the JWT Token (to store it locally) and the User Data Transfer Object of
	 * the user profile
	 * @throws Exception if the account is disabled / banned or the credentials are wrong
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JwtResponseDTO createAuthenticationToken(@RequestBody @NotNull JwtRequestDTO authenticationRequest)
			throws Exception {

		logger.info("Trying to login user " + authenticationRequest.getUsername());

		// Authenticate with the username and password
		Authentication authentication = authenticate(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		logger.info("Login Successful. Receiving name: " + authentication.getName());

		// Fetching the User Details from the database
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		User user = userRepository.findByEmail(userDetails.getUsername());

		// Generate a JWT Token for the session
		final String token = jwtTokenUtil.generateToken(userDetails);

		// Create the response (Data Transfer Object with the jwt token and the user
		// information)
		return new JwtResponseDTO(token, new UserDTO(user));

	}

	/**
	 * Function to log in via the authenticationManager
	 * @param username username (important! the username = email)
	 * @param password password (clear)
	 * @return the Authentication object
	 * @throws Exception if the account is disabled / banned or the credentials are wrong
	 * TODO: Change naming from username to password
	 */
	private Authentication authenticate(String username, String password) throws Exception {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		// If the account is disabled / banned
		catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		// If the credentials are wrong
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * Route to fetch the currently authenticated user
	 * @return the Data Transfer Object of the current user
	 */
	@GetMapping("/authenticateduser")
	public UserDTO getLogin() {
		// Get principal from the Security Context Holder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		// Normally, the principal should be an instance of UserDetails. If this is the
		// case
		// cast the principal and return the username (!Important userName = email)
		// Otherwise use the principal and "cast" it to a String
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		else {
			username = principal.toString();
		}

		// Return the Data Transfer Object of the User with a given email
		return new UserDTO(userRepository.findByEmail(username));
	}

}