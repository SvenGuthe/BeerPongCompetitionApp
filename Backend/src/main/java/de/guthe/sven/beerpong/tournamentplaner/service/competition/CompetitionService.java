package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

    CompetitionRepository competitionRepository;

    UserRepository userRepository;

    TeamRepository teamRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public CompetitionDetailDTO getCompetitionDetail(Long competitionId) {

        CompetitionDTO competition = new CompetitionDTO(competitionRepository.findById(competitionId).get());

        Collection<UserIDAndGamerTagDTO> users = userRepository.findAll().stream().map(UserIDAndGamerTagDTO::new).collect(Collectors.toList());

        Collection<TeamAndUserDTO> teamAndUser = teamRepository.findAll().stream().map(TeamAndUserDTO::new).collect(Collectors.toList());

        return new CompetitionDetailDTO(competition, users, teamAndUser);

    }

}
