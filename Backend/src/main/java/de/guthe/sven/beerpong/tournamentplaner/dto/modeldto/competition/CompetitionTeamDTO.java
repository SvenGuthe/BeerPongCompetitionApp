package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionTeamDTO extends ID {

    private CompetitionDTO competition;

    private String competitionTeamName;

    private Timestamp creationTime;

    private Collection<CompetitionPlayerDTO> competitionPlayer;

    private Collection<BillingStatusDTO> billingStatus;

    private Collection<RegistrationStatusDTO> registrationStatus;

    public CompetitionTeamDTO(Long id, CompetitionDTO competition, String competitionTeamName, Timestamp creationTime, Collection<CompetitionPlayerDTO> competitionPlayer, Collection<BillingStatusDTO> billingStatus, Collection<RegistrationStatusDTO> registrationStatus) {
        super(id);
        this.competition = competition;
        this.competitionTeamName = competitionTeamName;
        this.creationTime = creationTime;
        this.competitionPlayer = competitionPlayer;
        this.billingStatus = billingStatus;
        this.registrationStatus = registrationStatus;
    }

    public CompetitionTeamDTO(CompetitionTeam competitionTeam) {
        super(competitionTeam.getId());
        this.competition = new CompetitionDTO(competitionTeam.getCompetition());
        this.competitionTeamName = competitionTeam.getCompetitionTeamName();
        this.creationTime = competitionTeam.getCreationTime();
        this.competitionPlayer = competitionTeam.getCompetitionPlayers().stream().map(CompetitionPlayerDTO::new).collect(Collectors.toList());
        this.billingStatus = competitionTeam.getBillingStatusHistories().stream().map(BillingStatusDTO::new).collect(Collectors.toList());
        this.registrationStatus = competitionTeam.getRegistrationStatusHistories().stream().map(RegistrationStatusDTO::new).collect(Collectors.toList());
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDTO competition) {
        this.competition = competition;
    }

    public String getCompetitionTeamName() {
        return competitionTeamName;
    }

    public void setCompetitionTeamName(String competitionTeamName) {
        this.competitionTeamName = competitionTeamName;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Collection<CompetitionPlayerDTO> getCompetitionPlayer() {
        return competitionPlayer;
    }

    public void setCompetitionPlayer(Collection<CompetitionPlayerDTO> competitionPlayer) {
        this.competitionPlayer = competitionPlayer;
    }

    public Collection<BillingStatusDTO> getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(Collection<BillingStatusDTO> billingStatus) {
        this.billingStatus = billingStatus;
    }

    public Collection<RegistrationStatusDTO> getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Collection<RegistrationStatusDTO> registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
