package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authentication")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
    public List<User> getUsers() { return userRepository.findAll(); }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
    public User getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).get();
    }

    @PutMapping("/user")
    @PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
