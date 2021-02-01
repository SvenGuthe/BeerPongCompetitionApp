package de.guthe.sven.beerpong.tournamentplaner.datatype.competition;

import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionPlayerPermissions {

	public static Map<Sid, List<Permission>> initialCompetitionPlayerPermissions;

	static {
		initialCompetitionPlayerPermissions = new HashMap<>();
	}

	public static List<Permission> ownerPermissions = new ArrayList<>();

}
