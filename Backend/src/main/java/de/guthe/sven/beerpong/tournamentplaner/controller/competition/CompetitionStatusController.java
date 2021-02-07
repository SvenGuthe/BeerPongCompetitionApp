package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionStatusController {

	private CompetitionStatusRepository competitionStatusRepository;

	@Autowired
	public CompetitionStatusController(CompetitionStatusRepository competitionStatusRepository) {
		this.competitionStatusRepository = competitionStatusRepository;
	}

	@GetMapping("/competitionstatus")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionStatus> getCompetitionStati() {
		return competitionStatusRepository.findAll();
	}

	@GetMapping("/competitionstatus/{competitionStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatus getCompetitionStatus(@PathVariable Long competitionStatusId) {
		return competitionStatusRepository.findById(competitionStatusId).orElseThrow();
	}

	@PostMapping("/competitionstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatus addCompetitionStatus(@RequestBody CompetitionStatus competitionStatus) {
		return competitionStatusRepository.save(competitionStatus);
	}

	@PutMapping("/competitionstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatus updateCompetitionStatus(@RequestBody CompetitionStatus competitionStatus) {
		return competitionStatusRepository.save(competitionStatus);
	}

	@DeleteMapping("/competitionstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteCompetitionStatus(@RequestBody CompetitionStatus competitionStatus) {
		competitionStatusRepository.delete(competitionStatus);
	}

}
