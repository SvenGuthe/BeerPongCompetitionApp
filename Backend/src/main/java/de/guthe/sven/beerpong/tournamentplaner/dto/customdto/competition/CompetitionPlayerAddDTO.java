package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = CompetitionTeam ID
public class CompetitionPlayerAddDTO extends ID {

    private Long userId;

    public CompetitionPlayerAddDTO(Long id, Long userId) {
        super(id);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
