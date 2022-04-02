package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

public class CompetitionAdminAddDTO extends ID {

    @NotNull(message = "userId in CompetitionAdminAddDTO have to be set.")
    private Long userId;

    public CompetitionAdminAddDTO(Long id, Long userId) {
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
