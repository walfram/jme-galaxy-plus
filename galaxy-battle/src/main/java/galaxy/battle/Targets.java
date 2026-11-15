package galaxy.battle;

import galaxy.Race;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class Targets {
	private final Map<Race, Collection<BattleGroup>> battleGroups;
	private final BattleGroup attacker;

	public Targets(Map<Race, Collection<BattleGroup>> battleGroups, BattleGroup attacker) {
		this.battleGroups = battleGroups;
		this.attacker = attacker;
	}

	public Collection<BattleGroup> pick() {
		return battleGroups.entrySet().stream()
				.filter(e -> !e.getKey().equals(attacker.owner()))
				.flatMap(e -> e.getValue().stream())
				.toList();
	}
}
