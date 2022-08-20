package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE LOWER(r.role) = LOWER(?1)")
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleid", nullable = false)
	private Long id;

	@Column(name = "role", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private SecurityRole role;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Collection<UserRole> userRoles;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Collection<RolePrivilege> rolePrivileges;

	public Role() {
	}

	public Role(SecurityRole role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public SecurityRole getRole() {
		return role;
	}

	public void setRole(SecurityRole role) {
		this.role = role;
	}

	public Collection<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Collection<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Collection<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(Collection<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		Collection<RolePrivilege> rolePrivileges = privileges.stream().map(privilege -> {
			RolePrivilege rolePrivilege = new RolePrivilege();
			rolePrivilege.setPrivilege(privilege);
			rolePrivilege.setRole(this);
			return rolePrivilege;
		}).collect(Collectors.toList());
		setRolePrivileges(rolePrivileges);
	}

}
