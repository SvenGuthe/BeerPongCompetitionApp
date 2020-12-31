package de.guthe.sven.beerpong.tournamentplaner.repository.login;

import de.guthe.sven.beerpong.tournamentplaner.model.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
