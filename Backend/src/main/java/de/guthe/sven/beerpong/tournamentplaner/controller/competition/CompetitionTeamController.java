package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionTeamAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionTeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionTeamController {

	private ACLService aclService;

	private CompetitionTeamRepository competitionTeamRepository;

	private CompetitionService competitionService;

	@Autowired
	public CompetitionTeamController(ACLService aclService,
									 CompetitionTeamRepository competitionTeamRepository,
									 CompetitionService competitionService) {
		this.aclService = aclService;
		this.competitionTeamRepository = competitionTeamRepository;
		this.competitionService = competitionService;
	}

	@GetMapping("/competitionteam")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionTeamDTO> getCompetitionTeams() {
		return competitionTeamRepository.findAll().stream().map(CompetitionTeamDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/competitionteam/{competitionTeamId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionTeamDTO getCompetitionTeam(@PathVariable Long competitionTeamId) {
		return new CompetitionTeamDTO(competitionTeamRepository.findById(competitionTeamId).orElseThrow());
	}

	@PostMapping("/competitionteam")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionTeamDTO addCompetitionTeam(@RequestBody CompetitionTeamAddDTO competitionTeamAddDTO) {
		return competitionService.addCompetitionTeam(competitionTeamAddDTO);
	}

	/*
	@PostMapping("/competitionteam")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionTeam addCompetitionTeam(@RequestBody CompetitionTeam competitionTeam) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		Map<Sid, List<Permission>> initialCompetitionTeamPermissions = CompetitionTeamPermission.initialCompetitionTeamPermissions;
		initialCompetitionTeamPermissions.put(sidCreator, CompetitionTeamPermission.ownerPermissions);

		competitionTeamRepository.save(competitionTeam);
		aclService.setPrivileges(competitionTeam, initialCompetitionTeamPermissions);
		return competitionTeam;
	}
	 */

}
