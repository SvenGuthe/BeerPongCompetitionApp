package de.guthe.sven.beerpong.tournamentplaner.configuration;

import de.guthe.sven.beerpong.tournamentplaner.model.user.*;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true,
					getAuthorities(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.isEnabled(), true, true, true,
				getAuthorities(user.getUserRoles().stream().filter(userRole -> userRole.getValidTo() == null)
						.map(UserRole::getRole).collect(Collectors.toList())));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		List<String> privilegesAndRoles = getPrivileges(roles);
		for (Role role : roles) {
			privilegesAndRoles.add(role.getRole().name());
		}
		return getGrantedAuthorities(privilegesAndRoles);
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(
					role.getRolePrivileges().stream().filter(rolePrivilege -> rolePrivilege.getValidTo() == null)
							.map(RolePrivilege::getPrivilege).collect(Collectors.toList()));
		}
		for (Privilege item : collection) {
			privileges.add(item.getPrivilege().name());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
