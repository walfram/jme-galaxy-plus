package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class BattleGenerator {
	private final Map<Race, Collection<BattleGroup>> fleets;

	public BattleGenerator(Map<Race, Collection<ShipGroup>> fleets) {
		this.fleets = fleets.entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								entry -> entry.getValue().stream().map(BattleGroup::new).collect(Collectors.toList())
						)
				);
	}

	public Map<Race, Collection<ShipGroup>> generate() {
		return null;
	}
}
