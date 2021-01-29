package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ConfirmationToken;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.ConfirmationTokenRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class RegisterController {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private ConfirmationTokenRepository confirmationTokenRepository;

	private EmailSenderService emailSenderService;

	@Autowired
	public RegisterController(UserRepository userRepository, RoleRepository roleRepository,
			ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.emailSenderService = emailSenderService;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null) {
			return checkUser;
		}
		else {
			Role playerRole = roleRepository.findByName(SecurityRole.ROLE_PLAYER.toString());
			user.addRole(playerRole);
			userRepository.save(user);

			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(user.getEmail());
			simpleMailMessage.setSubject("Complete Registration!");
			simpleMailMessage.setFrom("svenguthe@gmail.com");
			simpleMailMessage.setText("Click here: http://localhost:9999/confirm-account?token="
					+ confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(simpleMailMessage);

			return user;
		}
	}

	@GetMapping("/confirm-account")
	public User confirmUser(@RequestParam String token) {
		ConfirmationToken confirmationTokenDataBase = confirmationTokenRepository.findByConfirmationToken(token);

		if (token != null) {
			User user = userRepository.findByEmail(confirmationTokenDataBase.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			return user;
		}
		else {
			return null;
		}
	}

}
