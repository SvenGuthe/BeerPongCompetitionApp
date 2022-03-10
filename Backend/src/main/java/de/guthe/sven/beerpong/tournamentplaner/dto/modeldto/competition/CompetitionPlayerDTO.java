package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayer;

import java.sql.Timestamp;

public class CompetitionPlayerDTO extends ID {

    private UserDTO user;

    private CompetitionPlayerStatusDTO playerStatusDTO;

    private Timestamp creationTime;

    public CompetitionPlayerDTO(Long id, UserDTO user, CompetitionPlayerStatusDTO playerStatusDTO, Timestamp creationTime) {
        super(id);
        this.user = user;
        this.playerStatusDTO = playerStatusDTO;
        this.creationTime = creationTime;
    }

    public CompetitionPlayerDTO(CompetitionPlayer competitionPlayer) {
        super(competitionPlayer.getId());
        this.user = new UserDTO(competitionPlayer.getUser());
        this.playerStatusDTO = new CompetitionPlayerStatusDTO(competitionPlayer.getCompetitionPlayerStatus());
        this.creationTime = competitionPlayer.getCreationTime();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CompetitionPlayerStatusDTO getPlayerStatusDTO() {
        return playerStatusDTO;
    }

    public void setPlayerStatusDTO(CompetitionPlayerStatusDTO playerStatusDTO) {
        this.playerStatusDTO = playerStatusDTO;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
