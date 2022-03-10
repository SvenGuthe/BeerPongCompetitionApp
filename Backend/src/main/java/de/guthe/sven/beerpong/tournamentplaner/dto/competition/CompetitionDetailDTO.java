package de.guthe.sven.beerpong.tournamentplaner.dto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.*;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionDetailDTO {

    static class TeamDTO {

        private Long id;

        private String teamName;

        public TeamDTO(Long id, String teamName) {
            this.id = id;
            this.teamName = teamName;
        }

        public TeamDTO(Team team) {
            this.id = team.getId();
            this.teamName = team.getTeamName();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }
    }

    static class BillingStatusHistoryDTO {

        private Long id;

        private Timestamp validFrom;

        private Timestamp validTo;

        private BillingStatusType billingStatusType;

        public BillingStatusHistoryDTO(Long id, Timestamp validFrom, Timestamp validTo, BillingStatusType billingStatusType) {
            this.id = id;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.billingStatusType = billingStatusType;
        }

        public BillingStatusHistoryDTO(BillingStatusHistory billingStatusHistory) {
            this.id = billingStatusHistory.getId();
            this.validFrom = billingStatusHistory.getValidFrom();
            this.validTo = billingStatusHistory.getValidTo();
            this.billingStatusType = billingStatusHistory.getBillingStatus().getBillingStatusDescription();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public BillingStatusType getBillingStatusType() {
            return billingStatusType;
        }

        public void setBillingStatusType(BillingStatusType billingStatusType) {
            this.billingStatusType = billingStatusType;
        }
    }

    static class RegistrationStatusHistoryDTO {

        private Long id;

        private Timestamp validFrom;

        private Timestamp validTo;

        private RegistrationStatusType registrationStatusType;

        public RegistrationStatusHistoryDTO(Long id, Timestamp validFrom, Timestamp validTo, RegistrationStatusType registrationStatusType) {
            this.id = id;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.registrationStatusType = registrationStatusType;
        }

        public RegistrationStatusHistoryDTO(RegistrationStatusHistory registrationStatusHistory) {
            this.id = registrationStatusHistory.getId();
            this.validFrom = registrationStatusHistory.getValidFrom();
            this.validTo = registrationStatusHistory.getValidTo();
            this.registrationStatusType = registrationStatusHistory.getRegistrationStatus().getRegistrationStatusDescription();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public RegistrationStatusType getRegistrationStatusType() {
            return registrationStatusType;
        }

        public void setRegistrationStatusType(RegistrationStatusType registrationStatusType) {
            this.registrationStatusType = registrationStatusType;
        }
    }

    static class CompetitionPlayerDTO {

        private Long id;

        private Long userId;

        private String gamerTag;

        private CompetitionPlayerStatusType competitionPlayerStatusType;

        public CompetitionPlayerDTO(Long id, Long userId, String gamerTag, CompetitionPlayerStatusType competitionPlayerStatusType) {
            this.id = id;
            this.userId = userId;
            this.gamerTag = gamerTag;
            this.competitionPlayerStatusType = competitionPlayerStatusType;
        }

        public CompetitionPlayerDTO(CompetitionPlayer competitionPlayer) {
            this.userId = competitionPlayer.getUser().getId();
            this.id = competitionPlayer.getId();
            this.gamerTag = competitionPlayer.getUser().getGamerTag();
            this.competitionPlayerStatusType = competitionPlayer.getCompetitionPlayerStatus().getCompetitionPlayerStatusDescription();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getGamerTag() {
            return gamerTag;
        }

        public void setGamerTag(String gamerTag) {
            this.gamerTag = gamerTag;
        }

        public CompetitionPlayerStatusType getCompetitionPlayerStatusType() {
            return competitionPlayerStatusType;
        }

        public void setCompetitionPlayerStatusType(CompetitionPlayerStatusType competitionPlayerStatusType) {
            this.competitionPlayerStatusType = competitionPlayerStatusType;
        }
    }

    static class CompetitionTeamDTO {

        private Long id;

        private TeamDTO team;

        private String competitionTeamName;

        private Timestamp creationTime;

        private Collection<BillingStatusHistoryDTO> billingStatusHistories;

        private Collection<RegistrationStatusHistoryDTO> registrationStatusHistories;

        private Collection<CompetitionPlayerDTO> competitionPlayers;

        public CompetitionTeamDTO(Long id, TeamDTO team, String competitionTeamName, Timestamp creationTime, Collection<BillingStatusHistoryDTO> billingStatusHistories, Collection<RegistrationStatusHistoryDTO> registrationStatusHistories, Collection<CompetitionPlayerDTO> competitionPlayers) {
            this.id = id;
            this.team = team;
            this.competitionTeamName = competitionTeamName;
            this.creationTime = creationTime;
            this.billingStatusHistories = billingStatusHistories;
            this.registrationStatusHistories = registrationStatusHistories;
            this.competitionPlayers = competitionPlayers;
        }

        public CompetitionTeamDTO(CompetitionTeam competitionTeam) {
            this.id = competitionTeam.getId();
            this.competitionTeamName = competitionTeam.getCompetitionTeamName();
            this.creationTime = competitionTeam.getCreationTime();
            this.team = new TeamDTO(competitionTeam.getTeam());
            this.billingStatusHistories = competitionTeam.getBillingStatusHistories().stream().map(BillingStatusHistoryDTO::new).collect(Collectors.toList());
            this.registrationStatusHistories = competitionTeam.getRegistrationStatusHistories().stream().map(RegistrationStatusHistoryDTO::new).collect(Collectors.toList());
            this.competitionPlayers = competitionTeam.getCompetitionPlayers().stream().map(CompetitionPlayerDTO::new).collect(Collectors.toList());
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public TeamDTO getTeam() {
            return team;
        }

        public void setTeam(TeamDTO team) {
            this.team = team;
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

        public Collection<BillingStatusHistoryDTO> getBillingStatusHistories() {
            return billingStatusHistories;
        }

        public void setBillingStatusHistories(Collection<BillingStatusHistoryDTO> billingStatusHistories) {
            this.billingStatusHistories = billingStatusHistories;
        }

        public Collection<RegistrationStatusHistoryDTO> getRegistrationStatusHistories() {
            return registrationStatusHistories;
        }

        public void setRegistrationStatusHistories(Collection<RegistrationStatusHistoryDTO> registrationStatusHistories) {
            this.registrationStatusHistories = registrationStatusHistories;
        }

        public Collection<CompetitionPlayerDTO> getCompetitionPlayers() {
            return competitionPlayers;
        }

        public void setCompetitionPlayers(Collection<CompetitionPlayerDTO> competitionPlayers) {
            this.competitionPlayers = competitionPlayers;
        }
    }

    static class CompetitionAdminDTO {

        private Long id;

        private Long userId;

        private String gamerTag;

        private Collection<CompetitionAdminStatusHistoryDTO> competitionAdminStatusHistories;

        public CompetitionAdminDTO(Long id, Long userId, String gamerTag, Collection<CompetitionAdminStatusHistoryDTO> competitionAdminStatusHistories) {
            this.id = id;
            this.userId = userId;
            this.gamerTag = gamerTag;
            this.competitionAdminStatusHistories = competitionAdminStatusHistories;
        }

        public CompetitionAdminDTO(CompetitionAdmin competitionAdmin) {
            this.id = competitionAdmin.getId();
            this.userId = competitionAdmin.getUser().getId();
            this.gamerTag = competitionAdmin.getUser().getGamerTag();
            this.competitionAdminStatusHistories = competitionAdmin.getCompetitionAdminStatusHistories().stream().map(CompetitionAdminStatusHistoryDTO::new).collect(Collectors.toList());
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getGamerTag() {
            return gamerTag;
        }

        public void setGamerTag(String gamerTag) {
            this.gamerTag = gamerTag;
        }

        public Collection<CompetitionAdminStatusHistoryDTO> getCompetitionAdminStatusHistories() {
            return competitionAdminStatusHistories;
        }

        public void setCompetitionAdminStatusHistories(Collection<CompetitionAdminStatusHistoryDTO> competitionAdminStatusHistories) {
            this.competitionAdminStatusHistories = competitionAdminStatusHistories;
        }
    }

    static class CompetitionAdminStatusHistoryDTO {

        private Long id;

        private Timestamp validFrom;

        private Timestamp validTo;

        private CompetitionAdminStatusType competitionAdminStatusType;

        public CompetitionAdminStatusHistoryDTO(Long id, Timestamp validFrom, Timestamp validTo, CompetitionAdminStatusType competitionAdminStatusType) {
            this.id = id;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.competitionAdminStatusType = competitionAdminStatusType;
        }

        public CompetitionAdminStatusHistoryDTO(CompetitionAdminStatusHistory competitionAdminStatusHistory) {
            this.id = competitionAdminStatusHistory.getId();
            this.validFrom = competitionAdminStatusHistory.getValidFrom();
            this.validTo = competitionAdminStatusHistory.getValidTo();
            this.competitionAdminStatusType = competitionAdminStatusHistory.getCompetitionAdminStatus().getCompetitionAdminStatusDescription();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public CompetitionAdminStatusType getCompetitionAdminStatusType() {
            return competitionAdminStatusType;
        }

        public void setCompetitionAdminStatusType(CompetitionAdminStatusType competitionAdminStatusType) {
            this.competitionAdminStatusType = competitionAdminStatusType;
        }
    }

    static class CompetitionStatusHistoryDTO {

        private Long id;

        private Timestamp validFrom;

        private Timestamp validTo;

        private CompetitionStatusType competitionStatusType;

        public CompetitionStatusHistoryDTO(Long id, Timestamp validFrom, Timestamp validTo, CompetitionStatusType competitionStatusType) {
            this.id = id;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.competitionStatusType = competitionStatusType;
        }

        public CompetitionStatusHistoryDTO(CompetitionStatusHistory competitionStatusHistory) {
            this.id = competitionStatusHistory.getId();
            this.validFrom = competitionStatusHistory.getValidFrom();
            this.validTo = competitionStatusHistory.getValidTo();
            this.competitionStatusType = competitionStatusHistory.getCompetitionStatus().getCompetitionStatusType();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public CompetitionStatusType getCompetitionStatusType() {
            return competitionStatusType;
        }

        public void setCompetitionStatusType(CompetitionStatusType competitionStatusType) {
            this.competitionStatusType = competitionStatusType;
        }
    }

    private Long id;

    private String competitionName;

    private Timestamp competitionStartTimestamp;

    private Integer minTeams;

    private Integer maxTeams;

    private Double fee;

    private Timestamp registrationStart;

    private Timestamp registrationEnd;

    private String setOfRules;

    private Timestamp creationTime;

    private Collection<CompetitionTeamDTO> competitionTeams;

    private Collection<CompetitionAdminDTO> competitionAdmins;

    private Collection<CompetitionStatusHistoryDTO> competitionStatusHistories;

    public CompetitionDetailDTO(Long id, String competitionName, Timestamp competitionStartTimestamp, Integer minTeams, Integer maxTeams, Double fee, Timestamp registrationStart, Timestamp registrationEnd, String setOfRules, Timestamp creationTime, Collection<CompetitionTeamDTO> competitionTeams, Collection<CompetitionAdminDTO> competitionAdmins, Collection<CompetitionStatusHistoryDTO> competitionStatusHistories) {
        this.id = id;
        this.competitionName = competitionName;
        this.competitionStartTimestamp = competitionStartTimestamp;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
        this.fee = fee;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.setOfRules = setOfRules;
        this.creationTime = creationTime;
        this.competitionTeams = competitionTeams;
        this.competitionAdmins = competitionAdmins;
        this.competitionStatusHistories = competitionStatusHistories;
    }

    public CompetitionDetailDTO(Competition competition) {
        this.id = competition.getId();
        this.competitionName = competition.getCompetitionName();
        this.competitionStartTimestamp = competition.getCompetitionStartTimestamp();
        this.minTeams = competition.getMinTeams();
        this.maxTeams = competition.getMaxTeams();
        this.fee = competition.getFee();
        this.registrationStart = competition.getRegistrationStart();
        this.registrationEnd = competition.getRegistrationEnd();
        this.setOfRules = competition.getSetOfRules();
        this.creationTime = competition.getCreationTime();
        this.competitionTeams = competition.getCompetitionTeams().stream().map(CompetitionTeamDTO::new).collect(Collectors.toList());
        this.competitionAdmins = competition.getCompetitionAdmins().stream().map(CompetitionAdminDTO::new).collect(Collectors.toList());
        this.competitionStatusHistories = competition.getCompetitionStatusHistories().stream().map(CompetitionStatusHistoryDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<CompetitionStatusHistoryDTO> getCompetitionStatusHistories() {
        return competitionStatusHistories;
    }

    public void setCompetitionStatusHistories(Collection<CompetitionStatusHistoryDTO> competitionStatusHistories) {
        this.competitionStatusHistories = competitionStatusHistories;
    }
}
