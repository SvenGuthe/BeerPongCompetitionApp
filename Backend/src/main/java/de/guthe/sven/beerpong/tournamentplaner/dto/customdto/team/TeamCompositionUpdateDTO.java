package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = TeamComposition ID
public class TeamCompositionUpdateDTO extends ID {

    private Boolean isAdmin;

    public TeamCompositionUpdateDTO(Long id, Boolean isAdmin) {
        super(id);
        this.isAdmin = isAdmin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

}
