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
import de.guthe.sven.beerpong.tournamentplaner.service.EmailSenderService;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class RegisterController {

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final TeamStatusRepository teamStatusRepository;

	private final ConfirmationTokenRepository confirmationTokenRepository;

	private final TeamService teamService;

	private final EmailSenderService emailSenderService;

	@Autowired
	public RegisterController(UserRepository userRepository, RoleRepository roleRepository,
			ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService,
			PasswordEncoder passwordEncoder, TeamStatusRepository teamStatusRepository, TeamService teamService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.teamStatusRepository = teamStatusRepository;
		this.teamService = teamService;
	}

	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		UserStatus userStatus = new UserStatus();
		userStatus.setUserStatus(UserStatusType.ACTIVE);

		User user = new User();
		user.setEmail(userRegistrationDTO.getEmail());
		user.setFirstName(userRegistrationDTO.getFirstName());
		user.setLastName(userRegistrationDTO.getLastName());
		user.setGamerTag(userRegistrationDTO.getGamerTag());
		user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

		UserStatusHistory userStatusHistory = new UserStatusHistory();
		userStatusHistory.setUser(user);
		userStatusHistory.setUserStatus(userStatus);

		user.addUserStatusHistory(userStatusHistory);

		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null) {
			return new UserDTO(checkUser);
		}
		else {
			Role playerRole = roleRepository.findByName(SecurityRole.ROLE_PLAYER.toString());

			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(playerRole);

			user.addUserRole(userRole);

			TeamStatus teamStatus = new TeamStatus();
			teamStatus.setTeamStatusDescription(TeamStatusType.INACTIVE);

			Team team = new Team();
			team.setTeamName(user.getGamerTag());
			team.setPlayerTeam(true);
			team.setPassword("");
			team.addTeamStatus(teamStatus);

			TeamCompositionStatus teamCompositionStatus = teamService
					.getOrCreateTeamCompositionStatus(TeamCompositionStatusType.PROMISED);

			user.addTeam(team, true, teamCompositionStatus);

			ConfirmationToken confirmationToken = new ConfirmationToken();
			ConfirmationTokenHistory confirmationTokenHistory = new ConfirmationTokenHistory(user, confirmationToken);

			user.addConfirmationTokenHistory(confirmationTokenHistory);

			userRepository.save(user);

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(user.getEmail());
			simpleMailMessage.setSubject("Complete Registration!");
			simpleMailMessage.setFrom("svenguthe@gmail.com");
			simpleMailMessage.setText("Click here: http://localhost:3000/confirm/result?token="
					+ confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(simpleMailMessage);

			return new UserDTO(user);
		}
	}

	@GetMapping("/confirm-account")
	public UserDTO confirmUser(@RequestParam String token) {
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

		if (token != null) {
			List<ConfirmationTokenHistory> confirmationTokenHistories = confirmationToken.getConfirmationTokenHistory();
			ConfirmationTokenHistory confirmationTokenHistory = confirmationTokenHistories.stream()
					.filter(singleHistory -> singleHistory.getValidTo() == null).collect(Collectors.toList()).get(0);

			User user = userRepository.findByEmail(confirmationTokenHistory.getUser().getEmail());

			List<ConfirmationTokenHistory> newConfirmationTokenHistories = confirmationTokenHistories.stream()
					.peek(singleHistory -> {
						if (singleHistory.getValidTo() == null) {
							singleHistory.setValidTo(new Timestamp(System.currentTimeMillis()));
						}
					}).collect(Collectors.toList());

			user.setEnabled(true);
			user.setConfirmationTokenHistories(newConfirmationTokenHistories);

			TeamStatus teamStatusActive;
			List<TeamStatus> teamStatusActiveDatabase = teamStatusRepository.findByDescription(TeamStatusType.ACTIVE);

			if (teamStatusActiveDatabase.size() == 0) {
				TeamStatus teamStatus = new TeamStatus();
				teamStatus.setTeamStatusDescription(TeamStatusType.ACTIVE);
				teamStatusActive = teamStatus;
			}
			else {
				teamStatusActive = teamStatusActiveDatabase.get(0);
			}

			List<TeamComposition> teamCompositions = user.getTeamCompositions().stream().peek(teamComposition -> {
				if (teamComposition.getTeam().isPlayerTeam()) {
					Team team = teamComposition.getTeam();
					List<TeamStatusHistory> teamStatusHistories = team.getTeamStatusHistories().stream()
							.peek(teamStatusHistory -> {
								if (teamStatusHistory.getValidTo() == null) {
									teamStatusHistory.setValidTo(new Timestamp(System.currentTimeMillis()));
								}
							}).collect(Collectors.toList());

					TeamStatusHistory newTeamStatusHistory = new TeamStatusHistory();
					newTeamStatusHistory.setTeamStatus(teamStatusActive);
					newTeamStatusHistory.setTeam(teamComposition.getTeam());

					teamStatusHistories.add(newTeamStatusHistory);

					team.setTeamStatusHistories(teamStatusHistories);

					teamComposition.setTeam(team);
				}
			}).collect(Collectors.toList());

			user.setTeamCompositions(teamCompositions);

			userRepository.save(user);
			return new UserDTO(user);
		}
		else {
			return null;
		}
	}

}
