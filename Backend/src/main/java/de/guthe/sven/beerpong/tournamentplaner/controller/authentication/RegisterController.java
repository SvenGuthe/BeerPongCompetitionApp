package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.user.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserRegistrationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.*;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationToken;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationTokenHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.team.*;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamComposition;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.ConfirmationTokenRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.EmailSenderService;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamCompositionStatusService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class RegisterController {

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	private final UserStatusRepository userStatusRepository;

	private final RoleRepository roleRepository;

	private final TeamStatusRepository teamStatusRepository;

	private final ConfirmationTokenRepository confirmationTokenRepository;

	private final TeamCompositionStatusService teamCompositionStatusService;

	private final EmailSenderService emailSenderService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param userRepository jpa repository to handle all database queries directly in
	 * this controller regarding the user information
	 * @param roleRepository jpa repository to handle all database queries directly in
	 * this controller regarding the roles
	 * @param confirmationTokenRepository jpa repository to handle all database queries
	 * directly in this controller regarding the confirmation token
	 * @param emailSenderService service to handle all E-Mail logic
	 * @param passwordEncoder PasswordEncoder defined in the WebSecurityConfig ->
	 * BCryptPasswordEncoder
	 * @param teamStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the team status
	 * @param teamCompositionStatusService service to handle all the transformations /
	 * database queries regarding the team composition (user <-> team)
	 * @param userStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the user
	 */
	@Autowired
	public RegisterController(UserRepository userRepository, RoleRepository roleRepository,
			ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService,
			PasswordEncoder passwordEncoder, TeamStatusRepository teamStatusRepository,
			TeamCompositionStatusService teamCompositionStatusService, UserStatusRepository userStatusRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.teamStatusRepository = teamStatusRepository;
		this.teamCompositionStatusService = teamCompositionStatusService;
		this.userStatusRepository = userStatusRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(RegisterController.class);

	/**
	 * Route to register a new user
	 * @param userRegistrationDTO data transfer object including all necessary information
	 * to create a user (e.g. username / email and password)
	 * @return the created user information as a data transfer object important to know:
	 * this user will be not available at first, because he has to click the link in the
	 * generated email to activate his account
	 */
	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody @NotNull UserRegistrationDTO userRegistrationDTO) {

		logger.info("Received a user registration request: " + userRegistrationDTO);

		// TODO: Most of the logic should be moved to a service
		// TODO: Why is the User Status Type set to ACTIVE?
		// Create a new UserStatus and set the User Status Type = ACTIVE
		UserStatus userStatus = userStatusRepository.findByStatus(UserStatusType.ACTIVE.name())
				.orElse(new UserStatus(UserStatusType.ACTIVE));

		// Transfer the information provided by the real user in the body of the request
		// to the internal
		// user class and encode the password with the password encoder
		User user = new User();
		user.setEmail(userRegistrationDTO.getEmail());
		user.setFirstName(userRegistrationDTO.getFirstName());
		user.setLastName(userRegistrationDTO.getLastName());
		user.setGamerTag(userRegistrationDTO.getGamerTag());
		user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

		// Create a first entry of the User Status History with the Active Status as the
		// current one
		UserStatusHistory userStatusHistory = new UserStatusHistory();
		userStatusHistory.setUser(user);
		userStatusHistory.setUserStatus(userStatus);
		user.addUserStatusHistory(userStatusHistory);

		// Check, if there is already a user with this email address (this is not allowed,
		// because it is our identifier)
		// TODO: Change logic to send an Error to the user that the email is already used
		// TODO: Move this logic in the beginning of this controller
		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null) {
			logger.error("The email " + user.getEmail() + " already exists");
			return new UserDTO(checkUser);
		}
		else {
			// Query the role ROLE_PLAYER from the database
			// we check here if the role was found?
			Optional<Role> playerRole = roleRepository.findByName(SecurityRole.ROLE_PLAYER.toString());

			if (playerRole.isEmpty()) {
				throw new RuntimeException("ROLE_PLAYER is not available in the database.");
			}

			// Create the new User Role with the fetched role entity
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(playerRole.get());
			user.addUserRole(userRole);

			// Create a new Team Status for the single player "team" and set the status to
			// INACTIVE
			TeamStatus teamStatus = teamStatusRepository.findByStatus(TeamStatusType.INACTIVE.name())
					.orElse(new TeamStatus(TeamStatusType.INACTIVE));

			// Create the new Team with name = gamerTag
			// TODO: I think we have to set password for the team as optional
			Team team = new Team();
			team.setTeamName(user.getGamerTag());
			team.setPlayerTeam(true);
			team.setPassword("");
			team.addTeamStatus(teamStatus);

			// Link the single player "team" with the user
			TeamCompositionStatus teamCompositionStatus = teamCompositionStatusService
					.getOrCreateTeamCompositionStatus(TeamCompositionStatusType.PROMISED);
			user.addTeam(team, true, teamCompositionStatus);

			// Create a new Confirmation Token for the user to activate the account
			ConfirmationToken confirmationToken = new ConfirmationToken();
			ConfirmationTokenHistory confirmationTokenHistory = new ConfirmationTokenHistory(user, confirmationToken);
			user.addConfirmationTokenHistory(confirmationTokenHistory);

			logger.info("Save the new user to the database: " + user);

			// Save now the user to the database
			userRepository.save(user);

			// Create an E-Mail (and use the gmail account of me) with the confirmation
			// token to activate the account
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(user.getEmail());
			simpleMailMessage.setSubject("Complete Registration!");
			simpleMailMessage.setFrom("svenguthe@gmail.com");
			simpleMailMessage.setText("Click here: http://localhost:3000/confirm/result?token="
					+ confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(simpleMailMessage);

			logger.info("Confirmation E-Mail was sent to " + user.getEmail());

			// Send the created user back as a response
			return new UserDTO(user);
		}
	}

	/**
	 * Route to activate the user account
	 * @param token the token will be the confirmation token created within the
	 * registration process
	 * @return the data transfer object with the user information
	 */
	@GetMapping("/confirm-account")
	public UserDTO confirmUser(@RequestParam String token) {

		logger.info("Try to confirm the account with confirmation-token = " + token);

		// TODO: Most of the logic should be moved to a service
		// Try to find the confirmation token in the database provided in the request
		// parameter
		// TODO: Implement a logic, that the confirmation token could expire after a
		// period of time
		// TODO: And also think about the flow, what a user could do if the token is
		// expired (maybe recreate an other token and send it again)
		// TODO: Check also if the account for the confirmation token is already activated
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

		// If the token was found, then continue with the processing
		// If this was not the case, just return null
		// TODO: Send an error message back to the user with the information, that the
		// confirmation token does not exists
		if (token != null) {
			// Get the history of the confirmation token and use this one, with getValidTo
			// == null
			// TODO: Change the logic, that maybe use the token, where the timestamp now <
			// getValidTo()
			List<ConfirmationTokenHistory> wholeConfirmationTokenHistories = confirmationToken
					.getConfirmationTokenHistory();
			List<ConfirmationTokenHistory> currentValidConfirmationTokenHistory = wholeConfirmationTokenHistories
					.stream().filter(singleHistory -> singleHistory.getValidTo() == null).collect(Collectors.toList());

			if (currentValidConfirmationTokenHistory.isEmpty()) {
				throw new RuntimeException("The confirmation history is empty for the selected user");
			}

			ConfirmationTokenHistory currentValidConfirmationToken = currentValidConfirmationTokenHistory.get(0);

			// Get the user with the confirmation token
			User user = currentValidConfirmationToken.getUser();

			// Invalidate the confirmation token with the current timestamp
			List<ConfirmationTokenHistory> newConfirmationTokenHistories = wholeConfirmationTokenHistories.stream()
					.peek(singleHistory -> {
						if (singleHistory.getValidTo() == null) {
							singleHistory.setValidTo(new Timestamp(System.currentTimeMillis()));
						}
					}).collect(Collectors.toList());

			// Enable the user
			user.setEnabled(true);
			user.setConfirmationTokenHistories(newConfirmationTokenHistories);

			// Set the Team Status of the single player "team" to ACTIVE
			TeamStatus teamStatusActive;
			List<TeamStatus> teamStatusActiveDatabase = teamStatusRepository.findByDescription(TeamStatusType.ACTIVE);

			if (teamStatusActiveDatabase.size() == 0) {
				TeamStatus teamStatus = new TeamStatus();
				teamStatus.setTeamStatusDescription(TeamStatusType.ACTIVE);
				teamStatusActive = teamStatus;
			}
			else if (teamStatusActiveDatabase.size() == 1) {
				teamStatusActive = teamStatusActiveDatabase.get(0);
			}
			else {
				throw new RuntimeException("There is a duplicate of the team status description in the database.");
			}

			List<TeamComposition> teamCompositions = user.getTeamCompositions().stream().peek(teamComposition -> {
				if (teamComposition.getTeam().isPlayerTeam()) {
					Team team = teamComposition.getTeam();
					// Invalidate the old Team Status
					List<TeamStatusHistory> teamStatusHistories = team.getTeamStatusHistories().stream()
							.peek(teamStatusHistory -> {
								if (teamStatusHistory.getValidTo() == null) {
									teamStatusHistory.setValidTo(new Timestamp(System.currentTimeMillis()));
								}
							}).collect(Collectors.toList());

					// Activate the new Team Status
					TeamStatusHistory newTeamStatusHistory = new TeamStatusHistory();
					newTeamStatusHistory.setTeamStatus(teamStatusActive);
					newTeamStatusHistory.setTeam(teamComposition.getTeam());

					teamStatusHistories.add(newTeamStatusHistory);

					team.setTeamStatusHistories(teamStatusHistories);

					teamComposition.setTeam(team);

				}
			}).collect(Collectors.toList());

			user.setTeamCompositions(teamCompositions);

			logger.info("Update the activated user: " + user);

			// Save the changes of the user
			userRepository.save(user);

			// Return the updated user information via the data transfer object
			return new UserDTO(user);
		}
		else {
			logger.error("There was no confirmation token in the database");
			return null;
		}
	}

}
