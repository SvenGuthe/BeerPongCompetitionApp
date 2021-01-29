package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
