package galaxy.core.team;

import galaxy.core.Component;
import galaxy.core.Entity;

import java.util.HashMap;
import java.util.Map;

public class Diplomacy implements Component {

	private final Map<TeamRef, DiplomaticStatus> status = new HashMap<>();

	public DiplomaticStatus statusWith(Entity otherTeam) {
		return status.computeIfAbsent(otherTeam.prop(TeamRef.class), k -> DiplomaticStatus.WAR);
	}

	public void declare(DiplomaticStatus status, Entity otherTeam) {
		this.status.put(otherTeam.prop(TeamRef.class), status);
	}

	public boolean isAtWarWith(Entity other) {
		return statusWith(other) == DiplomaticStatus.WAR;
	}

}
