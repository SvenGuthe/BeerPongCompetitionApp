package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionPlayerStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionPlayerStatusController {

	private CompetitionPlayerStatusRepository competitionPlayerStatusRepository;

	@Autowired
	public CompetitionPlayerStatusController(CompetitionPlayerStatusRepository competitionPlayerStatusRepository) {
		this.competitionPlayerStatusRepository = competitionPlayerStatusRepository;
	}

	// I know that the plural of status = status but I need a unique function name
	@GetMapping("/competitionplayerstatus")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionPlayerStatus> getCompetitionPlayerStati() {
		return competitionPlayerStatusRepository.findAll();
	}

	@GetMapping("/competitionplayerstatus/{competitionPlayerStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerStatus getCompetitionPlayerStatus(@PathVariable Long competitionPlayerStatusId) {
		return competitionPlayerStatusRepository.findById(competitionPlayerStatusId).orElseThrow();
	}

	@PostMapping("/competitionplayerstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerStatus addCompetitionPlayerStatus(
			@RequestBody CompetitionPlayerStatus competitionPlayerStatus) {
		return competitionPlayerStatusRepository.save(competitionPlayerStatus);
	}

	@PutMapping("/competitionplayerstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerStatus updateCompetitionPlayerStatus(
			@RequestBody CompetitionPlayerStatus competitionPlayerStatus) {
		return competitionPlayerStatusRepository.save(competitionPlayerStatus);
	}

	@DeleteMapping("/competitionplayerstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteCompetitionPlayerStatus(@RequestBody CompetitionPlayerStatus competitionPlayerStatus) {
		competitionPlayerStatusRepository.delete(competitionPlayerStatus);
	}

}
