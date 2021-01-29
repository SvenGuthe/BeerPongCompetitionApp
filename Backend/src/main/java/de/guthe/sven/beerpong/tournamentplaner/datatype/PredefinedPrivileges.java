package de.guthe.sven.beerpong.tournamentplaner.datatype;

import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class PredefinedPrivileges {

	public static Map<SecurityRole, List<SecurityPrivilege>> privileges;

	public static List<SecurityPrivilege> playerPrivileges = Arrays.asList(SecurityPrivilege.WRITE_TESTOBJECT_PRIVILEGE,
			SecurityPrivilege.READ_TESTOBJECT_PRIVILEGE, SecurityPrivilege.READ_TEAM_PRIVILEGE,
			SecurityPrivilege.WRITE_TEAM_PRIVILEGE);

	public static List<SecurityPrivilege> moderatorPrivileges = ListUtils.union(playerPrivileges,
			Arrays.asList(SecurityPrivilege.READ_ACL_PRIVILEGE, SecurityPrivilege.READ_AUTHENTICATION_PRIVILEGE));

	public static List<SecurityPrivilege> administratorPrivileges = Arrays.asList(SecurityPrivilege.values().clone());

	static {
		privileges = new HashMap<>();
		privileges.put(SecurityRole.ROLE_PLAYER, playerPrivileges);
		privileges.put(SecurityRole.ROLE_MODERATOR, moderatorPrivileges);
		privileges.put(SecurityRole.ROLE_ADMINISTRATOR, administratorPrivileges);
	}

}
