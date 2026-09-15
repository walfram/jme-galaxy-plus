package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.ShipGroup;

import java.util.List;

public class ShipGroups {
	private final List<ShipGroup> shipGroups;

	public ShipGroups(JsonNode src, Races races, Planets planets) {
		this(
				src.valueStream()
						.map(json -> new ShipGroup(json, races.raceById(json.get("owner").asText()), planets))
						.toList()
		);
	}

	public ShipGroups(List<ShipGroup> source) {
		this.shipGroups = source;
	}

	public int size() {
		return shipGroups.size();
	}
}
