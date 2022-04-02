package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;

public class UserIDAndGamerTagDTO extends ID {

    public String gamerTag;

    public UserIDAndGamerTagDTO(Long id, String gamerTag) {
        super(id);
        this.gamerTag = gamerTag;
    }

    public UserIDAndGamerTagDTO(IUserIDAndGamerTagDTO iUserIDAndGamerTagDTO) {
        super(iUserIDAndGamerTagDTO.getUserId());
        this.gamerTag = iUserIDAndGamerTagDTO.getGamerTag();
    }

    public UserIDAndGamerTagDTO(User user) {
        super(user.getId());
        this.gamerTag = user.getGamerTag();
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

}
