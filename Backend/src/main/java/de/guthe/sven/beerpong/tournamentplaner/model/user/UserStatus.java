package de.guthe.sven.beerpong.tournamentplaner.model.user;

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
	private Long id;

	@Column(name = "userstatus", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private UserStatusType userStatus;

	@OneToMany(mappedBy = "userStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private List<UserStatusHistory> userStatusHistories;

	public UserStatus() {
	}

	public UserStatus(UserStatusType userStatus) {
		this.userStatus = userStatus;
	}

	public Long getId() {
		return id;
	}

	public UserStatusType getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusType userStatus) {
		this.userStatus = userStatus;
	}

	public List<UserStatusHistory> getUserStatusHistories() {
		return userStatusHistories;
	}

	public void setUserStatusHistories(List<UserStatusHistory> userStatusHistories) {
		this.userStatusHistories = userStatusHistories;
	}

	@Override
	public String toString() {
		return "UserStatus{" + "id=" + id + ", userStatus=" + userStatus + ", userStatusHistories="
				+ userStatusHistories + '}';
	}

}
