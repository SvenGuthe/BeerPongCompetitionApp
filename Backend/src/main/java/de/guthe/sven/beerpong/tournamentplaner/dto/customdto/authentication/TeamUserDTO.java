package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class TeamUserDTO extends ID {

    @NotNull(message = "user in TeamUserDTO have to be set.")
    private UserDTO user;

    @NotNull(message = "isAdmin in TeamUserDTO have to be set.")
    private Boolean isAdmin;

    @NotNull(message = "creationTime in TeamUserDTO have to be set.")
    private Timestamp creationTime;

    public TeamUserDTO(Long id, UserDTO user, Boolean isAdmin, Timestamp creationTime) {
        super(id);
        this.user = user;
        this.isAdmin = isAdmin;
        this.creationTime = creationTime;
    }

    public TeamUserDTO(TeamComposition teamComposition) {
        super(teamComposition.getId());
        this.user = new UserDTO(teamComposition.getUser());
        this.isAdmin = teamComposition.getAdmin();
        this.creationTime = teamComposition.getCreationTime();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
