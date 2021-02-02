package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionAdminStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionAdminStatusController {

	private CompetitionAdminStatusRepository competitionAdminStatusRepository;

	@Autowired
	public CompetitionAdminStatusController(CompetitionAdminStatusRepository competitionAdminStatusRepository) {
		this.competitionAdminStatusRepository = competitionAdminStatusRepository;
	}

	@GetMapping("/competitionadminstatus")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionAdminStatus> getCompetitionAdminStati() {
		return competitionAdminStatusRepository.findAll();
	}

	@GetMapping("/competitionadminstatus/{competitionAdminStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatus getCompetitionAdminStatus(@PathVariable Long competitionAdminStatusId) {
		return competitionAdminStatusRepository.findById(competitionAdminStatusId).orElseThrow();
	}

	@PostMapping("/competitionadminstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatus addCompetitionAdminStatus(
			@RequestBody CompetitionAdminStatus competitionAdminStatus) {
		return competitionAdminStatusRepository.save(competitionAdminStatus);
	}

	@PutMapping("/competitionadminstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatus updateCompetitionAdminStatus(
			@RequestBody CompetitionAdminStatus competitionAdminStatus) {
		return competitionAdminStatusRepository.save(competitionAdminStatus);
	}

	@DeleteMapping("/competitionadminstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteCompetitionAdminStatus(@RequestBody CompetitionAdminStatus competitionAdminStatus) {
		competitionAdminStatusRepository.delete(competitionAdminStatus);
	}

}
