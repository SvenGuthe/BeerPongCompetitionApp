package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionStatusHistoryController {

	private CompetitionStatusHistoryRepository competitionStatusHistoryRepository;

	@Autowired
	public CompetitionStatusHistoryController(CompetitionStatusHistoryRepository competitionStatusHistoryRepository) {
		this.competitionStatusHistoryRepository = competitionStatusHistoryRepository;
	}

	@GetMapping("/competitionstatushistory")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionStatusHistory> getCompetitionStatusHistories() {
		return competitionStatusHistoryRepository.findAll();
	}

	@GetMapping("/competitionstatushistory/{competitionStatusHistoryId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatusHistory getCompetitionStatusHistory(@PathVariable Long competitionStatusHistoryId) {
		return competitionStatusHistoryRepository.findById(competitionStatusHistoryId).orElseThrow();
	}

	@PostMapping("/competitionstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatusHistory addCompetitionStatusHistory(
			@RequestBody CompetitionStatusHistory competitionStatusHistory) {
		return competitionStatusHistoryRepository.save(competitionStatusHistory);
	}

	@PutMapping("/competitionstatushistory")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatusHistory updateCompetitionStatusHistory(
			@RequestBody CompetitionStatusHistory competitionStatusHistory) {
		return competitionStatusHistoryRepository.save(competitionStatusHistory);
	}

	@DeleteMapping("/competitionstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteCompetitionStatusHistory(@RequestBody CompetitionStatusHistory competitionStatusHistory) {
		competitionStatusHistoryRepository.delete(competitionStatusHistory);
	}

}
