package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = CompetitionTeam ID
public class CompetitionPlayerStatusUpdateDTO extends ID {

    CompetitionPlayerStatusType competitionPlayerStatusType;

    public CompetitionPlayerStatusUpdateDTO(Long id, CompetitionPlayerStatusType competitionPlayerStatusType) {
        super(id);
        this.competitionPlayerStatusType = competitionPlayerStatusType;
    }

    public CompetitionPlayerStatusType getCompetitionPlayerStatusType() {
        return competitionPlayerStatusType;
    }

    public void setCompetitionPlayerStatusType(CompetitionPlayerStatusType competitionPlayerStatusType) {
        this.competitionPlayerStatusType = competitionPlayerStatusType;
    }

}
