package galaxy.battle;

import galaxy.ship.ShipGroup;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BattleGroupsOf {

	private final Collection<ShipGroup> source;

	public BattleGroupsOf(Collection<ShipGroup> source) {
		this.source = source;
	}

	public Collection<BattleGroup> value() {
		return source.stream()
				.flatMap(
						group -> IntStream.range(0, group.groupSize()).mapToObj(idx -> new BattleGroup(group))
				).toList();
	}
}
