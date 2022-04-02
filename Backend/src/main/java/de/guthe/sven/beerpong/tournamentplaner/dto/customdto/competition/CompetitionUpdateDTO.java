package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CompetitionUpdateDTO extends ID {

    @NotNull(message = "competitionName in CompetitionUpdateDTO have to be set.")
    private String competitionName;

    private Timestamp competitionStartTimestamp;

    private Integer minTeams;

    private Integer maxTeams;

    private Double fee;

    private Timestamp registrationStart;

    private Timestamp registrationEnd;

    private String setOfRules;

    public CompetitionUpdateDTO(Long id, String competitionName, Timestamp competitionStartTimestamp, Integer minTeams, Integer maxTeams, Double fee, Timestamp registrationStart, Timestamp registrationEnd, String setOfRules) {
        super(id);
        this.competitionName = competitionName;
        this.competitionStartTimestamp = competitionStartTimestamp;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
        this.fee = fee;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.setOfRules = setOfRules;
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

}