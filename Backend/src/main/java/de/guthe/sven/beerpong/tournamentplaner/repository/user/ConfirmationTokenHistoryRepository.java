package de.guthe.sven.beerpong.tournamentplaner.repository.user;

import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationTokenHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenHistoryRepository extends JpaRepository<ConfirmationTokenHistory, Long> {

}
