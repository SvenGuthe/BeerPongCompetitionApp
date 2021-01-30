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

public class TeamPermissions {

	public static Map<Sid, List<Permission>> initialTeamPermissions;

	static {
		initialTeamPermissions = new HashMap<>();
		initialTeamPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_PLAYER.toString()),
				Arrays.asList(CustomPermission.READ));
		initialTeamPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_MODERATOR.toString()), Arrays.asList(
				CustomPermission.READ, CustomPermission.WRITE, CustomPermission.CREATE, CustomPermission.DELETE));
		initialTeamPermissions.put(new GrantedAuthoritySid(SecurityRole.ROLE_ADMINISTRATOR.toString()),
				Arrays.asList(CustomPermission.READ, CustomPermission.WRITE, CustomPermission.CREATE,
						CustomPermission.ADMINISTRATION, CustomPermission.DELETE));
	}

	public static List<Permission> ownerPermissions = Arrays.asList(CustomPermission.READ, CustomPermission.WRITE,
			CustomPermission.CREATE);

}
