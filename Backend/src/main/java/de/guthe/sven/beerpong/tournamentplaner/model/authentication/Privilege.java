package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityPrivilege;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "Privilege.findByName", query = "SELECT p FROM Privilege p WHERE LOWER(p.privilege) = LOWER(?1)")
@Table(name = "privilege")
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "privilegeid", nullable = false)
	private Long id;

	@Column(name = "privilege", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private SecurityPrivilege privilege;

	@ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Collection<Role> roles;

	public Privilege() {
	}

	public Privilege(SecurityPrivilege privilege) {
		this.privilege = privilege;
	}

	public Long getId() {
		return id;
	}

	public SecurityPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(SecurityPrivilege privilege) {
		this.privilege = privilege;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
