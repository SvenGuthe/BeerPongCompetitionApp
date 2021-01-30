package de.guthe.sven.beerpong.tournamentplaner.configuration;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.model.Permission;

public class CustomPermission extends AbstractPermission {

	public static final Permission READ = new CustomPermission(1, 'R');

	public static final Permission WRITE = new CustomPermission(2, 'W');

	public static final Permission CREATE = new CustomPermission(4, 'C');

	public static final Permission DELETE = new CustomPermission(8, 'D');

	public static final Permission ADMINISTRATION = new CustomPermission(16, 'A');

	protected CustomPermission(int mask) {
		super(mask);
	}

	protected CustomPermission(int mask, char code) {
		super(mask, code);
	}

}
