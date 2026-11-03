package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;

import java.util.*;

public final class Attackers {

	private final List<BattleGroup> groups = new ArrayList<>();

//	@SafeVarargs
//	public Attackers(Collection<BattleGroup>... groups) {
//		for (Collection<BattleGroup> group : groups) {
//			this.groups.addAll(group);
//		}
//	}

	public Attackers(Map<Race, Collection<BattleGroup>> fleets) {
		this.groups.addAll(
				fleets.values().stream().flatMap(Collection::stream).toList()
		);
	}

	public Attackers(List<BattleGroup> groups) {
		this.groups.addAll(groups);
	}

	public List<BattleGroup> asList() {
		return groups.stream()
				.filter(group -> group.attackValue() > 0)
				.toList();
	}
}
