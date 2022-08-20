package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionStatusService {

	CompetitionStatusRepository competitionStatusRepository;

	@Autowired
	public CompetitionStatusService(CompetitionStatusRepository competitionStatusRepository) {
		this.competitionStatusRepository = competitionStatusRepository;
	}

	public CompetitionStatus getOrCreateCompetitionStatus(@NotNull CompetitionStatusType competitionStatusType) {
		Optional<CompetitionStatus> competitionStatus = competitionStatusRepository
				.findByStatus(competitionStatusType.name());
		return competitionStatus.orElseGet(() -> new CompetitionStatus(competitionStatusType));
	}

}
