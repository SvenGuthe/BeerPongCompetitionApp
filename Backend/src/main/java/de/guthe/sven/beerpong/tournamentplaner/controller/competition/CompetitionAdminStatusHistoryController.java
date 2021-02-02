package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionAdminStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionAdminStatusHistoryController {

	private CompetitionAdminStatusHistoryRepository competitionAdminStatusHistoryRepository;

	@Autowired
	public CompetitionAdminStatusHistoryController(
			CompetitionAdminStatusHistoryRepository competitionAdminStatusHistoryRepository) {
		this.competitionAdminStatusHistoryRepository = competitionAdminStatusHistoryRepository;
	}

	@GetMapping("/competitionadminstatushistory")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionAdminStatusHistory> getCompetitionAdminStatusHistories() {
		return competitionAdminStatusHistoryRepository.findAll();
	}

	@GetMapping("/competitionadminstatushistory/{competitionAdminStatusHistoryId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatusHistory getCompetitionAdminStatusHistory(
			@PathVariable Long competitionAdminStatusHistoryId) {
		return competitionAdminStatusHistoryRepository.findById(competitionAdminStatusHistoryId).orElseThrow();
	}

	@PostMapping("/competitionadminstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatusHistory addCompetitionAdminStatusHistory(
			@RequestBody CompetitionAdminStatusHistory competitionAdminStatusHistory) {
		return competitionAdminStatusHistoryRepository.save(competitionAdminStatusHistory);
	}

	@PutMapping("/competitionadminstatushistory")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatusHistory updateCompetitionAdminStatusHistory(
			@RequestBody CompetitionAdminStatusHistory competitionAdminStatusHistory) {
		return competitionAdminStatusHistoryRepository.save(competitionAdminStatusHistory);
	}

	@DeleteMapping("/competitionadminstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteCompetitionAdminStatusHistory(
			@RequestBody CompetitionAdminStatusHistory competitionAdminStatusHistory) {
		competitionAdminStatusHistoryRepository.delete(competitionAdminStatusHistory);
	}

}
