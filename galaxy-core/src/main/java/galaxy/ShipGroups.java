package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.race.RaceId;
import galaxy.ship.ShipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class ShipGroups {
	private final List<ShipGroup> shipGroups;

	public ShipGroups(JsonNode src, Races races, Planets planets) {
		this(
				src.valueStream()
						.map(json -> new ShipGroup(json, races.raceById(json.get("owner").asText()), planets))
						.toList()
		);
	}

	public ShipGroups(List<ShipGroup> source) {
		this.shipGroups = new ArrayList<>(source);
	}

	public int size() {
		return shipGroups.size();
	}

	public Optional<ShipGroup> findByOwnerAndName(RaceId raceId, String name) {
		return shipGroups.stream()
				.filter(group -> Objects.equals(group.race().raceId(), raceId))
				.filter(group -> Objects.equals(group.shipType().name(), name))
				.findFirst();
	}
}
