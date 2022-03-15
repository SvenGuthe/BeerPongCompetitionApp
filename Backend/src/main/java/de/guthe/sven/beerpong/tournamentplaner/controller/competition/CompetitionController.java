package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.competition.CompetitionPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	private CompetitionService competitionService;

	@Autowired
	public CompetitionController(ACLService aclService, CompetitionRepository competitionRepository, CompetitionService competitionService) {
		this.aclService = aclService;
		this.competitionRepository = competitionRepository;
		this.competitionService = competitionService;
	}

	@GetMapping("/competition")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<CompetitionDTO> getCompetitions(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<Competition> pageRequest;
		if (search.equals("")) {
			pageRequest = competitionRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = competitionRepository.findAll(search, PageRequest.of(page, size));
		}

		List<CompetitionDTO> data = pageRequest.stream().map(CompetitionDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/competition/{competitionId}")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionDetailDTO getCompetition(@PathVariable Long competitionId) {
		return competitionService.getCompetitionDetail(competitionId);
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
