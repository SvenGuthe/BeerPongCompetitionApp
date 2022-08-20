package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "userstatushistory")
public class UserStatusHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userstatushistoryid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	@JsonIgnore
	private User user;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userstatusid", nullable = false)
	@JsonIgnore
	private UserStatus userStatus;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	public UserStatusHistory(User user, UserStatus userStatus, Timestamp validFrom) {
		this.user = user;
		this.userStatus = userStatus;
		this.validFrom = validFrom;
	}

	public UserStatusHistory() {
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
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

}
