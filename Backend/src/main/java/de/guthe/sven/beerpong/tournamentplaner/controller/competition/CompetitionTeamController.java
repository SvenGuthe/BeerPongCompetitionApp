package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.competition.CompetitionTeamPermission;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionTeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionTeamController {

	private ACLService aclService;

	private CompetitionTeamRepository competitionTeamRepository;

	@Autowired
	public CompetitionTeamController(ACLService aclService, CompetitionTeamRepository competitionTeamRepository) {
		this.aclService = aclService;
		this.competitionTeamRepository = competitionTeamRepository;
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

}
