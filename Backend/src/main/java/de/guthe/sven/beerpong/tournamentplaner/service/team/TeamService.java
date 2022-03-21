package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.TeamUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamInvitationLinkAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamInvitationLinkDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.*;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    TeamRepository teamRepository;

    TeamStatusRepository teamStatusRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository,
                       TeamStatusRepository teamStatusRepository) {
        this.teamRepository = teamRepository;
        this.teamStatusRepository = teamStatusRepository;
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

    public TeamDTO updateTeam(TeamUpdateDTO teamUpdateDTO) {
        Team team = teamRepository.findById(teamUpdateDTO.getId()).get();
        team.setTeamName(teamUpdateDTO.getTeamName());

        teamRepository.save(team);

        return new TeamDTO(team);
    }

    public List<TeamStatusDTO> updateTeamStatus(TeamStatusUpdateDTO teamStatusUpdateDTO) {
        Team team = teamRepository.findById(teamStatusUpdateDTO.getId()).get();

        List<TeamStatus> teamStatusList = teamStatusRepository.findByStatus(teamStatusUpdateDTO.getTeamStatusType().name());
        TeamStatus teamStatus;

        if (teamStatusList.size() == 0) {
            teamStatus = new TeamStatus(
                teamStatusUpdateDTO.getTeamStatusType()
            );
        } else {
            teamStatus = teamStatusList.get(0);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<TeamStatusHistory> currentTeamStatusHistory = team.getTeamStatusHistories().stream()
                .peek(teamStatusHistory -> {
                    if (teamStatusHistory.getValidTo() == null) {
                        teamStatusHistory.setValidTo(now);
                    }
                }).collect(Collectors.toList());

        currentTeamStatusHistory.add(new TeamStatusHistory(
                now,
                team,
                teamStatus
        ));

        teamStatus.setTeamStatusHistories(currentTeamStatusHistory);
        teamStatusRepository.save(teamStatus);

        return currentTeamStatusHistory.subList(currentTeamStatusHistory.size()-2, currentTeamStatusHistory.size())
                .stream().map(TeamStatusDTO::new).collect(Collectors.toList());

    }

    public TeamInvitationLinkDTO addTeamInvitationLink(TeamInvitationLinkAddDTO teamInvitationLinkAddDTO) {
        Team team = teamRepository.findById(teamInvitationLinkAddDTO.getId()).get();
        TeamInvitationLink teamInvitationLink = new TeamInvitationLink(teamInvitationLinkAddDTO.getTeamInvitationLink());
        team.addTeamInvitationLink(teamInvitationLink);
        teamRepository.save(team);

        return new TeamInvitationLinkDTO(team.getTeamInvitationLinkHistories().get(
                team.getTeamInvitationLinkHistories().size() - 1
        ));
    }

}
