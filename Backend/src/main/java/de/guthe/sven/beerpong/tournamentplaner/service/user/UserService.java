package de.guthe.sven.beerpong.tournamentplaner.service.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.confirmationtoken.ConfirmationTokenAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.UserTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.confirmationtoken.ConfirmationTokenDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.*;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationToken;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationTokenHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final ConfirmationTokenRepository confirmationTokenRepository;

	private final ConfirmationTokenHistoryRepository confirmationTokenHistoryRepository;

	private final UserStatusService userStatusService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param userRepository jpa repository to handle all database queries directly in
	 * this controller regarding the user
	 * @param confirmationTokenRepository jpa repository to handle all database queries
	 * directly in this controller regarding the confirmation token
	 * @param userStatusService jpa repository to handle all database queries directly in
	 * this controller regarding the user status
	 * @param confirmationTokenHistoryRepository jpa repository to handle all database
	 * queries directly in this controller regarding the confirmation token history
	 */
	@Autowired
	public UserService(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository,
			UserStatusService userStatusService,
			ConfirmationTokenHistoryRepository confirmationTokenHistoryRepository) {
		this.userRepository = userRepository;
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.userStatusService = userStatusService;
		this.confirmationTokenHistoryRepository = confirmationTokenHistoryRepository;
	}

	/**
	 * Function to transform a User to the Data Transfer Object of the user details
	 * @param id the User ID
	 * @return the Data Transfer Object of the User Details
	 * @throws Exception if there was no user with the id TODO: Check if it is better to
	 * throw an exception or use Optional
	 */
	public UserDetailDTO transformUserToUserDetailDTO(Long id) throws Exception {

		// Trying to fetch the user with the given id
		Optional<User> user = userRepository.findById(id);

		// If user was present then create the Data Transfer Object
		if (user.isPresent()) {
			return transformUserToUserDetailDTO(user.get());
		}
		// If the team does not exist, throw an error
		else {
			throw new Exception("User not found with id " + id);
		}
	}

	/**
	 * Function to transform a User to the Data Transfer Object of the user details
	 * @param user the User object of the model class
	 * @return the User Details
	 */
	public UserDetailDTO transformUserToUserDetailDTO(User user) {

		// Create the Data Transfer Object of the User
		UserDTO userDTO = new UserDTO(user);

		// Create a list of User Teams from the team compositions
		// TODO: do we have to check the status here?
		Collection<UserTeamDTO> userTeams = user.getTeamCompositions().stream().map(UserTeamDTO::new)
				.collect(Collectors.toList());

		// Create a list of Competitions from the competition admins
		// TODO: do we have to check the status here?
		Collection<CompetitionDTO> competitionsWhereAdmin = user.getCompetitionAdmins().stream()
				.map(CompetitionAdmin::getCompetition).map(CompetitionDTO::new).collect(Collectors.toList());

		// Create a list of Competitions from the competition players
		// TODO: do we have to check the status here?
		Collection<CompetitionDTO> competitionsWherePlayer = user.getCompetitionPlayers().stream()
				.map(competitionPlayer -> new CompetitionDTO(competitionPlayer.getCompetitionTeam().getCompetition()))
				.collect(Collectors.toList());

		// Return the user details as a Data Transfer Object
		return new UserDetailDTO(userDTO, userTeams, competitionsWhereAdmin, competitionsWherePlayer);
	}

	/**
	 * Update the user
	 * @param userUpdateDTO values which should be updated
	 * @return the updated Data Transfer Object of the user
	 */
	public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {

		// Trying to fetch the user with the id which was given through the User Update
		// DTO
		Optional<User> user = userRepository.findById(userUpdateDTO.getId());

		if (user.isEmpty()) {
			throw new RuntimeException("User not present with given id " + userUpdateDTO.getId());
		}

		User singleUser = user.get();

		// Set the new values
		singleUser.setFirstName(userUpdateDTO.getFirstName());
		singleUser.setLastName(userUpdateDTO.getLastName());
		singleUser.setGamerTag(userUpdateDTO.getGamerTag());
		singleUser.setEmail(userUpdateDTO.getEmail());
		singleUser.setEnabled(userUpdateDTO.isEnabled());

		/*
		 * TODO: Handle Update Roles separate in Front- and Backend TODO: Do we need the
		 * ability to change the Role-Privilege assignment Collection<Role> roles =
		 * userUpdateDTO.getRoles().stream().map(securityRole ->
		 * roleRepository.findByName(securityRole.name()) ).collect(Collectors.toList());
		 *
		 * user.setRoles(roles);
		 */

		UserStatus newUserStatus = userStatusService.getOrCreateUserStatus(userUpdateDTO.getUserStatusType());

		/*
		 * TODO: Handle Update UserStatus separate in Front- and Backend
		 * user.setUserStatus(newUserStatus);
		 */

		userRepository.save(singleUser);

		return new UserDTO(singleUser);

	}

	/**
	 * Add a new confirmation token to the current user
	 * @param confirmationTokenAddDTO the Data Transfer Object with the id of the user and
	 * the new confirmation token
	 * @return the created confirmation token
	 */
	public ConfirmationTokenDTO addConfirmationToken(ConfirmationTokenAddDTO confirmationTokenAddDTO) {

		// Trying to fetch the user with the user-id which was given through the
		// Confirmation Token Add DTO
		Optional<User> user = userRepository.findById(confirmationTokenAddDTO.getId());

		if (user.isEmpty()) {
			throw new RuntimeException("User not present with given id " + confirmationTokenAddDTO.getId());
		}

		User singleUser = user.get();

		// Create the new confirmation token and store it
		ConfirmationToken confirmationToken = new ConfirmationToken(confirmationTokenAddDTO.getConfirmationToken());
		confirmationTokenRepository.save(confirmationToken);

		// Create the new confirmation token history and store it
		ConfirmationTokenHistory confirmationTokenHistory = new ConfirmationTokenHistory(singleUser, confirmationToken);
		confirmationTokenHistoryRepository.save(confirmationTokenHistory);

		singleUser.addConfirmationTokenHistory(confirmationTokenHistory);

		// Update the user
		userRepository.save(singleUser);

		// Return the created confirmation token
		return new ConfirmationTokenDTO(confirmationTokenHistory);
	}

	/**
	 * Update the confirmation token
	 * @param confirmationTokenDTO the confirmation token where the "status" should be
	 * toggled
	 * @return the updated Data Transfer Object of the confirmation token
	 */
	public ConfirmationTokenDTO toggleConfirmationToken(ConfirmationTokenDTO confirmationTokenDTO) {

		// Trying to fetch the confirmation token history with the id which was given
		// through the Confirmation Token DTO
		Optional<ConfirmationTokenHistory> confirmationTokenHistory = confirmationTokenHistoryRepository
				.findById(confirmationTokenDTO.getId());

		if (confirmationTokenHistory.isEmpty()) {
			throw new RuntimeException(
					"Confirmation Token History not present with given id " + confirmationTokenDTO.getId());
		}

		ConfirmationTokenHistory singleConfirmationTokenHistory = confirmationTokenHistory.get();

		Timestamp now = new Timestamp(System.currentTimeMillis());
		ConfirmationTokenHistory resultConfirmationTokenHistory;

		// Check if the getValid is not set (so is enabled)
		// If this is the case, set the value so that the confirmation token is disabled
		if (singleConfirmationTokenHistory.getValidTo() == null) {
			singleConfirmationTokenHistory.setValidTo(now);
			confirmationTokenHistoryRepository.save(singleConfirmationTokenHistory);

			resultConfirmationTokenHistory = singleConfirmationTokenHistory;
		}
		// If the getValid is set (so is disabled currently)
		// create a new Confirmation Token and add it to the user
		// so it is no real "toggle" - but creating a new one with the same string
		else {
			ConfirmationToken newConfirmationToken = new ConfirmationToken(
					singleConfirmationTokenHistory.getConfirmationToken().getConfirmationToken());
			confirmationTokenRepository.save(newConfirmationToken);

			ConfirmationTokenHistory newConfirmationTokenHistory = new ConfirmationTokenHistory(
					singleConfirmationTokenHistory.getUser(), newConfirmationToken);
			confirmationTokenHistoryRepository.save(newConfirmationTokenHistory);
			resultConfirmationTokenHistory = newConfirmationTokenHistory;
		}

		return new ConfirmationTokenDTO(resultConfirmationTokenHistory);
	}

}
