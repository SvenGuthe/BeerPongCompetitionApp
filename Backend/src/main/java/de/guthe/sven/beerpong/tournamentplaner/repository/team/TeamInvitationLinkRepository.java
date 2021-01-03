package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamInvitationLinkRepository extends JpaRepository<TeamInvitationLink, Long> {
}
