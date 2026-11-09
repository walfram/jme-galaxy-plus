package galaxy.battle;

import galaxy.Race;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public final class MinDefence {
	private final Collection<BattleGroup> groups;

	public MinDefence(Collection<BattleGroup> groups) {
		this.groups = groups;
	}

	public MinDefence(Map<Race, Collection<BattleGroup>> battleGroups) {
		this(battleGroups.values().stream().flatMap(Collection::stream).toList());
	}

	public Optional<BattleGroup> shipGroup() {
		return groups.stream()
				.min(Comparator.comparingDouble(BattleGroup::defenceValue));
	}
}
