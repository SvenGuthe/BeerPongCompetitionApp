package de.guthe.sven.beerpong.tournamentplaner.datatype.team;

import de.guthe.sven.beerpong.tournamentplaner.configuration.CustomPermission;
import de.guthe.sven.beerpong.tournamentplaner.datatype.SecurityRole;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamInvitationLinkPermissions {

	public static Map<Sid, List<Permission>> initialTeamInvitationLinkPermissions;

	static {
		initialTeamInvitationLinkPermissions = new HashMap<>();
		initialTeamInvitationLinkPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_MODERATOR.toString()),
				Arrays.asList(CustomPermission.GET_TEAM_INVITATION_LINK, CustomPermission.UPDATE_TEAM_INVITATION_LINK,
						CustomPermission.DELETE_TEAM_INVITATION_LINK));
		initialTeamInvitationLinkPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_ADMINISTRATOR.toString()),
				Arrays.asList(CustomPermission.GET_TEAM_INVITATION_LINK, CustomPermission.UPDATE_TEAM_INVITATION_LINK,
						CustomPermission.DELETE_TEAM_INVITATION_LINK));
	}

	public static List<Permission> ownerPermissions = Arrays.asList(CustomPermission.GET_TEAM_INVITATION_LINK,
			CustomPermission.UPDATE_TEAM_INVITATION_LINK, CustomPermission.DELETE_TEAM_INVITATION_LINK);

}
