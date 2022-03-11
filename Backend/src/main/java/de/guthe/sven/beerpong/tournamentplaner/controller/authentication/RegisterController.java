package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserRegistrationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ConfirmationToken;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.ConfirmationTokenRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.EmailSenderService;
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

	private PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private TeamStatusRepository teamStatusRepository;

	private ConfirmationTokenRepository confirmationTokenRepository;

	private EmailSenderService emailSenderService;

	@Autowired
	public RegisterController(UserRepository userRepository,
			RoleRepository roleRepository, ConfirmationTokenRepository confirmationTokenRepository,
			EmailSenderService emailSenderService, PasswordEncoder passwordEncoder, TeamStatusRepository teamStatusRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.teamStatusRepository = teamStatusRepository;
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
		user.setUserStatus(userStatus);

		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null) {
			return new UserDTO(checkUser);
		}
		else {
			Role playerRole = roleRepository.findByName(SecurityRole.ROLE_PLAYER.toString());
			user.addRole(playerRole);

			TeamStatus teamStatus = new TeamStatus();
			teamStatus.setTeamStatusDescription(TeamStatusType.INACTIVE);

			Team team = new Team();
			team.setTeamName(user.getGamerTag());
			team.setPlayerTeam(true);
			team.setPassword("");
			team.addTeamStatus(teamStatus);

			user.addTeam(team);

			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			user.addConfirmationToken(confirmationToken);

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
		ConfirmationToken confirmationTokenDataBase = confirmationTokenRepository.findByConfirmationToken(token);

		if (token != null) {
			User user = userRepository.findByEmail(confirmationTokenDataBase.getUser().getEmail());
			user.setEnabled(true);

			TeamStatus teamStatusActive;
			List<TeamStatus> teamStatusActiveDatabase = teamStatusRepository.findByDescription(TeamStatusType.ACTIVE);

			if (teamStatusActiveDatabase.size() == 0) {
				TeamStatus teamStatus = new TeamStatus();
				teamStatus.setTeamStatusDescription(TeamStatusType.ACTIVE);
				teamStatusActive = teamStatus;
			} else {
				teamStatusActive = teamStatusActiveDatabase.get(0);
			}

			List<TeamComposition> teamCompositions = user.getTeamCompositions().stream().peek(teamComposition -> {
				if (teamComposition.getTeam().isPlayerTeam()) {
					Team team = teamComposition.getTeam();
					List <TeamStatusHistory> teamStatusHistories = team.getTeamStatusHistories().stream().peek(teamStatusHistory -> {
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
		} else {
			return null;
		}
	}

}
