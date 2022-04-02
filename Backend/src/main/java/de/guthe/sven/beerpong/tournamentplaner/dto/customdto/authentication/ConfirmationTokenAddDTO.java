package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = UserID
public class ConfirmationTokenAddDTO extends ID {

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
