package de.guthe.sven.beerpong.tournamentplaner.datatype.team;

import de.guthe.sven.beerpong.tournamentplaner.configuration.CustomPermission;
import de.guthe.sven.beerpong.tournamentplaner.datatype.user.SecurityRole;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import java.util.*;

public class TeamPermissions {

	public static Map<Sid, List<Permission>> initialTeamPermissions;

	static {
		initialTeamPermissions = new HashMap<>();
		initialTeamPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_PLAYER.toString()),
				Collections.singletonList(CustomPermission.GET_TEAM));
		initialTeamPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_MODERATOR.toString()),
				Arrays.asList(CustomPermission.GET_TEAM, CustomPermission.UPDATE_TEAM, CustomPermission.DELETE_TEAM));
		initialTeamPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_ADMINISTRATOR.toString()),
				Arrays.asList(CustomPermission.GET_TEAM, CustomPermission.UPDATE_TEAM, CustomPermission.DELETE_TEAM));
	}

	public static List<Permission> ownerPermissions = Arrays.asList(CustomPermission.GET_TEAM,
			CustomPermission.UPDATE_TEAM, CustomPermission.DELETE_TEAM);

}
