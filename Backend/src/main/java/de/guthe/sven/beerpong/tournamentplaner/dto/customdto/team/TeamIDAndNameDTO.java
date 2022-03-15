package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

public class TeamIDAndNameDTO  extends ID {

    private String teamName;

    public TeamIDAndNameDTO(Long id, String teamName) {
        super(id);
        this.teamName = teamName;
    }

    public TeamIDAndNameDTO(Team team) {
        super(team.getId());
        this.teamName = team.getTeamName();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
