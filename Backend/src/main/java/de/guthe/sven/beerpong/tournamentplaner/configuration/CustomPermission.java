package de.guthe.sven.beerpong.tournamentplaner.configuration;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.model.Permission;

public class CustomPermission extends AbstractPermission {

	public static final Permission GET_TEAM = new CustomPermission(1001);

	public static final Permission UPDATE_TEAM = new CustomPermission(1002);

	public static final Permission DELETE_TEAM = new CustomPermission(1003);

	public static final Permission GET_TEAM_INVITATION_LINK = new CustomPermission(1101);

	public static final Permission UPDATE_TEAM_INVITATION_LINK = new CustomPermission(1102);

	public static final Permission DELETE_TEAM_INVITATION_LINK = new CustomPermission(1103);

	protected CustomPermission(int mask) {
		super(mask);
	}

	protected CustomPermission(int mask, char code) {
		super(mask, code);
	}

}
