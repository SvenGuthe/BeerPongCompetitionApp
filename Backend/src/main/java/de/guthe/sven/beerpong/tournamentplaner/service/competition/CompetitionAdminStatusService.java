package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin.CompetitionAdminStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionAdminStatusService {

	CompetitionAdminStatusRepository competitionAdminStatusRepository;

	@Autowired
	public CompetitionAdminStatusService(CompetitionAdminStatusRepository competitionAdminStatusRepository) {
		this.competitionAdminStatusRepository = competitionAdminStatusRepository;
	}

	public CompetitionAdminStatus getOrCreateCompetitionAdminStatus(
			@NotNull CompetitionAdminStatusType competitionAdminStatusType) {
		Optional<CompetitionAdminStatus> competitionAdminStatus = competitionAdminStatusRepository
				.findByStatus(competitionAdminStatusType.name());
		CompetitionAdminStatus finalCompetitionAdminStatus;
		if (competitionAdminStatus.isEmpty()) {
			finalCompetitionAdminStatus = new CompetitionAdminStatus(competitionAdminStatusType);
			competitionAdminStatusRepository.save(finalCompetitionAdminStatus);
		}
		else {
			finalCompetitionAdminStatus = competitionAdminStatus.get();
		}
		return finalCompetitionAdminStatus;
	}

}
