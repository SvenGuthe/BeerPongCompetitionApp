package de.guthe.sven.beerpong.tournamentplaner.dto.authentication.admin;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;

public class EnumDTO {

    private Long id;

    private String value;

    public EnumDTO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public EnumDTO() {
    }

    public EnumDTO(UserStatus userStatus) {
        this.id = userStatus.getUserStatusId();
        this.value = userStatus.getUserStatus().name();
    }

    public EnumDTO(Role role) {
        this.id = role.getRoleId();
        this.value = role.getName();
    }

    public EnumDTO(Privilege privilege) {
        this.id = privilege.getPrivilegeId();
        this.value = privilege.getName();
    }

    public EnumDTO(TeamStatus teamStatus) {
        this.id = teamStatus.getId();
        this.value = teamStatus.getTeamStatusDescription().name();
    }

    public EnumDTO(BillingStatus billingStatus) {
        this.id = billingStatus.getId();
        this.value = billingStatus.getBillingStatusDescription().name();
    }

    public EnumDTO(RegistrationStatus registrationStatus) {
        this.id = registrationStatus.getId();
        this.value = registrationStatus.getRegistrationStatusDescription().name();
    }

    public EnumDTO(CompetitionPlayerStatus competitionPlayerStatus) {
        this.id = competitionPlayerStatus.getId();
        this.value = competitionPlayerStatus.getCompetitionPlayerStatusDescription().toString();
    }

    public EnumDTO(ACLClass aclClass) {
        this.id = aclClass.getId();
        this.value = aclClass.getAclClass();
    }

    public EnumDTO(CompetitionStatus competitionStatus) {
        this.id = competitionStatus.getId();
        this.value = competitionStatus.getCompetitionStatusType().name();
    }

    public EnumDTO(CompetitionAdminStatus competitionAdminStatus) {
        this.id = competitionAdminStatus.getId();
        this.value = competitionAdminStatus.getCompetitionAdminStatusDescription().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
