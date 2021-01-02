package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ConfirmationToken;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.ConfirmationTokenRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        User checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser != null) {
            return checkUser;
        } else {
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("Complete Registration!");
            simpleMailMessage.setFrom("svenguthe@gmail.com");
            simpleMailMessage.setText("Click here: http://localhost:9999/confirm-account?token=" + confirmationToken.getConfirmationToken());

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
        } else {
            return null;
        }
    }

}
