package de.guthe.sven.beerpong.tournamentplaner.dto;

import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.user.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.user.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.user.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;

import javax.validation.constraints.NotNull;

public class EnumDTO extends ID {

	@NotNull(message = "value in EnumDTO have to be set.")
	private String value;

	public EnumDTO(Long id, String value) {
		super(id);
		this.value = value;
	}

	public EnumDTO(Long id) {
		super(id);
	}

	public EnumDTO(UserStatus userStatus) {
		super(userStatus.getId());
		this.value = userStatus.getUserStatus().name();
	}

	public EnumDTO(Role role) {
		super(role.getId());
		this.value = role.getRole().name();
	}

	public EnumDTO(Privilege privilege) {
		super(privilege.getId());
		this.value = privilege.getPrivilege().name();
	}

	public EnumDTO(TeamStatus teamStatus) {
		super(teamStatus.getId());
		this.value = teamStatus.getTeamStatusDescription().name();
	}

	public EnumDTO(BillingStatus billingStatus) {
		super(billingStatus.getId());
		this.value = billingStatus.getBillingStatusDescription().name();
	}

	public EnumDTO(RegistrationStatus registrationStatus) {
		super(registrationStatus.getId());
		this.value = registrationStatus.getRegistrationStatusDescription().name();
	}

	public EnumDTO(CompetitionPlayerStatus competitionPlayerStatus) {
		super(competitionPlayerStatus.getId());
		this.value = competitionPlayerStatus.getCompetitionPlayerStatusDescription().toString();
	}

	public EnumDTO(ACLClass aclClass) {
		super(aclClass.getId());
		this.value = aclClass.getAclClass();
	}

	public EnumDTO(CompetitionStatus competitionStatus) {
		super(competitionStatus.getId());
		this.value = competitionStatus.getCompetitionStatusType().name();
	}

	public EnumDTO(TeamCompositionStatus teamCompositionStatus) {
		super(teamCompositionStatus.getId());
		this.value = teamCompositionStatus.getTeamCompositionStatusType().name();
	}

	public EnumDTO(CompetitionAdminStatus competitionAdminStatus) {
		super(competitionAdminStatus.getId());
		this.value = competitionAdminStatus.getCompetitionAdminStatusDescription().name();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
