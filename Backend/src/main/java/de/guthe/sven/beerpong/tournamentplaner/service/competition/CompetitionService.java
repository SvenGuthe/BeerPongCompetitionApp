package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionadmin.CompetitionAdminAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionadmin.CompetitionAdminStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionplayer.CompetitionPlayerAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionplayer.CompetitionPlayerStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionstatus.CompetitionStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionteam.CompetitionTeamAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionteam.CompetitionTeamUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.billing.BillingStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.registration.RegistrationStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.billing.BillingStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionadmin.CompetitionAdminDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionadmin.CompetitionAdminStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer.CompetitionPlayerDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer.CompetitionPlayerStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionstatus.CompetitionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionteam.CompetitionTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.registration.RegistrationStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin.CompetitionAdminRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer.CompetitionPlayerRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

	final private CompetitionRepository competitionRepository;

	final private CompetitionAdminRepository competitionAdminRepository;

	final private CompetitionTeamRepository competitionTeamRepository;

	final private RegistrationStatusRepository registrationStatusRepository;

	final private BillingStatusRepository billingStatusRepository;

	final private CompetitionPlayerRepository competitionPlayerRepository;

	final private UserRepository userRepository;

	final private TeamRepository teamRepository;

	final private CompetitionStatusService competitionStatusService;

	final private CompetitionAdminStatusService competitionAdminStatusService;

	final private RegistrationStatusService registrationStatusService;

	final private BillingStatusService billingStatusService;

	final private CompetitionPlayerStatusService competitionPlayerStatusService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionRepository jpa repository to handle all database queries directly
	 * in this controller regarding the competition
	 * @param userRepository jpa repository to handle all database queries directly in
	 * this controller regarding the user
	 * @param teamRepository jpa repository to handle all database queries directly in
	 * this controller regarding the team
	 * @param competitionAdminRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition admin
	 * @param competitionTeamRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition team
	 * @param registrationStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the registration status
	 * @param billingStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the billing status
	 * @param competitionPlayerRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition player
	 * @param competitionStatusService jpa repository to handle all database queries
	 * directly in this controller regarding the competition status
	 * @param competitionAdminStatusService jpa repository to handle all database queries
	 * directly in this controller regarding the competition admin status
	 * @param registrationStatusService jpa repository to handle all database queries
	 * directly in this controller regarding the registration status
	 * @param billingStatusService jpa repository to handle all database queries directly
	 * in this controller regarding the billing status
	 * @param competitionPlayerStatusService jpa repository to handle all database queries
	 * directly in this controller regarding the competition player status
	 */
	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository, UserRepository userRepository,
			TeamRepository teamRepository, CompetitionAdminRepository competitionAdminRepository,
			CompetitionTeamRepository competitionTeamRepository,
			RegistrationStatusRepository registrationStatusRepository, BillingStatusRepository billingStatusRepository,
			CompetitionPlayerRepository competitionPlayerRepository, CompetitionStatusService competitionStatusService,
			CompetitionAdminStatusService competitionAdminStatusService,
			RegistrationStatusService registrationStatusService, BillingStatusService billingStatusService,
			CompetitionPlayerStatusService competitionPlayerStatusService) {
		this.competitionRepository = competitionRepository;
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
		this.competitionAdminRepository = competitionAdminRepository;
		this.competitionTeamRepository = competitionTeamRepository;
		this.registrationStatusRepository = registrationStatusRepository;
		this.billingStatusRepository = billingStatusRepository;
		this.competitionPlayerRepository = competitionPlayerRepository;
		this.competitionStatusService = competitionStatusService;
		this.competitionAdminStatusService = competitionAdminStatusService;
		this.registrationStatusService = registrationStatusService;
		this.billingStatusService = billingStatusService;
		this.competitionPlayerStatusService = competitionPlayerStatusService;
	}

	/**
	 * Function to fetch the competition details for a given competition id TODO: Change
	 * to Optional<>
	 * @param competitionId the ID of the competition
	 * @return the competition details if the competition was present in the database
	 */
	public CompetitionDetailDTO getCompetitionDetail(Long competitionId) {
		// Trying to create a Competition Data Transfer Object from the database with the
		// given ID
		// TODO: Change logic to Optional if the id is not present
		CompetitionDTO competition = new CompetitionDTO(competitionRepository.findById(competitionId).get());

		// Fetch all possible admins for the current competition
		// A possible admin could be everyone who is not admin already with status Invited
		// or Promised
		// TODO: Check if these status are enough
		Collection<UserIDAndGamerTagDTO> possibleAdminUsers = userRepository.findAllPossibleAdmins(competitionId)
				.stream().map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());

		// Fetch all possible players for the current competition
		// A possible player could be everyone who is not player already with status
		// Invited or Promised
		// TODO: Check if these status are enough
		Collection<UserIDAndGamerTagDTO> possiblePlayers = userRepository.findAllPossiblePlayers(competitionId).stream()
				.map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());

		// Fetch all teams and users
		// TODO: Check why we are needing this here
		Collection<TeamAndUserDTO> teamAndUser = teamRepository.findAll().stream().map(TeamAndUserDTO::new)
				.collect(Collectors.toList());

		return new CompetitionDetailDTO(competition, possibleAdminUsers, possiblePlayers, teamAndUser);
	}

	/**
	 * Update the competition status of a given competition id (id = id of the
	 * competition, competitionStatusType = new status which should be set)
	 * @param competitionStatusUpdateDTO the Data Transfer Object with the id of the
	 * competition and the new status
	 * @return a list of Competition Status Data Transfer Object (2 entries -> the last
	 * one which is now not valid anymore and the new one)
	 */
	public List<CompetitionStatusDTO> updateCompetitionStatus(CompetitionStatusUpdateDTO competitionStatusUpdateDTO) {

		// Trying to fetch the competition with the id which was given through the
		// Competition Status Update DTO
		// TODO: Change logic to Optional if the id is not present
		Competition competition = competitionRepository.findById(competitionStatusUpdateDTO.getId()).get();

		// Fetch the competition status which should be set from the database or create
		// this one new
		CompetitionStatus competitionStatus = competitionStatusService
				.getOrCreateCompetitionStatus(competitionStatusUpdateDTO.getCompetitionStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<CompetitionStatusHistory> currentCompetitionStatusHistory = competition.getCompetitionStatusHistories()
				.stream().peek(competitionStatusHistory -> {
					if (competitionStatusHistory.getValidTo() == null) {
						competitionStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentCompetitionStatusHistory.add(new CompetitionStatusHistory(now, competition, competitionStatus));

		// Set the new Competition Status History and save it to the database
		competition.setCompetitionStatusHistories(currentCompetitionStatusHistory);
		competitionRepository.save(competition);

		// Sort the history via the validFrom value
		currentCompetitionStatusHistory.sort(Comparator.comparing(CompetitionStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentCompetitionStatusHistory
				.subList(currentCompetitionStatusHistory.size() - 2, currentCompetitionStatusHistory.size()).stream()
				.map(CompetitionStatusDTO::new).collect(Collectors.toList());

	}

	/**
	 * Update the competition admin status of a given competition admin id (id = id of the
	 * competition admin, competitionAdminStatusType = new status which should be set)
	 * @param competitionAdminStatusUpdateDTO the Data Transfer Object with the id of the
	 * competition admin and the new status
	 * @return a list of Competition Admin Status Data Transfer Object (2 entries -> the
	 * last one which is now not valid anymore and the new one)
	 */
	public List<CompetitionAdminStatusDTO> updateCompetitionAdminStatus(
			CompetitionAdminStatusUpdateDTO competitionAdminStatusUpdateDTO) {

		// Trying to fetch the competition admin with the id which was given through the
		// Competition Admin Status Update DTO
		// TODO: Change logic to Optional if the id is not present
		CompetitionAdmin competitionAdmin = competitionAdminRepository.findById(competitionAdminStatusUpdateDTO.getId())
				.get();

		// Fetch the competition admin status which should be set from the database or
		// create this one new
		CompetitionAdminStatus competitionAdminStatus = competitionAdminStatusService
				.getOrCreateCompetitionAdminStatus(competitionAdminStatusUpdateDTO.getCompetitionAdminStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<CompetitionAdminStatusHistory> currentCompetitionAdminStatusHistory = competitionAdmin
				.getCompetitionAdminStatusHistories().stream().peek(competitionAdminStatusHistory -> {
					if (competitionAdminStatusHistory.getValidTo() == null) {
						competitionAdminStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentCompetitionAdminStatusHistory
				.add(new CompetitionAdminStatusHistory(now, competitionAdmin, competitionAdminStatus));

		// Set the new Competition Admin Status History and save it to the database
		competitionAdmin.setCompetitionAdminStatusHistories(currentCompetitionAdminStatusHistory);
		competitionAdminRepository.save(competitionAdmin);

		// Sort the history via the validFrom value
		currentCompetitionAdminStatusHistory.sort(Comparator.comparing(CompetitionAdminStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentCompetitionAdminStatusHistory
				.subList(currentCompetitionAdminStatusHistory.size() - 2, currentCompetitionAdminStatusHistory.size())
				.stream().map(CompetitionAdminStatusDTO::new).collect(Collectors.toList());

	}

	/**
	 * Update the registration status of a given competition team id (id = id of the
	 * competition team, registration status = new status which should be set)
	 * @param registrationStatusUpdateDTO the Data Transfer Object with the id of the
	 * competition team and the new registration status
	 * @return a list of Registration Status Data Transfer Object (2 entries -> the last
	 * one which is now not valid anymore and the new one)
	 */
	public List<RegistrationStatusDTO> updateRegistrationStatus(
			RegistrationStatusUpdateDTO registrationStatusUpdateDTO) {

		// Trying to fetch the competition team with the id which was given through the
		// Registration Status Update DTO
		// TODO: Change logic to Optional if the id is not present
		CompetitionTeam competitionTeam = competitionTeamRepository.findById(registrationStatusUpdateDTO.getId()).get();

		// Fetch the registration status which should be set from the database or create
		// this one new
		RegistrationStatus registrationStatus = registrationStatusService
				.getOrCreateRegistrationStatus(registrationStatusUpdateDTO.getRegistrationStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<RegistrationStatusHistory> currentRegistrationStatusHistory = competitionTeam
				.getRegistrationStatusHistories().stream().peek(registrationStatusHistory -> {
					if (registrationStatusHistory.getValidTo() == null) {
						registrationStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentRegistrationStatusHistory.add(new RegistrationStatusHistory(now, competitionTeam, registrationStatus));

		// Set the new Registration Status History and save it to the database
		registrationStatus.setRegistrationStatusHistories(currentRegistrationStatusHistory);
		registrationStatusRepository.save(registrationStatus);

		// Sort the history via the validFrom value
		currentRegistrationStatusHistory.sort(Comparator.comparing(RegistrationStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentRegistrationStatusHistory
				.subList(currentRegistrationStatusHistory.size() - 2, currentRegistrationStatusHistory.size()).stream()
				.map(RegistrationStatusDTO::new).collect(Collectors.toList());

	}

	/**
	 * Update the registration status of a given competition team id (id = id of the
	 * competition team, billing status = new status which should be set)
	 * @param billingStatusUpdateDTO the Data Transfer Object with the id of the
	 * competition team and the new billing status
	 * @return a list of Billing Status Data Transfer Object (2 entries -> the last one
	 * which is now not valid anymore and the new one)
	 */
	public List<BillingStatusDTO> updateBillingStatus(@NotNull BillingStatusUpdateDTO billingStatusUpdateDTO) {

		// Trying to fetch the competition team with the id which was given through the
		// Billing Status Update DTO
		// TODO: Change logic to Optional if the id is not present
		CompetitionTeam competitionTeam = competitionTeamRepository.findById(billingStatusUpdateDTO.getId()).get();

		// Fetch the billing status which should be set from the database or create this
		// one new
		BillingStatus billingStatus = billingStatusService
				.getOrCreateBillingStatus(billingStatusUpdateDTO.getBillingStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<BillingStatusHistory> currentBillingStatusHistory = competitionTeam.getBillingStatusHistories().stream()
				.peek(billingStatusHistory -> {
					if (billingStatusHistory.getValidTo() == null) {
						billingStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentBillingStatusHistory.add(new BillingStatusHistory(now, competitionTeam, billingStatus));

		// Set the new Billing Status History and save it to the database
		billingStatus.setBillingStatusHistories(currentBillingStatusHistory);
		billingStatusRepository.save(billingStatus);

		// Sort the history via the validFrom value
		currentBillingStatusHistory.sort(Comparator.comparing(BillingStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentBillingStatusHistory
				.subList(currentBillingStatusHistory.size() - 2, currentBillingStatusHistory.size()).stream()
				.map(BillingStatusDTO::new).collect(Collectors.toList());
	}

	/**
	 * Add a new competition admin to the current competition (id = id of the competition,
	 * user = the user which should be added as a competition admin)
	 * @param competitionAdminAddDTO the Data Transfer Object with the id of the
	 * competition and the user which should be added as an admin
	 * @return the Data Transfer Object of the new added competition admin
	 */
	public CompetitionAdminDTO addCompetitionAdmin(CompetitionAdminAddDTO competitionAdminAddDTO) {

		// Trying to fetch the competition with the competition-id which was given through
		// the Competition Admin Add DTO
		// TODO: Change logic to Optional if the id is not present
		Competition competition = competitionRepository.findById(competitionAdminAddDTO.id).get();

		// Trying to fetch the user with the user-id which was given through the
		// Competition Admin Add DTO
		// TODO: Change logic to Optional if the id is not present
		User user = userRepository.findById(competitionAdminAddDTO.getUserId()).get();

		// Fetch the competition admin status which should be set from the database or
		// create this one new
		CompetitionAdminStatus competitionAdminStatus = competitionAdminStatusService
				.getOrCreateCompetitionAdminStatus(CompetitionAdminStatusType.PROMISED);

		// Add the user to the competition admins of the competition
		CompetitionAdmin competitionAdmin = new CompetitionAdmin();
		competitionAdmin.setUser(user);
		competitionAdmin.setCompetition(competition);
		competitionAdmin.addCompetitionAdminStatus(competitionAdminStatus);

		// Store the new competition admin
		competitionAdminRepository.save(competitionAdmin);

		// Return the stored competition admin
		return new CompetitionAdminDTO(competitionAdmin);
	}

	/**
	 * Add a new competition player to the current competition (id = id of the competition
	 * team, user = the user which should be added as a competition player)
	 * @param competitionPlayerAddDTO the Data Transfer Object with the id of the
	 * competition team and the user which should be added as a player
	 * @return the Data Transfer Object of the new added competition player
	 */
	public CompetitionPlayerDTO addCompetitionPlayer(CompetitionPlayerAddDTO competitionPlayerAddDTO) {

		// Trying to fetch the competition team with the competition team-id which was
		// given through the Competition Player Add DTO
		// TODO: Change logic to Optional if the id is not present
		CompetitionTeam competitionTeam = competitionTeamRepository.findById(competitionPlayerAddDTO.getId()).get();

		// Trying to fetch the user with the user-id which was given through the
		// Competition Player Add DTO
		// TODO: Change logic to Optional if the id is not present
		User user = userRepository.findById(competitionPlayerAddDTO.getUserId()).get();

		// Fetch the competition player status which should be set from the database or
		// create this one new
		CompetitionPlayerStatus competitionPlayerStatus = competitionPlayerStatusService
				.getOrCreateCompetitionPlayerStatus(CompetitionPlayerStatusType.PROMISED);

		// Add the user to the competition players of the competition
		CompetitionPlayer competitionPlayer = new CompetitionPlayer();
		competitionPlayer.setCompetitionTeam(competitionTeam);
		competitionPlayer.addCompetitionPlayerStatus(competitionPlayerStatus);
		competitionPlayer.setUser(user);

		// Store the new competition player
		competitionPlayerRepository.save(competitionPlayer);

		// Return the stored competition player
		return new CompetitionPlayerDTO(competitionPlayer);
	}

	/**
	 * Add a new competition team to the current competition (id = id of the competition,
	 * team which should be added as a competition team)
	 * @param competitionTeamAddDTO the Data Transfer Object with the id of the
	 * competition and the team which should be added as a competition team
	 * @return the Data Transfer Object of the new added competition team
	 */
	public CompetitionTeamDTO addCompetitionTeam(CompetitionTeamAddDTO competitionTeamAddDTO) {

		// Trying to fetch the competition with the competition-id which was given through
		// the Competition Team Add DTO
		// TODO: Change logic to Optional if the id is not present
		Competition competition = competitionRepository.findById(competitionTeamAddDTO.getId()).get();

		Team team = null;

		// Trying to fetch the team with the team-id which was given through the
		// Competition Team Add DTO
		// TODO: Change logic to Optional if the id is not present
		if (competitionTeamAddDTO.getTeamId() != null) {
			team = teamRepository.findById(competitionTeamAddDTO.getTeamId()).get();
		}

		// Fetch the billing status which should be set from the database or create this
		// one new
		BillingStatus billingStatus = billingStatusService.getOrCreateBillingStatus(BillingStatusType.NOT_PAYED);

		// Fetch the registration status which should be set from the database or create
		// this one new
		RegistrationStatus registrationStatus = registrationStatusService
				.getOrCreateRegistrationStatus(RegistrationStatusType.REGISTERED);

		// Add the team to the competition teams of the competition
		CompetitionTeam competitionTeam = new CompetitionTeam();
		competitionTeam.setCompetition(competition);
		competitionTeam.setCompetitionTeamName(competitionTeamAddDTO.getTeamname());
		competitionTeam.setPassword(competitionTeamAddDTO.getPassword());
		competitionTeam.setTeam(team);
		competitionTeam.addBillingStatus(billingStatus);
		competitionTeam.addRegistrationStatus(registrationStatus);

		// Go through the id of the users which should be in the competition team
		List<CompetitionPlayer> competitionPlayers = Arrays.stream(competitionTeamAddDTO.getPlayerIds())
				.map(playerId -> {
					// Trying to fetch the user with the user-id which was given through
					// the Competition Team Add DTO
					// TODO: Change logic to Optional if the id is not present
					User user = userRepository.findById(playerId).get();

					// Fetch the competition player status which should be set from the
					// database or create this one new
					CompetitionPlayerStatus competitionPlayerStatus = competitionPlayerStatusService
							.getOrCreateCompetitionPlayerStatus(CompetitionPlayerStatusType.PROMISED);

					// Add the user to the competition players of this competition team
					CompetitionPlayer competitionPlayer = new CompetitionPlayer();
					competitionPlayer.setCompetitionTeam(competitionTeam);
					competitionPlayer.setUser(user);
					competitionPlayer.addCompetitionPlayerStatus(competitionPlayerStatus);
					return competitionPlayer;

				}).collect(Collectors.toList());

		competitionTeam.setCompetitionPlayers(competitionPlayers);

		// Store the new competition team
		competitionTeamRepository.save(competitionTeam);

		// Return the stored competition team
		return new CompetitionTeamDTO(competitionTeam);
	}

	/**
	 * Update the competition
	 * @param competitionUpdateDTO values which should be updated
	 * @return the updated Data Transfer Object of the competition
	 */
	public CompetitionDTO updateCompetition(CompetitionUpdateDTO competitionUpdateDTO) {

		// Trying to fetch the competition with the id which was given through the
		// Competition Update DTO
		// TODO: Change logic to Optional if the id is not present
		Competition competition = competitionRepository.findById(competitionUpdateDTO.getId()).get();

		// Set the new values
		competition.setCompetitionName(competitionUpdateDTO.getCompetitionName());
		competition.setCompetitionStartTimestamp(competitionUpdateDTO.getCompetitionStartTimestamp());
		competition.setMinTeams(competitionUpdateDTO.getMinTeams());
		competition.setMaxTeams(competitionUpdateDTO.getMaxTeams());
		competition.setFee(competitionUpdateDTO.getFee());
		competition.setRegistrationStart(competitionUpdateDTO.getRegistrationStart());
		competition.setRegistrationEnd(competitionUpdateDTO.getRegistrationEnd());
		competition.setSetOfRules(competitionUpdateDTO.getSetOfRules());

		// Store the new competition
		competitionRepository.save(competition);

		// Return the stored competition
		return new CompetitionDTO(competition);
	}

	/**
	 * Update the competition player status of a given competition player id (id = id of
	 * the competition player, competition player status = new status which should be set)
	 * @param competitionPlayerStatusUpdateDTO the Data Transfer Object with the id of the
	 * competition player and the new competition player status
	 * @return a list of Competition Player Status Data Transfer Object (2 entries -> the
	 * last one which is now not valid anymore and the new one)
	 */
	public List<CompetitionPlayerStatusDTO> updateCompetitionPlayerStatus(
			CompetitionPlayerStatusUpdateDTO competitionPlayerStatusUpdateDTO) {

		// Trying to fetch the competition player with the id which was given through the
		// Competition Player Status Update DTO
		// TODO: Change logic to Optional if the id is not present
		CompetitionPlayer competitionPlayer = competitionPlayerRepository
				.findById(competitionPlayerStatusUpdateDTO.getId()).get();

		// Fetch the competition player status which should be set from the database or
		// create this one new
		CompetitionPlayerStatus competitionPlayerStatus = competitionPlayerStatusService
				.getOrCreateCompetitionPlayerStatus(competitionPlayerStatusUpdateDTO.getCompetitionPlayerStatusType());

		// Set the current timestamp to set the old valid value to invalid with validTo =
		// timestamp
		// And add the new status with validFrom and the current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());

		List<CompetitionPlayerStatusHistory> currentCompetitionPlayerStatusHistory = competitionPlayer
				.getCompetitionPlayerStatusHistories().stream().peek(competitionPlayerStatusHistory -> {
					if (competitionPlayerStatusHistory.getValidTo() == null) {
						competitionPlayerStatusHistory.setValidTo(now);
					}
				}).collect(Collectors.toList());

		currentCompetitionPlayerStatusHistory
				.add(new CompetitionPlayerStatusHistory(competitionPlayer, competitionPlayerStatus, now));

		// Set the new Competition Player Status History and save it to the database
		competitionPlayer.setCompetitionPlayerStatusHistories(currentCompetitionPlayerStatusHistory);
		competitionPlayerRepository.save(competitionPlayer);

		// Sort the history via the validFrom value
		currentCompetitionPlayerStatusHistory.sort(Comparator.comparing(CompetitionPlayerStatusHistory::getValidFrom));

		// Return the last 2 values
		// TODO: Check first if the size > 2 (otherwise the first value is not positive)
		return currentCompetitionPlayerStatusHistory
				.subList(currentCompetitionPlayerStatusHistory.size() - 2, currentCompetitionPlayerStatusHistory.size())
				.stream().map(CompetitionPlayerStatusDTO::new).collect(Collectors.toList());

	}

	/**
	 * Update the competition team
	 * @param competitionTeamUpdateDTO values which should be updated
	 * @return the updated Data Transfer Object of the competition team
	 */
	public CompetitionTeamDTO updateCompetitionTeam(CompetitionTeamUpdateDTO competitionTeamUpdateDTO) {

		// Trying to fetch the competition team with the id which was given through the
		// Competition Team Update DTO
		// TODO: Change logic to Optional if the id is not present
		CompetitionTeam competitionTeam = competitionTeamRepository.findById(competitionTeamUpdateDTO.getId()).get();

		if (competitionTeamUpdateDTO.getTeamId() != null) {
			// Trying to fetch the team with the team-id which was given through the
			// Competition Team Update DTO
			// TODO: Change logic to Optional if the id is not present
			Team team = teamRepository.findById(competitionTeamUpdateDTO.getTeamId()).get();
			competitionTeam.setTeam(team);
		}
		else {
			competitionTeam.setTeam(null);
		}
		competitionTeam.setCompetitionTeamName(competitionTeamUpdateDTO.getTeamname());

		// Store the new competition team
		competitionTeamRepository.save(competitionTeam);

		// Return the stored competition team
		return new CompetitionTeamDTO(competitionTeam);
	}

}
