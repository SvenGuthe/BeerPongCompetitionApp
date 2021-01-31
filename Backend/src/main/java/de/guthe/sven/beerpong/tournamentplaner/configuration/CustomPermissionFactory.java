package de.guthe.sven.beerpong.tournamentplaner.configuration;

import org.springframework.security.acls.domain.DefaultPermissionFactory;

public class CustomPermissionFactory extends DefaultPermissionFactory {

	public CustomPermissionFactory() {
		super(CustomPermission.class);
	}

}
