package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.competition.CompetitionPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
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
public class CompetitionController {

	private ACLService aclService;

	private CompetitionRepository competitionRepository;

	@Autowired
	public CompetitionController(ACLService aclService, CompetitionRepository competitionRepository) {
		this.aclService = aclService;
		this.competitionRepository = competitionRepository;
	}

	@GetMapping("/competition")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionDTO> getCompetitions() {
		return competitionRepository.findAll().stream().map(CompetitionDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/competition/{competitionId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionDTO getCompetition(@PathVariable Long competitionId) {
		return new CompetitionDTO(competitionRepository.findById(competitionId).orElseThrow());
	}

	@PostMapping("/competition")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Competition addCompetition(@RequestBody Competition competition) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		Map<Sid, List<Permission>> initialCompetitionPermissions = CompetitionPermissions.initialCompetitionPermissions;
		initialCompetitionPermissions.put(sidCreator, CompetitionPermissions.ownerPermissions);

		competitionRepository.save(competition);
		aclService.setPrivileges(competition, initialCompetitionPermissions);
		return competition;
	}

}
