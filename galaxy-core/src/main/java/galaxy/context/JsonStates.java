package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.ShipGroup;
import galaxy.ship.state.ShipGroupState;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class JsonStates implements Supplier<Map<ShipGroup, ShipGroupState>> {

	private final JsonNode source;
	private final Races races;
	private final Planets planets;

	public JsonStates(JsonNode source, Races races, Planets planets) {
		this.source = source;
		this.races = races;
		this.planets = planets;
	}

	@Override
	public Map<ShipGroup, ShipGroupState> get() {
		Map<ShipGroup, ShipGroupState> result = new LinkedHashMap<>();

		this.source.valueStream().forEach(json -> {
			ShipGroup group = new JsonShipGroup(json, this.races, this.planets).get();
			result.put(group, new JsonState(json, group, this.races, this.planets).get());
		});

		return result;
	}

}
