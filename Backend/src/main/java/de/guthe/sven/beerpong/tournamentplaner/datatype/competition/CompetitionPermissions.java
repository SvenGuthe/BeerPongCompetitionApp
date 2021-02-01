package de.guthe.sven.beerpong.tournamentplaner.datatype.competition;

import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionPermissions {

	public static Map<Sid, List<Permission>> initialCompetitionPermissions;

	static {
		initialCompetitionPermissions = new HashMap<>();
	}

	public static List<Permission> ownerPermissions = new ArrayList<>();

}
