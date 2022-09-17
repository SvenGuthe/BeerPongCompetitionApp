package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionteam.CompetitionTeamAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionteam.CompetitionTeamUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionteam.CompetitionTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionTeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionTeamController {

	private final ACLService aclService;

	private final CompetitionTeamRepository competitionTeamRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclService service to set privileges for objects
	 * @param competitionTeamRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition teams
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionTeamController(ACLService aclService, CompetitionTeamRepository competitionTeamRepository,
			CompetitionService competitionService) {
		this.aclService = aclService;
		this.competitionTeamRepository = competitionTeamRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionTeamController.class);

	/**
	 * Route to return all Competition Teams TODO: Check if we want to change it to
	 * pagination
	 * @return all Competition Teams
	 */
	@GetMapping("/competitionteam")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionTeamDTO> getCompetitionTeams() {
		logger.info("Fetch all Competition Teams");
		return competitionTeamRepository.findAll().stream().map(CompetitionTeamDTO::new).collect(Collectors.toList());
	}

	/**
	 * Route to get a single Competition Team by the ID
	 * @param competitionTeamId the id of the Competition Team in the database
	 * @return the Competition Team
	 */
	@GetMapping("/competitionteam/{competitionTeamId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionTeamDTO getCompetitionTeam(@PathVariable Long competitionTeamId) {
		logger.info("Trying to find a Competition Team with id: " + competitionTeamId);

		Optional<CompetitionTeam> competitionTeam = competitionTeamRepository.findById(competitionTeamId);

		if (competitionTeam.isEmpty()) {
			throw new RuntimeException("Competition Team not present with given id " + competitionTeamId);
		}
		else {
			return new CompetitionTeamDTO(competitionTeam.get());
		}
	}

	/**
	 * Route to add the Competition Team manually
	 * @param competitionTeamAddDTO Competition Team wrapped in data transfer object
	 * @return the created Competition Team
	 */
	@PostMapping("/competitionteam")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionTeamDTO addCompetitionTeam(@RequestBody CompetitionTeamAddDTO competitionTeamAddDTO) {
		logger.info("Trying to save " + competitionTeamAddDTO);
		return competitionService.addCompetitionTeam(competitionTeamAddDTO);
	}

	/**
	 * Route to update the Competition Team manually
	 * @param competitionTeamUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/competitionteam")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionTeamDTO updateCompetitionTeam(@RequestBody CompetitionTeamUpdateDTO competitionTeamUpdateDTO) {
		logger.info("Trying to update the Competition Team with id = " + competitionTeamUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + competitionTeamUpdateDTO);
		return competitionService.updateCompetitionTeam(competitionTeamUpdateDTO);
	}

	/*
	 * TODO: Check how to activate the privileges for competition teams
	 *
	 * @PostMapping("/competitionteam")
	 *
	 * @Transactional
	 *
	 * @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')") public CompetitionTeam
	 * addCompetitionTeam(@RequestBody CompetitionTeam competitionTeam) { Authentication
	 * authentication = SecurityContextHolder.getContext().getAuthentication(); Sid
	 * sidCreator = new PrincipalSid(authentication);
	 *
	 * Map<Sid, List<Permission>> initialCompetitionTeamPermissions =
	 * CompetitionTeamPermission.initialCompetitionTeamPermissions;
	 * initialCompetitionTeamPermissions.put(sidCreator,
	 * CompetitionTeamPermission.ownerPermissions);
	 *
	 * competitionTeamRepository.save(competitionTeam);
	 * aclService.setPrivileges(competitionTeam, initialCompetitionTeamPermissions);
	 * return competitionTeam; }
	 */

}
