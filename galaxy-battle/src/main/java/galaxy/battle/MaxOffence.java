package galaxy.battle;

import java.util.Collection;
import java.util.Comparator;

public final class MaxOffence {
	private final Collection<BattleGroup> groups;

	public MaxOffence(Collection<BattleGroup> groups) {
		this.groups = groups;
	}

	public BattleGroup shipGroup() {
		return groups.stream()
				.max(Comparator.comparingDouble(BattleGroup::attackValue))
				.orElseThrow();
	}
}
