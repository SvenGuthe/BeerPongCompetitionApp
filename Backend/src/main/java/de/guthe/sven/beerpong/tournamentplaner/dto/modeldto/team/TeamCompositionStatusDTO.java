package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamCompositionStatusHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class TeamCompositionStatusDTO extends EnumDTO {

    @NotNull(message = "teamCompositionStatusType in TeamCompositionStatusDTO have to be set.")
    private TeamCompositionStatusType teamCompositionStatusType;

    @NotNull(message = "validFrom in UserStatusDTO have to be set.")
    private Timestamp validFrom;

    private Timestamp validTo;

    public TeamCompositionStatusDTO(Long id, TeamCompositionStatusType teamCompositionStatusType, Timestamp validFrom, Timestamp validTo) {
        super(id, teamCompositionStatusType.name());
        this.teamCompositionStatusType = teamCompositionStatusType;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public TeamCompositionStatusDTO(TeamCompositionStatusHistory teamCompositionStatusHistory) {
        super(teamCompositionStatusHistory.getId(), teamCompositionStatusHistory.getTeamCompositionStatus().getTeamCompositionStatusType().name());
        this.teamCompositionStatusType = teamCompositionStatusHistory.getTeamCompositionStatus().getTeamCompositionStatusType();
        this.validFrom = teamCompositionStatusHistory.getValidFrom();
        this.validTo = teamCompositionStatusHistory.getValidTo();
    }

    public TeamCompositionStatusType getTeamCompositionStatusType() {
        return teamCompositionStatusType;
    }

    public void setTeamCompositionStatusType(TeamCompositionStatusType teamCompositionStatusType) {
        this.teamCompositionStatusType = teamCompositionStatusType;
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

}
