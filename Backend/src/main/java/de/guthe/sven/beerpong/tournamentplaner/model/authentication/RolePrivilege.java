package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "roleprivilege")
public class RolePrivilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userroleid")
	private Long id;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "privilegeid", nullable = false)
	@JsonIgnore
	private Privilege privilege;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid", nullable = false)
	@JsonIgnore
	private Role role;

	public RolePrivilege() {
	}

	public RolePrivilege(Timestamp validFrom, Privilege privilege, Role role) {
		this.validFrom = validFrom;
		this.privilege = privilege;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
