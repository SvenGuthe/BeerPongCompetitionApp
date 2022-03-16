package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

public class CompetitionStatusUpdateDTO extends ID {

    private CompetitionStatusType competitionStatusType;

    public CompetitionStatusUpdateDTO(Long id, CompetitionStatusType competitionStatusType) {
        super(id);
        this.competitionStatusType = competitionStatusType;
    }

    public CompetitionStatusType getCompetitionStatusType() {
        return competitionStatusType;
    }

    public void setCompetitionStatusType(CompetitionStatusType competitionStatusType) {
        this.competitionStatusType = competitionStatusType;
    }
}
