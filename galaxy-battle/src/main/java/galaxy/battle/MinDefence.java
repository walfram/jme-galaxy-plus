package galaxy.battle;

import java.util.Collection;
import java.util.Comparator;

public final class MinDefence {
	private final Collection<BattleGroup> groups;

	public MinDefence(Collection<BattleGroup> groups) {
		this.groups = groups;
	}

	public BattleGroup shipGroup() {
		return groups.stream()
				.min(Comparator.comparingDouble(BattleGroup::defenceValue))
				.orElseThrow();
	}
}
