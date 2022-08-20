package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "userrole")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userroleid")
	private Long id;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	@JsonIgnore
	private User user;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid", nullable = false)
	@JsonIgnore
	private Role role;

	public UserRole() {
	}

	public UserRole(Timestamp validFrom, User user, Role role) {
		this.validFrom = validFrom;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
