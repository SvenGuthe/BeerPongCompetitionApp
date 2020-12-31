package de.guthe.sven.beerpong.tournamentplaner.configuration;

import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityPrivilege;
import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.model.login.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.login.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.login.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.login.PrivilegeRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.login.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        Privilege readAclPrivilege = createPrivilegeIfNotFound(SecurityPrivilege.READ_TESTOBJECT_PRIVILEGE);
        Privilege writeAclPrivilege = createPrivilegeIfNotFound(SecurityPrivilege.WRITE_TESTOBJECT_PRIVILEGE);

        List<Privilege> administratorPrivileges = Arrays.asList(readAclPrivilege, writeAclPrivilege);
        List<Privilege> moderatorPrivileges = Arrays.asList(readAclPrivilege, writeAclPrivilege);
        List<Privilege> playerPrivileges = Arrays.asList();

        createRoleIfNotFound(SecurityRole.ROLE_ADMINISTRATOR, administratorPrivileges);
        createRoleIfNotFound(SecurityRole.ROLE_MODERATOR, moderatorPrivileges);
        createRoleIfNotFound(SecurityRole.ROLE_PLAYER, playerPrivileges);

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
        userRepository.save(adminUser);

        User moderatorUser = new User();
        moderatorUser.setFirstName("moderator");
        moderatorUser.setLastName("moderator");
        moderatorUser.setPassword(passwordEncoder.encode("moderator"));
        moderatorUser.setEmail("moderator@moderator.com");
        moderatorUser.setRoles(Arrays.asList(moderatorRole, playerRole));
        moderatorUser.setEnabled(true);
        userRepository.save(moderatorUser);

        User playerUser = new User();
        playerUser.setFirstName("player");
        playerUser.setLastName("player");
        playerUser.setPassword(passwordEncoder.encode("player"));
        playerUser.setEmail("player@player.com");
        playerUser.setRoles(Arrays.asList(playerRole));
        playerUser.setEnabled(true);
        userRepository.save(playerUser);

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
