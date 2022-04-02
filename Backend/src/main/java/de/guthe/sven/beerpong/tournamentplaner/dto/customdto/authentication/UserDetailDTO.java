package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.UserTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class UserDetailDTO {

    @NotNull(message = "user in UserDetailDTO have to be set.")
    UserDTO user;

    @NotNull(message = "teams in UserDetailDTO have to be set.")
    Collection<UserTeamDTO> teams;

    @NotNull(message = "competitionsWhereAdmin have to be set.")
    Collection<CompetitionDTO> competitionsWhereAdmin;

    @NotNull(message = "competitionsWherePlayer have to be set.")
    Collection<CompetitionDTO> competitionsWherePlayer;

    public UserDetailDTO(UserDTO user, Collection<UserTeamDTO> teams, Collection<CompetitionDTO> competitionsWhereAdmin, Collection<CompetitionDTO> competitionsWherePlayer) {
        this.user = user;
        this.teams = teams;
        this.competitionsWhereAdmin = competitionsWhereAdmin;
        this.competitionsWherePlayer = competitionsWherePlayer;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Collection<UserTeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(Collection<UserTeamDTO> teams) {
        this.teams = teams;
    }

    public Collection<CompetitionDTO> getCompetitionsWhereAdmin() {
        return competitionsWhereAdmin;
    }

    public void setCompetitionsWhereAdmin(Collection<CompetitionDTO> competitionsWhereAdmin) {
        this.competitionsWhereAdmin = competitionsWhereAdmin;
    }

    public Collection<CompetitionDTO> getCompetitionsWherePlayer() {
        return competitionsWherePlayer;
    }

    public void setCompetitionsWherePlayer(Collection<CompetitionDTO> competitionsWherePlayer) {
        this.competitionsWherePlayer = competitionsWherePlayer;
    }
}
