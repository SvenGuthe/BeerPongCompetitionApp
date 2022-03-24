package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = Team ID
public class TeamCompositionAddDTO extends ID {

    private Long userId;

    private Boolean isAdmin;

    public TeamCompositionAddDTO(Long id, Long userId, Boolean isAdmin) {
        super(id);
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

}
