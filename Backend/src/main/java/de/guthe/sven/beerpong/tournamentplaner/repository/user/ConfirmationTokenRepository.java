package de.guthe.sven.beerpong.tournamentplaner.repository.user;

import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

	ConfirmationToken findByConfirmationToken(String confirmationToken);

}
