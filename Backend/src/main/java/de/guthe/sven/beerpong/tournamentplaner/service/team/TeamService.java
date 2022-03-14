package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.TeamUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDetailDTO transformTeamToTeamDetailDTO(Long id) throws Exception {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            return transformTeamToTeamDetailDTO(team.get());
        } else {
            throw new Exception("Team not found with id " + id);
        }
    }

    public TeamDetailDTO transformTeamToTeamDetailDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO(team);

        Collection<TeamUserDTO> teamUsers = team.getTeamCompositions().stream().map(TeamUserDTO::new).collect(Collectors.toList());

        Collection<CompetitionDTO> competitions = team.getCompetitionTeams().stream().map(competitionTeam -> new CompetitionDTO(competitionTeam.getCompetition())).collect(Collectors.toList());

        return new TeamDetailDTO(
                teamDTO,
                teamUsers,
                competitions
        );
    }

}
