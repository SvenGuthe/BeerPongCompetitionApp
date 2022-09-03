package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationTokenHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ConfirmationTokenDTO extends ID {

	@NotNull(message = "confirmationToken in ConfirmationTokenDTO have to be set.")
	private String confirmationToken;

	@NotNull(message = "validFrom in UserStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public ConfirmationTokenDTO(Long id, String confirmationToken, Timestamp validFrom, Timestamp validTo) {
		super(id);
		this.confirmationToken = confirmationToken;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public ConfirmationTokenDTO(ConfirmationTokenHistory confirmationTokenHistory) {
		super(confirmationTokenHistory.getId());
		this.confirmationToken = confirmationTokenHistory.getConfirmationToken().getConfirmationToken();
		this.validFrom = confirmationTokenHistory.getValidFrom();
		this.validTo = confirmationTokenHistory.getValidTo();
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
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
		return "ConfirmationTokenDTO{" + "confirmationToken='" + confirmationToken + '\'' + ", validFrom=" + validFrom
				+ ", validTo=" + validTo + ", id=" + id + '}';
	}

}
