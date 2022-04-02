package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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

	@ManyToMany(mappedBy = "roles", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Collection<User> users;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"),
			inverseJoinColumns = @JoinColumn(name = "privilegeid", referencedColumnName = "privilegeid"))
	@JsonIgnore
	private Collection<Privilege> privileges;

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

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

	public void addPrivilege(Privilege privilege) {
		if (this.privileges == null) {
			this.privileges = new ArrayList<>();
		}
		this.privileges.add(privilege);
	}

}
