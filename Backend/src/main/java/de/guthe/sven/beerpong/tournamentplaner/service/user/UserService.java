package de.guthe.sven.beerpong.tournamentplaner.service.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.ConfirmationTokenAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.UserTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.ConfirmationTokenDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.*;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationToken;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationTokenHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

	UserRepository userRepository;

	RoleRepository roleRepository;

	ConfirmationTokenRepository confirmationTokenRepository;

	ConfirmationTokenHistoryRepository confirmationTokenHistoryRepository;

	UserStatusRepository userStatusRepository;

	UserStatusService userStatusService;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			UserStatusRepository userStatusRepository, ConfirmationTokenRepository confirmationTokenRepository,
			UserStatusService userStatusService,
			ConfirmationTokenHistoryRepository confirmationTokenHistoryRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userStatusRepository = userStatusRepository;
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.userStatusService = userStatusService;
		this.confirmationTokenHistoryRepository = confirmationTokenHistoryRepository;
	}

	public UserDetailDTO transformUserToUserDetailDTO(Long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return transformUserToUserDetailDTO(user.get());
		}
		else {
			throw new Exception("User not found with id " + id);
		}
	}

	public UserDetailDTO transformUserToUserDetailDTO(User user) {

		UserDTO userDTO = new UserDTO(user);

		Collection<UserTeamDTO> userTeams = user.getTeamCompositions().stream().map(UserTeamDTO::new)
				.collect(Collectors.toList());

		Collection<CompetitionDTO> competitionsWhereAdmin = user.getCompetitionAdmins().stream()
				.map(CompetitionAdmin::getCompetition).map(CompetitionDTO::new).collect(Collectors.toList());
		Collection<CompetitionDTO> competitionsWherePlayer = user.getCompetitionPlayers().stream()
				.map(competitionPlayer -> new CompetitionDTO(competitionPlayer.getCompetitionTeam().getCompetition()))
				.collect(Collectors.toList());

		return new UserDetailDTO(userDTO, userTeams, competitionsWhereAdmin, competitionsWherePlayer);
	}

	public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {

		User user = userRepository.findById(userUpdateDTO.getId()).get();
		user.setFirstName(userUpdateDTO.getFirstName());
		user.setLastName(userUpdateDTO.getLastName());
		user.setGamerTag(userUpdateDTO.getGamerTag());
		user.setEmail(userUpdateDTO.getEmail());
		user.setEnabled(userUpdateDTO.isEnabled());

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

		userRepository.save(user);

		return new UserDTO(user);

	}

	public ConfirmationTokenDTO addConfirmationToken(ConfirmationTokenAddDTO confirmationTokenAddDTO) {
		User user = userRepository.findById(confirmationTokenAddDTO.getId()).get();

		ConfirmationToken confirmationToken = new ConfirmationToken(confirmationTokenAddDTO.getConfirmationToken());
		confirmationTokenRepository.save(confirmationToken);

		ConfirmationTokenHistory confirmationTokenHistory = new ConfirmationTokenHistory(user, confirmationToken);
		confirmationTokenHistoryRepository.save(confirmationTokenHistory);

		user.addConfirmationTokenHistory(confirmationTokenHistory);
		userRepository.save(user);

		return new ConfirmationTokenDTO(confirmationTokenHistory);
	}

	public ConfirmationTokenDTO toggleConfirmationToken(ConfirmationTokenDTO confirmationTokenDTO) {
		ConfirmationTokenHistory confirmationTokenHistory = confirmationTokenHistoryRepository
				.findById(confirmationTokenDTO.getId()).get();

		Timestamp now = new Timestamp(System.currentTimeMillis());
		ConfirmationTokenHistory resultConfirmationTokenHistory;

		if (confirmationTokenHistory.getValidTo() == null) {
			confirmationTokenHistory.setValidTo(now);
			confirmationTokenHistoryRepository.save(confirmationTokenHistory);

			resultConfirmationTokenHistory = confirmationTokenHistory;
		}
		else {
			ConfirmationToken newConfirmationToken = new ConfirmationToken(
					confirmationTokenHistory.getConfirmationToken().getConfirmationToken());
			confirmationTokenRepository.save(newConfirmationToken);

			ConfirmationTokenHistory newConfirmationTokenHistory = new ConfirmationTokenHistory(
					confirmationTokenHistory.getUser(), newConfirmationToken);
			confirmationTokenHistoryRepository.save(newConfirmationTokenHistory);
			resultConfirmationTokenHistory = newConfirmationTokenHistory;
		}

		return new ConfirmationTokenDTO(resultConfirmationTokenHistory);
	}

}
