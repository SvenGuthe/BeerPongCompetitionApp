package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionDTO extends ID {

    private String competitionName;

    private Timestamp competitionStartTimestamp;

    private Integer minTeams;

    private Integer maxTeams;

    private Double fee;

    private Timestamp registrationStart;

    private Timestamp registrationEnd;

    private String setOfRules;

    private Timestamp creationTime;

    private Collection<CompetitionStatusDTO> competitionStatus;

    private Collection<CompetitionTeamDTO> competitionTeams;

    private Collection<CompetitionAdminDTO> competitionAdmins;

    public CompetitionDTO(Long id, String competitionName, Timestamp competitionStartTimestamp, Integer minTeams, Integer maxTeams, Double fee, Timestamp registrationStart, Timestamp registrationEnd, String setOfRules, Timestamp creationTime, Collection<CompetitionStatusDTO> competitionStatus, Collection<CompetitionTeamDTO> competitionTeams, Collection<CompetitionAdminDTO> competitionAdmins) {
        super(id);
        this.competitionName = competitionName;
        this.competitionStartTimestamp = competitionStartTimestamp;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
        this.fee = fee;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.setOfRules = setOfRules;
        this.creationTime = creationTime;
        this.competitionStatus = competitionStatus;
        this.competitionTeams = competitionTeams;
        this.competitionAdmins = competitionAdmins;
    }

    public CompetitionDTO(Competition competition) {
        super(competition.getId());
        this.competitionName = competition.getCompetitionName();
        this.competitionStartTimestamp = competition.getCompetitionStartTimestamp();
        this.minTeams = competition.getMinTeams();
        this.maxTeams = competition.getMaxTeams();
        this.fee = competition.getFee();
        this.registrationStart = competition.getRegistrationStart();
        this.registrationEnd = competition.getRegistrationEnd();
        this.setOfRules = competition.getSetOfRules();
        this.creationTime = competition.getCreationTime();
        this.competitionStatus = competition.getCompetitionStatusHistories().stream().map(CompetitionStatusDTO::new).collect(Collectors.toList());
        this.competitionTeams = competition.getCompetitionTeams().stream().map(CompetitionTeamDTO::new).collect(Collectors.toList());
        this.competitionAdmins = competition.getCompetitionAdmins().stream().map(CompetitionAdminDTO::new).collect(Collectors.toList());
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Timestamp getCompetitionStartTimestamp() {
        return competitionStartTimestamp;
    }

    public void setCompetitionStartTimestamp(Timestamp competitionStartTimestamp) {
        this.competitionStartTimestamp = competitionStartTimestamp;
    }

    public Integer getMinTeams() {
        return minTeams;
    }

    public void setMinTeams(Integer minTeams) {
        this.minTeams = minTeams;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Timestamp getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(Timestamp registrationStart) {
        this.registrationStart = registrationStart;
    }

    public Timestamp getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(Timestamp registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    public String getSetOfRules() {
        return setOfRules;
    }

    public void setSetOfRules(String setOfRules) {
        this.setOfRules = setOfRules;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Collection<CompetitionStatusDTO> getCompetitionStatus() {
        return competitionStatus;
    }

    public void setCompetitionStatus(Collection<CompetitionStatusDTO> competitionStatus) {
        this.competitionStatus = competitionStatus;
    }

    public Collection<CompetitionTeamDTO> getCompetitionTeams() {
        return competitionTeams;
    }

    public void setCompetitionTeams(Collection<CompetitionTeamDTO> competitionTeams) {
        this.competitionTeams = competitionTeams;
    }

    public Collection<CompetitionAdminDTO> getCompetitionAdmins() {
        return competitionAdmins;
    }

    public void setCompetitionAdmins(Collection<CompetitionAdminDTO> competitionAdmins) {
        this.competitionAdmins = competitionAdmins;
    }
}
