package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

    CompetitionRepository competitionRepository;

    CompetitionStatusRepository competitionStatusRepository;

    UserRepository userRepository;

    TeamRepository teamRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, UserRepository userRepository, TeamRepository teamRepository, CompetitionStatusRepository competitionStatusRepository) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.competitionStatusRepository = competitionStatusRepository;
    }

    public CompetitionDetailDTO getCompetitionDetail(Long competitionId) {
        CompetitionDTO competition = new CompetitionDTO(competitionRepository.findById(competitionId).get());

        Collection<UserIDAndGamerTagDTO> possibleAdminUsers = userRepository.findAllPossibleAdmins(competitionId).stream().map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());
        Collection<UserIDAndGamerTagDTO> possiblePlayers = userRepository.findAllPossiblePlayers(competitionId).stream().map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());
        Collection<TeamAndUserDTO> teamAndUser = teamRepository.findAll().stream().map(TeamAndUserDTO::new).collect(Collectors.toList());

        return new CompetitionDetailDTO(competition, possibleAdminUsers, possiblePlayers, teamAndUser);
    }

    public List<CompetitionStatusDTO> updateCompetitionStatus(CompetitionStatusUpdateDTO competitionStatusUpdateDTO) {
        Competition competition = competitionRepository.findById(competitionStatusUpdateDTO.getId()).get();
        List<CompetitionStatus> allCompetitionStatus = competitionStatusRepository.findByStatus(competitionStatusUpdateDTO.getCompetitionStatusType());
        CompetitionStatus competitionStatus;

        if (allCompetitionStatus.size() == 0) {
            competitionStatus = new CompetitionStatus(competitionStatusUpdateDTO.getCompetitionStatusType());
        } else {
            competitionStatus = allCompetitionStatus.get(0);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<CompetitionStatusHistory> currentCompetitionStatusHistory = competition.getCompetitionStatusHistories().stream()
                .peek(competitionStatusHistory -> {
                    if (competitionStatusHistory.getValidTo() == null) {
                        competitionStatusHistory.setValidTo(now);
                    }
                }).collect(Collectors.toList());

        currentCompetitionStatusHistory.add(new CompetitionStatusHistory(
                now,
                competition,
                competitionStatus
        ));

        competition.setCompetitionStatusHistories(currentCompetitionStatusHistory);
        competitionRepository.save(competition);

        return currentCompetitionStatusHistory.subList(currentCompetitionStatusHistory.size()-2, currentCompetitionStatusHistory.size()).stream().map(CompetitionStatusDTO::new).collect(Collectors.toList());

    }

}
