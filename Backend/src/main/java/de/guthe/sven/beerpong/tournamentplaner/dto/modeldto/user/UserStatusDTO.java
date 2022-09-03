package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.UserStatusHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class UserStatusDTO extends EnumDTO {

	@NotNull(message = "userStatus in UserStatusDTO have to be set.")
	private UserStatusType userStatus;

	@NotNull(message = "validFrom in UserStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public UserStatusDTO(Long id, UserStatusType userStatusType, Timestamp validFrom, Timestamp validTo) {
		super(id, userStatusType.name());
		this.userStatus = userStatusType;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public UserStatusDTO(UserStatusHistory userStatusHistory) {
		super(userStatusHistory.getId(), userStatusHistory.getUserStatus().getUserStatus().name());
		this.userStatus = userStatusHistory.getUserStatus().getUserStatus();
		this.validFrom = userStatusHistory.getValidFrom();
		this.validTo = userStatusHistory.getValidTo();
	}

	public UserStatusType getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusType userStatus) {
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

	@Override
	public String toString() {
		return "UserStatusDTO{" + "userStatus=" + userStatus + ", validFrom=" + validFrom + ", validTo=" + validTo
				+ ", id=" + id + '}';
	}

}
