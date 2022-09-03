package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.TeamUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.*;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teaminvitationlink.TeamInvitationLinkDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teaminvitationlink.TeamInvitationLinkHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.model.team.*;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamComposition;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teaminvitationlink.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

	final private TeamRepository teamRepository;

	final private TeamStatusRepository teamStatusRepository;

	final private TeamCompositionRepository teamCompositionRepository;

	final private TeamCompositionStatusRepository teamCompositionStatusRepository;

	final private TeamCompositionStatusService teamCompositionStatusService;

	final private TeamStatusService teamStatusService;

	final private UserRepository userRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param teamRepository jpa repository to handle all database queries directly in
	 * this controller regarding the team
	 * @param teamStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the team status
	 * @param teamCompositionRepository jpa repository to handle all database queries
	 * directly in this controller regarding the team composition
	 * @param userRepository jpa repository to handle all database queries directly in
	 * this controller regarding the user
	 * @param teamCompositionStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the team composition status
	 * @param teamCompositionStatusService service to handle all the transformations /
	 * database queries regarding the team composition status
	 * @param teamStatusService service to handle all the transformations / database
	 * queries regarding the team status
	 */
	@Autowired
	public TeamService(TeamRepository teamRepository, TeamStatusRepository teamStatusRepository,
			TeamCompositionRepository teamCompositionRepository, UserRepository userRepository,
			TeamCompositionStatusRepository teamCompositionStatusRepository,
			TeamCompositionStatusService teamCompositionStatusService, TeamStatusService teamStatusService) {
		this.teamRepository = teamRepository;
		this.teamStatusRepository = teamStatusRepository;
		this.teamCompositionRepository = teamCompositionRepository;
		this.userRepository = userRepository;
		this.teamCompositionStatusRepository = teamCompositionStatusRepository;
		this.teamCompositionStatusService = teamCompositionStatusService;
		this.teamStatusService = teamStatusService;
	}

	/**
	 * Function to transform a Team to the Data Transfer Object of the team details
	 * @param id the Team ID
	 * @return the Data Transfer Object of the Team Details
	 * @throws Exception if there was no team with the id TODO: Check if it is better to
	 * throw an exception or use Optional
	 */
	public TeamDetailDTO transformTeamToTeamDetailDTO(Long id) throws Exception {

		// Trying to fetch the team with the given id
		Optional<Team> team = teamRepository.findById(id);

		// If team was present then create the Data Transfer Object
		if (team.isPresent()) {
			return transformTeamToTeamDetailDTO(team.get());
		}
		// If the team does not exist, throw an error
		else {
			throw new Exception("Team not found with id " + id);
		}
	}

	/**
	 * Function to transform a Team to the Data Transfer Object of the team details
	 * @param team the Team object of the model class
	 * @return the Team Details
	 */
	public TeamDetailDTO transformTeamToTeamDetailDTO(Team team) {

		// Create the Data Transfer Object of the Team
		TeamDTO teamDTO = new TeamDTO(team);

		// Create a list of Team User from the team compositions
		// TODO: do we have to check the status here?
		Collection<TeamUserDTO> teamUsers = team.getTeamCompositions().stream().map(TeamUserDTO::new)
				.collect(Collectors.toList());

		// Create a list of Competitions from the competition teams
		// TODO: do we have to check the status here?
		Collection<CompetitionDTO> competitions = team.getCompetitionTeams().stream()
				.map(competitionTeam -> new CompetitionDTO(competitionTeam.getCompetition()))
				.collect(Collectors.toList());

		// Find possible users for the given team
		// So all players which are not in the team already
		Collection<UserDTO> possibleUsers = userRepository.findAll().stream()
				.filter(user -> !teamUsers.stream().map(teamUserDTO -> teamUserDTO.getUser().getId())
						.collect(Collectors.toList()).contains(user.getId()))
				.map(UserDTO::new).collect(Collectors.toList());

		// Return the team details as a Data Transfer Object
		return new TeamDetailDTO(teamDTO, teamUsers, competitions, possibleUsers);
	}

	/**
	 * Update the team
	 * @param teamUpdateDTO values which should be updated
	 * @return the updated Data Transfer Object of the team
	 */
	public TeamDTO updateTeam(TeamUpdateDTO teamUpdateDTO) {

		// Trying to fetch the team with the id which was given through the Team Update
		// DTO
		// TODO: Change logic to Optional if the id is not present
		Team team = teamRepository.findById(teamUpdateDTO.getId()).get();

		// Set the new values
		team.setTeamName(teamUpdateDTO.getTeamName());

		// Store the new team
		teamRepository.save(team);

		// Return the stored team
		return new TeamDTO(team);
	}

	/**
	 * Update the registration status of a given team id (id = id of the team status, team
	 * status = new status which should be set)
	 * @param teamStatusUpdateDTO the Data Transfer Object with the id of the team and the
	 * new team status
	 * @return a list of Team Status Data Transfer Object (2 entries -> the last one which
	 * is now not valid anymore and the new one)
	 */
	public List<TeamStatusDTO> updateTeamStatus(TeamStatusUpdateDTO teamStatusUpdateDTO) {

		// Trying to fetch the team with the id which was given through the Team Status
		// Update DTO
		// TODO: Change logic to Optional if the id is not present
		Team team = teamRepository.findById(teamStatusUpdateDTO.getId()).get();

		// Fetch the team status which should be set from the database or create this one
		// new
		TeamStatus teamStatus = teamStatusService.getOrCreateTeamStatus(teamStatusUpdateDTO.getTeamStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<TeamStatusHistory> currentTeamStatusHistory = team.getTeamStatusHistories().stream()
				.peek(teamStatusHistory -> {
					if (teamStatusHistory.getValidTo() == null) {
						teamStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentTeamStatusHistory.add(new TeamStatusHistory(now, team, teamStatus));

		// Set the new Team Status History and save it to the database
		teamStatus.setTeamStatusHistories(currentTeamStatusHistory);
		teamStatusRepository.save(teamStatus);

		// Sort the history via the validFrom value
		currentTeamStatusHistory.sort(Comparator.comparing(TeamStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentTeamStatusHistory.subList(currentTeamStatusHistory.size() - 2, currentTeamStatusHistory.size())
				.stream().map(TeamStatusDTO::new).collect(Collectors.toList());

	}

	/**
	 * Add a new team invitation link to the current team (id = id of the team)
	 * @param teamInvitationLinkAddDTO the Data Transfer Object with the id of the team
	 * and the new team invitation link
	 * @return the created team invitation link
	 */
	public TeamInvitationLinkDTO addTeamInvitationLink(TeamInvitationLinkAddDTO teamInvitationLinkAddDTO) {

		// Trying to fetch the team with the team-id which was given through the Team
		// Invitation Link Add DTO
		// TODO: Change logic to Optional if the id is not present
		Team team = teamRepository.findById(teamInvitationLinkAddDTO.getId()).get();

		// Add the team invitation link to the invitation links of the user
		TeamInvitationLink teamInvitationLink = new TeamInvitationLink(
				teamInvitationLinkAddDTO.getTeamInvitationLink());
		team.addTeamInvitationLink(teamInvitationLink);

		// Store the new team with the added team invitation link
		teamRepository.save(team);

		// TODO: Check if we can just use the teamInvitationLink above
		List<TeamInvitationLinkHistory> teamInvitationLinkHistory = team.getTeamInvitationLinkHistories();
		teamInvitationLinkHistory.sort(Comparator.comparing(TeamInvitationLinkHistory::getValidFrom));

		return new TeamInvitationLinkDTO(teamInvitationLinkHistory.get(teamInvitationLinkHistory.size() - 1));
	}

	/**
	 * Update the team composition
	 * @param teamCompositionUpdateDTO values which should be updated
	 * @return the updated Data Transfer Object of the team composition
	 */
	public TeamCompositionDTO updateTeamComposition(TeamCompositionUpdateDTO teamCompositionUpdateDTO) {

		// Trying to fetch the team composition with the id which was given through the
		// Team Competition Update DTO
		// TODO: Change logic to Optional if the id is not present
		TeamComposition teamComposition = teamCompositionRepository.findById(teamCompositionUpdateDTO.getId()).get();

		// Set the new values
		teamComposition.setAdmin(teamCompositionUpdateDTO.getAdmin());

		// Store the new team composition
		teamCompositionRepository.save(teamComposition);

		// Return the stored team composition
		return new TeamCompositionDTO(teamComposition);
	}

	/**
	 * Add a new team composition
	 * @param teamCompositionAddDTO
	 * @return the Data Transfer Object with the id of the team and the id of the user
	 * which should be added
	 */
	public TeamCompositionDTO addTeamComposition(TeamCompositionAddDTO teamCompositionAddDTO) {

		// Trying to fetch the team with the team-id which was given through the Team
		// Composition Add DTO
		// TODO: Change logic to Optional if the id is not present
		Team team = teamRepository.findById(teamCompositionAddDTO.getId()).get();

		// Trying to fetch the user with the user-id which was given through the Team
		// Composition Add DTO
		// TODO: Change logic to Optional if the id is not present
		User user = userRepository.findById(teamCompositionAddDTO.getUserId()).get();
		Boolean isAdmin = teamCompositionAddDTO.getAdmin();

		// Create the new Team Composition Object
		TeamComposition teamComposition = new TeamComposition(team, user, isAdmin);

		// Fetch the team composition status which should be set from the database or
		// create this one new
		TeamCompositionStatus teamCompositionStatus = teamCompositionStatusService
				.getOrCreateTeamCompositionStatus(TeamCompositionStatusType.PROMISED);

		teamComposition.addTeamCompositionStatus(teamCompositionStatus);

		// Store the new team composition
		teamCompositionRepository.save(teamComposition);

		// Return the stored team composition
		return new TeamCompositionDTO(teamComposition);
	}

	/**
	 * Update the team composition status of a given team composition team id (id = id of
	 * the composition status, team composition status = new status which should be set)
	 * @param teamCompositionStatusUpdateDTO the Data Transfer Object with the id of the
	 * team composition and the new team composition status
	 * @return a list of Team Composition Status Data Transfer Object (2 entries -> the
	 * last one which is now not valid anymore and the new one)
	 */
	public List<TeamCompositionStatusDTO> updateTeamCompositionStatus(
			TeamCompositionStatusUpdateDTO teamCompositionStatusUpdateDTO) {

		// Trying to fetch the team composition with the id which was given through the
		// Team Composition Status Update DTO
		// TODO: Change logic to Optional if the id is not present
		TeamComposition teamComposition = teamCompositionRepository.findById(teamCompositionStatusUpdateDTO.getId())
				.get();

		// Fetch the team composition status which should be set from the database or
		// create this one new
		TeamCompositionStatus teamCompositionStatus = teamCompositionStatusService
				.getOrCreateTeamCompositionStatus(teamCompositionStatusUpdateDTO.getTeamCompositionStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<TeamCompositionStatusHistory> currentTeamCompositionStatusHistory = teamComposition
				.getTeamCompositionStatusHistories().stream().peek(teamCompositionStatusHistory -> {
					if (teamCompositionStatusHistory.getValidTo() == null) {
						teamCompositionStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentTeamCompositionStatusHistory
				.add(new TeamCompositionStatusHistory(now, teamComposition, teamCompositionStatus));

		// Set the new Team Composition Status History and save it to the database
		teamCompositionStatus.setTeamCompositionStatusHistories(currentTeamCompositionStatusHistory);
		teamCompositionStatusRepository.save(teamCompositionStatus);

		// Sort the history via the validFrom value
		currentTeamCompositionStatusHistory.sort(Comparator.comparing(TeamCompositionStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentTeamCompositionStatusHistory
				.subList(currentTeamCompositionStatusHistory.size() - 2, currentTeamCompositionStatusHistory.size())
				.stream().map(TeamCompositionStatusDTO::new).collect(Collectors.toList());
	}

}
