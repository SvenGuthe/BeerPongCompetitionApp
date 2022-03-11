package de.guthe.sven.beerpong.tournamentplaner.service.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.UserTeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailDTO transformUserToUserDetailDTO(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return transformUserToUserDetailDTO(user.get());
        } else {
            throw new Exception("User not found with id " + id);
        }
    }

    public UserDetailDTO transformUserToUserDetailDTO(User user) {

        UserDTO userDTO = new UserDTO(user);

        Collection<UserTeamDTO> userTeams = user.getTeamCompositions().stream().map(UserTeamDTO::new).collect(Collectors.toList());

        Collection<CompetitionDTO> competitionsWhereAdmin = user.getCompetitionAdmins().stream().map(CompetitionAdmin::getCompetition).map(CompetitionDTO::new).collect(Collectors.toList());
        Collection<CompetitionDTO> competitionsWherePlayer =  user.getCompetitionPlayers().stream().map(competitionPlayer -> new CompetitionDTO(competitionPlayer.getCompetitionTeam().getCompetition())).collect(Collectors.toList());

        return new UserDetailDTO(
                userDTO,
                userTeams,
                competitionsWhereAdmin,
                competitionsWherePlayer
        );
    }

}
