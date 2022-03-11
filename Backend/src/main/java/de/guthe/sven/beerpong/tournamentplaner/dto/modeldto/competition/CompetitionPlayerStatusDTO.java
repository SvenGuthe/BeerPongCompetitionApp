package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayerStatus;

import java.sql.Timestamp;

public class CompetitionPlayerStatusDTO extends EnumDTO {

    private CompetitionPlayerStatusType competitionPlayerStatusDescription;

    private Timestamp creationTime;

    public CompetitionPlayerStatusDTO(Long id, CompetitionPlayerStatusType competitionPlayerStatusDescription, Timestamp creationTime) {
        super(id, competitionPlayerStatusDescription.name());
        this.competitionPlayerStatusDescription = competitionPlayerStatusDescription;
        this.creationTime = creationTime;
    }

    public CompetitionPlayerStatusDTO(CompetitionPlayerStatus competitionPlayerStatus) {
        super(competitionPlayerStatus.getId(), competitionPlayerStatus.getCompetitionPlayerStatusDescription().name());
        this.competitionPlayerStatusDescription = competitionPlayerStatus.getCompetitionPlayerStatusDescription();
        this.creationTime = competitionPlayerStatus.getCreationTime();
    }

    public CompetitionPlayerStatusType getCompetitionPlayerStatusDescription() {
        return competitionPlayerStatusDescription;
    }

    public void setCompetitionPlayerStatusDescription(CompetitionPlayerStatusType competitionPlayerStatusDescription) {
        this.competitionPlayerStatusDescription = competitionPlayerStatusDescription;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

}
