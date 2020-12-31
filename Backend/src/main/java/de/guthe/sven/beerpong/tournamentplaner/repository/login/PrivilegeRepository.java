package de.guthe.sven.beerpong.tournamentplaner.repository.login;

import de.guthe.sven.beerpong.tournamentplaner.model.login.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

}
