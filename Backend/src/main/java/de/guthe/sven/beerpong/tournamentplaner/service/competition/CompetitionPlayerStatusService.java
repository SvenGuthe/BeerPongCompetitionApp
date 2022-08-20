package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer.CompetitionPlayerStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionPlayerStatusService {

	CompetitionPlayerStatusRepository competitionPlayerStatusRepository;

	@Autowired
	public CompetitionPlayerStatusService(CompetitionPlayerStatusRepository competitionPlayerStatusRepository) {
		this.competitionPlayerStatusRepository = competitionPlayerStatusRepository;
	}

	public CompetitionPlayerStatus getOrCreateCompetitionPlayerStatus(
			@NotNull CompetitionPlayerStatusType competitionPlayerStatusType) {
		Optional<CompetitionPlayerStatus> competitionPlayerStatus = competitionPlayerStatusRepository
				.findByStatus(competitionPlayerStatusType.name());
		return competitionPlayerStatus.orElseGet(() -> new CompetitionPlayerStatus(competitionPlayerStatusType));
	}

}
