package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionAdminController {

	private CompetitionAdminRepository competitionAdminRepository;

	@Autowired
	public CompetitionAdminController(CompetitionAdminRepository competitionAdminRepository) {
		this.competitionAdminRepository = competitionAdminRepository;
	}

	@GetMapping("/competitionadmin")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionAdmin> getCompetitionAdmins() {
		return competitionAdminRepository.findAll();
	}

	@GetMapping("/competitionadmin/{competitionAdminId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdmin getCompetitionAdmin(@PathVariable Long competitionAdminId) {
		return competitionAdminRepository.findById(competitionAdminId).orElseThrow();
	}

	@PostMapping("/competitionadmin")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdmin addCompetitionAdmin(@RequestBody CompetitionAdmin competitionAdmin) {
		return competitionAdminRepository.save(competitionAdmin);
	}

}
