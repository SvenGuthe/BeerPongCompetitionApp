package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;

import java.sql.Timestamp;

public class TeamCompositionDTO extends ID {

    private TeamDTO team;

    private UserDTO user;

    private Boolean isAdmin;

    private Timestamp creationTime;

    public TeamCompositionDTO(Long id, TeamDTO team, UserDTO user, Boolean isAdmin, Timestamp creationTime) {
        super(id);
        this.team = team;
        this.user = user;
        this.isAdmin = isAdmin;
        this.creationTime = creationTime;
    }

    public TeamCompositionDTO(TeamComposition teamComposition) {
        super(teamComposition.getId());
        this.team = new TeamDTO(teamComposition.getTeam());
        this.user = new UserDTO(teamComposition.getUser());
        this.isAdmin = teamComposition.getAdmin();
        this.creationTime = teamComposition.getCreationTime();
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
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
