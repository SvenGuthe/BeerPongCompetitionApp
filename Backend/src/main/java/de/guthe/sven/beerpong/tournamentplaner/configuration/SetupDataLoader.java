package de.guthe.sven.beerpong.tournamentplaner.configuration;

import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityPrivilege;
import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.datatype.PredefinedPrivileges;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.PrivilegeRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamInvitationLinkRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
	private PasswordEncoder passwordEncoder;

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
		userStatus.setUserStatus("active");

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
		adminUser.setUserStatus(userStatus);
		userRepository.save(adminUser);

		User moderatorUser = new User();
		moderatorUser.setFirstName("moderator");
		moderatorUser.setLastName("moderator");
		moderatorUser.setPassword(passwordEncoder.encode("moderator"));
		moderatorUser.setEmail("moderator@moderator.com");
		moderatorUser.setRoles(Arrays.asList(moderatorRole, playerRole));
		moderatorUser.setEnabled(true);
		moderatorUser.setGamerTag("moderator");
		moderatorUser.setUserStatus(userStatus);
		userRepository.save(moderatorUser);

		User playerUser = new User();
		playerUser.setFirstName("player");
		playerUser.setLastName("player");
		playerUser.setPassword(passwordEncoder.encode("player"));
		playerUser.setEmail("player@player.com");
		playerUser.setRoles(Arrays.asList(playerRole));
		playerUser.setEnabled(true);
		playerUser.setGamerTag("player");
		playerUser.setUserStatus(userStatus);
		userRepository.save(playerUser);

		Team teamPlayer = new Team();
		teamPlayer.setPassword("password");
		teamPlayer.setPlayerTeam(true);
		teamPlayer.setTeamName(playerUser.getGamerTag());
		teamPlayer.addUser(playerUser);

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
		teamModAdmin.addUser(moderatorUser);
		teamModAdmin.addUser(adminUser);
		teamModAdmin.addTeamInvitationLink(teamInvitationLink1);
		teamModAdmin.addTeamInvitationLink(teamInvitationLink2);

		teamRepository.save(teamModAdmin);

		alreadySetup = true;

	}

	@Transactional
	Privilege createPrivilegeIfNotFound(SecurityPrivilege securityPrivilege) {
		Privilege privilege = privilegeRepository.findByName(securityPrivilege.toString());
		if (privilege == null) {
			privilege = new Privilege(securityPrivilege.toString());
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(SecurityRole securityRole, Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(securityRole.toString());
		if (role == null) {
			role = new Role(securityRole.toString());
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

}
