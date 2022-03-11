package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLinkHistory;

import java.sql.Timestamp;

public class TeamInvitationLinkDTO extends EnumDTO {

    private String teamInvitationLink;

    private Timestamp creationTime;

    private Timestamp validFrom;

    private Timestamp validTo;

    public TeamInvitationLinkDTO(Long id, String teamInvitationLink, Timestamp creationTime, Timestamp validFrom, Timestamp validTo) {
        super(id, teamInvitationLink);
        this.teamInvitationLink = teamInvitationLink;
        this.creationTime = creationTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public TeamInvitationLinkDTO(TeamInvitationLink teamInvitationLink, Timestamp validFrom, Timestamp validTo) {
        super(teamInvitationLink.getId(), teamInvitationLink.getTeamInvitationLink());
        this.teamInvitationLink = teamInvitationLink.getTeamInvitationLink();
        this.creationTime = teamInvitationLink.getCreationTime();
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public TeamInvitationLinkDTO(TeamInvitationLink teamInvitationLink) {
        super(teamInvitationLink.getId(), teamInvitationLink.getTeamInvitationLink());
        this.teamInvitationLink = teamInvitationLink.getTeamInvitationLink();
        this.creationTime = teamInvitationLink.getCreationTime();
        this.validFrom = null;
        this.validTo = null;
    }

    public TeamInvitationLinkDTO(TeamInvitationLinkHistory teamInvitationLinkHistory) {
        super(teamInvitationLinkHistory.getTeamInvitationLink().getId(), teamInvitationLinkHistory.getTeamInvitationLink().getTeamInvitationLink());
        this.teamInvitationLink = teamInvitationLinkHistory.getTeamInvitationLink().getTeamInvitationLink();
        this.creationTime = teamInvitationLinkHistory.getTeamInvitationLink().getCreationTime();
        this.validFrom = teamInvitationLinkHistory.getValidFrom();
        this.validTo = teamInvitationLinkHistory.getValidTo();
    }

    public String getTeamInvitationLink() {
        return teamInvitationLink;
    }

    public void setTeamInvitationLink(String teamInvitationLink) {
        this.teamInvitationLink = teamInvitationLink;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    public Timestamp getValidTo() {
        return validTo;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }
}
