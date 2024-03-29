package de.guthe.sven.beerpong.tournamentplaner.configuration;

import de.guthe.sven.beerpong.tournamentplaner.datatype.user.SecurityPrivilege;
import de.guthe.sven.beerpong.tournamentplaner.datatype.user.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.datatype.user.PredefinedPrivileges;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.*;
import de.guthe.sven.beerpong.tournamentplaner.model.user.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teaminvitationlink.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.PrivilegeRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teaminvitationlink.TeamInvitationLinkRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamCompositionStatusService;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserStatusRepository userStatusRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamInvitationLinkRepository teamInvitationLinkRepository;

	@Autowired
	private CompetitionRepository competitionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TeamCompositionStatusService teamCompositionStatusService;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (alreadySetup)
			return;

		Map<SecurityRole, List<SecurityPrivilege>> securityPrivilegesMap = PredefinedPrivileges.privileges;

		for (Map.Entry<SecurityRole, List<SecurityPrivilege>> entry : securityPrivilegesMap.entrySet()) {
			List<Privilege> privileges = entry.getValue().stream().map(this::createPrivilegeIfNotFound)
					.collect(Collectors.toList());
			createRoleIfNotFound(entry.getKey(), privileges);
		}

		UserStatus userStatus = new UserStatus();
		userStatus.setUserStatus(UserStatusType.ACTIVE);

		userStatusRepository.save(userStatus);

		Role adminRole = roleRepository.findByName(SecurityRole.ROLE_ADMINISTRATOR.toString());
		Role moderatorRole = roleRepository.findByName(SecurityRole.ROLE_MODERATOR.toString());
		Role playerRole = roleRepository.findByName(SecurityRole.ROLE_PLAYER.toString());

		User adminUser = new User();
		adminUser.setFirstName("admin");
		adminUser.setLastName("admin");
		adminUser.setPassword(passwordEncoder.encode("admin"));
		adminUser.setEmail("admin@admin.com");
		adminUser.setRoles(Arrays.asList(adminRole, moderatorRole, playerRole));
		adminUser.setEnabled(true);
		adminUser.setGamerTag("admin");

		UserStatusHistory userStatusHistoryAdmin = new UserStatusHistory();
		userStatusHistoryAdmin.setUser(adminUser);
		userStatusHistoryAdmin.setUserStatus(userStatus);

		adminUser.addUserStatusHistory(userStatusHistoryAdmin);
		userRepository.save(adminUser);

		User moderatorUser = new User();
		moderatorUser.setFirstName("moderator");
		moderatorUser.setLastName("moderator");
		moderatorUser.setPassword(passwordEncoder.encode("moderator"));
		moderatorUser.setEmail("moderator@moderator.com");
		moderatorUser.setRoles(Arrays.asList(moderatorRole, playerRole));
		moderatorUser.setEnabled(true);
		moderatorUser.setGamerTag("moderator");

		UserStatusHistory userStatusHistoryModerator = new UserStatusHistory();
		userStatusHistoryModerator.setUser(moderatorUser);
		userStatusHistoryModerator.setUserStatus(userStatus);

		moderatorUser.addUserStatusHistory(userStatusHistoryModerator);
		userRepository.save(moderatorUser);

		User playerUser = new User();
		playerUser.setFirstName("player");
		playerUser.setLastName("player");
		playerUser.setPassword(passwordEncoder.encode("player"));
		playerUser.setEmail("player@player.com");
		playerUser.setRoles(Collections.singletonList(playerRole));
		playerUser.setEnabled(true);
		playerUser.setGamerTag("player");

		UserStatusHistory userStatusHistoryPlayer = new UserStatusHistory();
		userStatusHistoryPlayer.setUser(playerUser);
		userStatusHistoryPlayer.setUserStatus(userStatus);

		playerUser.addUserStatusHistory(userStatusHistoryPlayer);
		userRepository.save(playerUser);

		User testUser = new User();
		testUser.setFirstName("test");
		testUser.setLastName("test");
		testUser.setPassword(passwordEncoder.encode("test"));
		testUser.setEmail("test@test.com");
		testUser.setRoles(Collections.singletonList(playerRole));
		testUser.setEnabled(true);
		testUser.setGamerTag("test");

		UserStatusHistory userStatusHistoryTest = new UserStatusHistory();
		userStatusHistoryTest.setUser(testUser);
		userStatusHistoryTest.setUserStatus(userStatus);

		testUser.addUserStatusHistory(userStatusHistoryTest);
		userRepository.save(testUser);

		TeamStatus teamStatus = new TeamStatus();
		teamStatus.setTeamStatusDescription(TeamStatusType.ACTIVE);

		TeamCompositionStatus teamCompositionStatus = teamCompositionStatusService
				.getOrCreateTeamCompositionStatus(TeamCompositionStatusType.PROMISED);

		Team teamPlayer = new Team();
		teamPlayer.setPassword("password");
		teamPlayer.setPlayerTeam(true);
		teamPlayer.setTeamName(playerUser.getGamerTag());
		teamPlayer.addUser(playerUser, true, teamCompositionStatus);
		teamPlayer.addTeamStatus(teamStatus);

		teamRepository.save(teamPlayer);

		TeamInvitationLink teamInvitationLink1 = new TeamInvitationLink();
		teamInvitationLink1.setTeamInvitationLink("InvitationLink1");

		teamInvitationLinkRepository.save(teamInvitationLink1);

		TeamInvitationLink teamInvitationLink2 = new TeamInvitationLink();
		teamInvitationLink2.setTeamInvitationLink("InvitationLink2");

		teamInvitationLinkRepository.save(teamInvitationLink2);

		Team teamModAdmin = new Team();
		teamModAdmin.setPassword("password");
		teamModAdmin.setPlayerTeam(false);
		teamModAdmin.setTeamName("teamModAdmin");
		teamModAdmin.addUser(moderatorUser, true, teamCompositionStatus);
		teamModAdmin.addUser(adminUser, true, teamCompositionStatus);
		teamModAdmin.addUser(testUser, true, teamCompositionStatus);
		teamModAdmin.addTeamInvitationLink(teamInvitationLink1);
		teamModAdmin.addTeamInvitationLink(teamInvitationLink2);
		teamModAdmin.addTeamStatus(teamStatus);

		teamRepository.save(teamModAdmin);

		CompetitionPlayerStatus competitionPlayerStatus = new CompetitionPlayerStatus();
		competitionPlayerStatus.setCompetitionPlayerStatusDescription(CompetitionPlayerStatusType.PROMISED);

		CompetitionPlayer competitionPlayerMod = new CompetitionPlayer();
		competitionPlayerMod.setUser(moderatorUser);
		competitionPlayerMod.addCompetitionPlayerStatus(competitionPlayerStatus);

		CompetitionPlayer competitionPlayerAdmin = new CompetitionPlayer();
		competitionPlayerAdmin.setUser(adminUser);
		competitionPlayerAdmin.addCompetitionPlayerStatus(competitionPlayerStatus);

		BillingStatus billingStatus = new BillingStatus();
		billingStatus.setBillingStatusDescription(BillingStatusType.PAYED);

		RegistrationStatus registrationStatus = new RegistrationStatus();
		registrationStatus.setRegistrationStatusDescription(RegistrationStatusType.REGISTERED);

		CompetitionTeam competitionTeam = new CompetitionTeam();
		competitionTeam.setCompetitionTeamName("CompetitionTeamName");
		competitionTeam.setTeam(teamModAdmin);
		competitionTeam.setPassword("password");
		competitionTeam.addCompetitionPlayer(competitionPlayerMod);
		competitionTeam.addBillingStatus(billingStatus);
		competitionTeam.addRegistrationStatus(registrationStatus);

		CompetitionTeam competitionTeam2 = new CompetitionTeam();
		competitionTeam2.setCompetitionTeamName("CompetitionTeamName2");
		competitionTeam2.setTeam(teamModAdmin);
		competitionTeam2.setPassword("password");
		competitionTeam2.addCompetitionPlayer(competitionPlayerAdmin);
		competitionTeam2.addBillingStatus(billingStatus);
		competitionTeam2.addRegistrationStatus(registrationStatus);

		CompetitionAdminStatus competitionAdminStatus = new CompetitionAdminStatus();
		competitionAdminStatus.setCompetitionAdminStatusDescription(CompetitionAdminStatusType.PROMISED);

		CompetitionAdmin competitionAdmin = new CompetitionAdmin();
		competitionAdmin.setUser(adminUser);
		competitionAdmin.addCompetitionAdminStatus(competitionAdminStatus);

		CompetitionStatus competitionStatus = new CompetitionStatus();
		competitionStatus.setCompetitionStatusType(CompetitionStatusType.REGISTRATION_PHASE);

		Competition competition = new Competition();
		competition.setCompetitionName("Competition");
		competition.setCompetitionStartTimestamp(new Timestamp(System.currentTimeMillis()));
		competition.setFee(1.00);
		competition.setMinTeams(10);
		competition.setMaxTeams(20);
		competition.setRegistrationStart(new Timestamp(System.currentTimeMillis()));
		competition.setRegistrationEnd(new Timestamp(System.currentTimeMillis()));
		competition.setSetOfRules("abc.de");
		competition.addCompetitionTeam(competitionTeam);
		competition.addCompetitionTeam(competitionTeam2);
		competition.addCompetitionAdmin(competitionAdmin);
		competition.addCompetitionStatus(competitionStatus);

		competitionRepository.save(competition);

		alreadySetup = true;

	}

	@Transactional
	Privilege createPrivilegeIfNotFound(SecurityPrivilege securityPrivilege) {
		Privilege privilege = privilegeRepository.findByName(securityPrivilege.toString());
		if (privilege == null) {
			privilege = new Privilege(securityPrivilege);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(SecurityRole securityRole, Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(securityRole.toString());
		if (role == null) {
			role = new Role(securityRole);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

}
