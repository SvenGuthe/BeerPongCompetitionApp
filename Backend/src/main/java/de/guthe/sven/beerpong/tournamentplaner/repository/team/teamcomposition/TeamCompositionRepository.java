package de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition;

import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamComposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCompositionRepository extends JpaRepository<TeamComposition, Long> {

	TeamComposition findByTeamIdAndUserId(Long teamId, Long userId);

}
