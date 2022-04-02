package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = CompetitionTeamID
public class CompetitionTeamUpdateDTO extends ID {

    @NotNull(message = "teamname in CompetitionTeamUpdateDTO have to be set.")
    private String teamname;

    private Long teamId;

    public CompetitionTeamUpdateDTO(Long id, String teamname, Long teamId) {
        super(id);
        this.teamname = teamname;
        this.teamId = teamId;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

}
