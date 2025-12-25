package galaxy.domain.team;

import galaxy.domain.Component;
import galaxy.domain.Entity;

import java.util.HashMap;
import java.util.Map;

public class Diplomacy implements Component {

	private final Map<TeamRef, DiplomaticStatus> status = new HashMap<>();

	public DiplomaticStatus statusWith(Entity otherTeam) {
		return status.computeIfAbsent(otherTeam.prop(TeamRef.class), k -> DiplomaticStatus.WAR);
	}

}
