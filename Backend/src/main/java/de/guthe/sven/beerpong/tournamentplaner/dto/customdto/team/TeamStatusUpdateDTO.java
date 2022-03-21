package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = Team ID
public class TeamStatusUpdateDTO extends ID {

    private TeamStatusType teamStatusType;

    public TeamStatusUpdateDTO(Long id, TeamStatusType teamStatusType) {
        super(id);
        this.teamStatusType = teamStatusType;
    }

    public TeamStatusType getTeamStatusType() {
        return teamStatusType;
    }

    public void setTeamStatusType(TeamStatusType teamStatusType) {
        this.teamStatusType = teamStatusType;
    }

}
