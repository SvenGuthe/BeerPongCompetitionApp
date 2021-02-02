package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;

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
	@Enumerated(EnumType.STRING)
	private UserStatusType userStatus;

	@OneToMany(mappedBy = "userStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private List<User> users;

	public UserStatus() {
	}

	public Long getUserStatusId() {
		return userStatusId;
	}

	public UserStatusType getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusType userStatus) {
		this.userStatus = userStatus;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
