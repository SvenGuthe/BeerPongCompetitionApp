package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.*;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.UserInformationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.competition.CompetitionListDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.competition.CompetitionOverviewDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CompetitionService {

	private CompetitionRepository competitionRepository;

	private UserRepository userRepository;

	private ModelMapper modelMapper;

	@Autowired
	public CompetitionService(CompetitionRepository competitionRepository, UserRepository userRepository,
			ModelMapper modelMapper) {
		this.competitionRepository = competitionRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	public CompetitionListDTO getActiveClosedOwnCompetitions(int page, int size) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// ToDo: Add Check if this is possible to cast
		String email = ((UserDetails) principal).getUsername();
		User user = userRepository.findByEmail(email);

		Set<Privilege> privilegeSet = user.getRoles().stream().map(role -> new ArrayList<>(role.getPrivileges()))
				.flatMap(List::stream).collect(Collectors.toSet());

		PageRequest pageRequest = PageRequest.of(page, size);

		ArrayList<CompetitionStatusType> openCompetitionStatusTypes = new ArrayList<>();
		openCompetitionStatusTypes.add(CompetitionStatusType.REGISTRATION_PHASE);
		openCompetitionStatusTypes.add(CompetitionStatusType.COMPETITION_PLANNING_PHASE);
		openCompetitionStatusTypes.add(CompetitionStatusType.PRE_COMPETITION_PHASE);
		openCompetitionStatusTypes.add(CompetitionStatusType.RUNNING_COMPETITION_PHASE);
		Page<Competition> openCompetitionPage = competitionRepository.findCompetitionsByType(openCompetitionStatusTypes,
				pageRequest);
		List<Competition> openCompetitions = openCompetitionPage.getContent();
		Set<CompetitionOverviewDTO> openCompetitionOverviewDTO = openCompetitions.stream()
				.map(competition -> convertCompetitionToCompetitionOverviewDTO(competition, user))
				.collect(Collectors.toSet());

		ArrayList<CompetitionStatusType> closedCompetitionStatusTypes = new ArrayList<>();
		closedCompetitionStatusTypes.add(CompetitionStatusType.CLOSED_COMPETITION_PHASE);
		Page<Competition> closedCompetitionPage = competitionRepository
				.findCompetitionsByType(closedCompetitionStatusTypes, pageRequest);
		List<Competition> closedCompetitions = closedCompetitionPage.getContent();
		Set<CompetitionOverviewDTO> closedCompetitionOverviewDTO = closedCompetitions.stream()
				.map(competition -> convertCompetitionToCompetitionOverviewDTO(competition, user))
				.collect(Collectors.toSet());

		Page<Competition> ownCompetitionPage = competitionRepository.findOwnCompetitions(
				CompetitionAdminStatusType.PROMISED, user, CompetitionPlayerStatusType.PROMISED, pageRequest);
		List<Competition> ownCompetitions = ownCompetitionPage.getContent();
		Set<CompetitionOverviewDTO> ownCompetitionOverviewDTO = ownCompetitions.stream()
				.map(competition -> convertCompetitionToCompetitionOverviewDTO(competition, user))
				.collect(Collectors.toSet());

		UserInformationDTO userInformationDTO = convertUserToUserInformationDTO(user);
		userInformationDTO.setPrivileges(privilegeSet);

		CompetitionListDTO competitionListDTO = new CompetitionListDTO();
		competitionListDTO.setUser(userInformationDTO);
		competitionListDTO.setUpcomingCompetitions(openCompetitionOverviewDTO);
		competitionListDTO.setClosedCompetitions(closedCompetitionOverviewDTO);
		competitionListDTO.setOwnCompetitions(ownCompetitionOverviewDTO);

		return competitionListDTO;

	}

	private CompetitionOverviewDTO convertCompetitionToCompetitionOverviewDTO(Competition competition, User user) {
		CompetitionOverviewDTO competitionOverviewDTO = modelMapper.map(competition, CompetitionOverviewDTO.class);

		UserIDAndGamerTagDTO activeAdmins = getActiveAdmins(competition).get(0);
		competitionOverviewDTO.setAdmin(activeAdmins);

		Integer registeredTeams = getCountOfRegisteredTeams(competition);
		competitionOverviewDTO.setCurrentTeams(registeredTeams);

		Boolean isRegistered = isRegistered(competition, user);
		competitionOverviewDTO.setRegistered(isRegistered);

		Boolean isPaid = isPaid(competition, user);
		competitionOverviewDTO.setPayed(isPaid);

		Boolean isAdmin = activeAdmins.getUserId().equals(user.getUserId());
		competitionOverviewDTO.setUserIsAdmin(isAdmin);

		CompetitionStatusType competitionStatusType = getCompetitionStatus(competition);
		competitionOverviewDTO.setCompetitionStatusType(competitionStatusType);

		return competitionOverviewDTO;
	}

	private UserInformationDTO convertUserToUserInformationDTO(User user) {
		return modelMapper.map(user, UserInformationDTO.class);
	}

	private UserIDAndGamerTagDTO convertToUserIdAndGamerTagDTO(User user) {
		return modelMapper.map(user, UserIDAndGamerTagDTO.class);
	}

	private List<UserIDAndGamerTagDTO> getActiveAdmins(Competition competition) {
		return competition.getCompetitionAdmins().stream().map(CompetitionAdmin::getCompetitionAdminStatusHistories)
				.flatMap(competitionAdminStatusHistories -> {
					Stream<CompetitionAdminStatusHistory> currentlyPromisedAdmins = competitionAdminStatusHistories
							.stream()
							.filter(competitionAdminStatusHistory -> competitionAdminStatusHistory
									.getCompetitionAdminStatus()
									.getCompetitionAdminStatusDescription() == CompetitionAdminStatusType.PROMISED
									&& competitionAdminStatusHistory.getValidTo() == null);
					return currentlyPromisedAdmins.map(competitionAdminStatusHistory -> competitionAdminStatusHistory
							.getCompetitionAdmin().getUser());
				}).map(this::convertToUserIdAndGamerTagDTO).collect(Collectors.toList());
	}

	private Stream<RegistrationStatusHistory> getRegisteredTeams(Competition competition) {
		return competition.getCompetitionTeams().stream()
				.flatMap(competitionTeam -> competitionTeam.getRegistrationStatusHistories().stream()
						.filter(registrationStatusHistory -> registrationStatusHistory.getRegistrationStatus()
								.getRegistrationStatusDescription() == RegistrationStatusType.REGISTERED
								&& registrationStatusHistory.getValidTo() == null));
	}

	private Integer getCountOfRegisteredTeams(Competition competition) {
		return (int) getRegisteredTeams(competition).count();
	}

	private Boolean isRegistered(Competition competition, User user) {
		Stream<RegistrationStatusHistory> registeredTeams = getRegisteredTeams(competition);
		List<User> users = registeredTeams.flatMap(registrationStatusHistory -> registrationStatusHistory
				.getCompetitionTeam().getCompetitionPlayers().stream()
				.filter(competitionPlayer -> competitionPlayer.getCompetitionPlayerStatus()
						.getCompetitionPlayerStatusDescription() == CompetitionPlayerStatusType.PROMISED)
				.map(CompetitionPlayer::getUser)).collect(Collectors.toList());
		return users.contains(user);
	}

	private Boolean isPaid(Competition competition, User user) {
		Stream<RegistrationStatusHistory> registeredTeams = getRegisteredTeams(competition);

		List<BillingStatusType> billingStatusTypesUser = registeredTeams
				.map(RegistrationStatusHistory::getCompetitionTeam)
				.filter(competitionTeam -> competitionTeam.getCompetitionPlayers().stream()
						.map(competitionPlayer -> competitionPlayer.getUser() == user && competitionPlayer
								.getCompetitionPlayerStatus()
								.getCompetitionPlayerStatusDescription() == CompetitionPlayerStatusType.PROMISED)
						.collect(Collectors.toList()).contains(true))
				.flatMap(competitionTeam -> competitionTeam.getBillingStatusHistories().stream()
						.filter(billingStatusHistory -> billingStatusHistory.getValidTo() == null)
						.map(billingStatusHistory -> billingStatusHistory.getBillingStatus()
								.getBillingStatusDescription()))
				.collect(Collectors.toList());

		if (billingStatusTypesUser.size() == 1) {
			return billingStatusTypesUser.get(0) == BillingStatusType.PAYED;
		}
		else if (billingStatusTypesUser.size() == 0) {
			return false;
		}
		else {
			// ToDo: Exception
			return false;
		}
	}

	private CompetitionStatusType getCompetitionStatus(Competition competition) {
		return competition.getCompetitionStatusHistories().stream()
				.filter(competitionStatusHistory -> competitionStatusHistory.getValidTo() == null)
				.map(competitionStatusHistory -> competitionStatusHistory.getCompetitionStatus()
						.getCompetitionStatusType())
				.collect(Collectors.toList()).get(0);
	}

}
