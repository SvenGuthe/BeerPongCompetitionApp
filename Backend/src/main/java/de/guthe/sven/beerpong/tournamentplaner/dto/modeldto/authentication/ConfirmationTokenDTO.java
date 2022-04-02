package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ConfirmationToken;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ConfirmationTokenDTO extends ID {

    @NotNull(message = "confirmationToken in ConfirmationTokenDTO have to be set.")
    private String confirmationToken;

    @NotNull(message = "createdDate in ConfirmationTokenDTO have to be set.")
    private Timestamp createdDate;

    public ConfirmationTokenDTO(Long id, String confirmationToken, Timestamp createdDate) {
        super(id);
        this.confirmationToken = confirmationToken;
        this.createdDate = createdDate;
    }

    public ConfirmationTokenDTO(ConfirmationToken confirmationToken) {
        super(confirmationToken.getId());
        this.confirmationToken = confirmationToken.getConfirmationToken();
        this.createdDate = confirmationToken.getCreatedDate();
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
