package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = UserID
public class ConfirmationTokenAddDTO extends ID {

	@NotNull(message = "ConfirmationToken have to be set!")
	private String confirmationToken;

	public ConfirmationTokenAddDTO(Long id, String confirmationToken) {
		super(id);
		this.confirmationToken = confirmationToken;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

}
