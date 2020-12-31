package de.guthe.sven.beerpong.tournamentplaner.repository.login;

import de.guthe.sven.beerpong.tournamentplaner.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
