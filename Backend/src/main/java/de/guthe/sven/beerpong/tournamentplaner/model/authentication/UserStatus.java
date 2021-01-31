package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userstatus")
public class UserStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userstatusid", nullable = false)
	private Long userStatusId;

	@Column(name = "userstatus", nullable = false)
	private String userStatus;

	@OneToMany(mappedBy = "userStatus", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<User> users;

	public UserStatus() {
	}

	public Long getUserStatusId() {
		return userStatusId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
