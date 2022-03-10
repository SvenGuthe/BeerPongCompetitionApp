package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdmin;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionAdminDTO extends ID {

    private CompetitionDTO competitionDTO;

    private UserDTO userDTO;

    private Timestamp creationTime;

    private Collection<CompetitionAdminStatusDTO> competitionAdminStatus;

    public CompetitionAdminDTO(Long id, CompetitionDTO competitionDTO, UserDTO userDTO, Timestamp creationTime, Collection<CompetitionAdminStatusDTO> competitionAdminStatus) {
        super(id);
        this.competitionDTO = competitionDTO;
        this.userDTO = userDTO;
        this.creationTime = creationTime;
        this.competitionAdminStatus = competitionAdminStatus;
    }

    public CompetitionAdminDTO(CompetitionAdmin competitionAdmin) {
        super(competitionAdmin.getId());
        this.competitionDTO = new CompetitionDTO(competitionAdmin.getCompetition());
        this.userDTO = new UserDTO(competitionAdmin.getUser());
        this.creationTime = competitionAdmin.getCreationTime();
        this.competitionAdminStatus = competitionAdmin.getCompetitionAdminStatusHistories().stream().map(CompetitionAdminStatusDTO::new).collect(Collectors.toList());
    }

    public CompetitionDTO getCompetitionDTO() {
        return competitionDTO;
    }

    public void setCompetitionDTO(CompetitionDTO competitionDTO) {
        this.competitionDTO = competitionDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Collection<CompetitionAdminStatusDTO> getCompetitionAdminStatus() {
        return competitionAdminStatus;
    }

    public void setCompetitionAdminStatus(Collection<CompetitionAdminStatusDTO> competitionAdminStatus) {
        this.competitionAdminStatus = competitionAdminStatus;
    }
}
