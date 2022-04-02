package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

public class CompetitionAdminStatusUpdateDTO extends ID {

    private CompetitionAdminStatusType competitionAdminStatusType;

    public CompetitionAdminStatusUpdateDTO(Long id, CompetitionAdminStatusType competitionAdminStatusType) {
        super(id);
        this.competitionAdminStatusType = competitionAdminStatusType;
    }

    public CompetitionAdminStatusType getCompetitionAdminStatusType() {
        return competitionAdminStatusType;
    }

    public void setCompetitionAdminStatusType(CompetitionAdminStatusType competitionAdminStatusType) {
        this.competitionAdminStatusType = competitionAdminStatusType;
    }
}
