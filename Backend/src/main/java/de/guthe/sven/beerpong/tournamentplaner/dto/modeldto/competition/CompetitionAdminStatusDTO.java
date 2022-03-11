package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatusHistory;

import java.sql.Timestamp;

public class CompetitionAdminStatusDTO extends EnumDTO {

    private Long competitionAdminStatusId;

    private CompetitionAdminStatusType competitionAdminStatusDescription;

    private Timestamp creationTime;

    private Timestamp validFrom;

    private Timestamp validTo;

    public CompetitionAdminStatusDTO(Long id, CompetitionAdminStatusType competitionAdminStatusDescription, Timestamp creationTime, Timestamp validFrom, Timestamp validTo) {
        super(id, competitionAdminStatusDescription.name());
        this.competitionAdminStatusId = id;
        this.competitionAdminStatusDescription = competitionAdminStatusDescription;
        this.creationTime = creationTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public CompetitionAdminStatusDTO(CompetitionAdminStatus competitionAdminStatus, Timestamp validFrom, Timestamp validTo) {
        super(competitionAdminStatus.getId(), competitionAdminStatus.getCompetitionAdminStatusDescription().name());
        this.competitionAdminStatusId = competitionAdminStatus.getId();
        this.competitionAdminStatusDescription = competitionAdminStatus.getCompetitionAdminStatusDescription();
        this.creationTime = competitionAdminStatus.getCreationTime();
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public CompetitionAdminStatusDTO(CompetitionAdminStatus competitionAdminStatus) {
        super(competitionAdminStatus.getId(), competitionAdminStatus.getCompetitionAdminStatusDescription().name());
        this.competitionAdminStatusId = competitionAdminStatus.getId();
        this.competitionAdminStatusDescription = competitionAdminStatus.getCompetitionAdminStatusDescription();
        this.creationTime = competitionAdminStatus.getCreationTime();
        this.validFrom = null;
        this.validTo = null;
    }

    public CompetitionAdminStatusDTO(CompetitionAdminStatusHistory competitionAdminStatusHistory) {
        super(competitionAdminStatusHistory.getId(), competitionAdminStatusHistory.getCompetitionAdminStatus().getCompetitionAdminStatusDescription().name());
        this.competitionAdminStatusId = competitionAdminStatusHistory.getCompetitionAdminStatus().getId();
        this.competitionAdminStatusDescription = competitionAdminStatusHistory.getCompetitionAdminStatus().getCompetitionAdminStatusDescription();
        this.creationTime = competitionAdminStatusHistory.getCompetitionAdminStatus().getCreationTime();
        this.validFrom = competitionAdminStatusHistory.getValidFrom();
        this.validTo = competitionAdminStatusHistory.getValidTo();
    }

    public CompetitionAdminStatusType getCompetitionAdminStatusDescription() {
        return competitionAdminStatusDescription;
    }

    public void setCompetitionAdminStatusDescription(CompetitionAdminStatusType competitionAdminStatusDescription) {
        this.competitionAdminStatusDescription = competitionAdminStatusDescription;
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

    public Long getCompetitionAdminStatusId() {
        return competitionAdminStatusId;
    }

    public void setCompetitionAdminStatusId(Long competitionAdminStatusId) {
        this.competitionAdminStatusId = competitionAdminStatusId;
    }
}
