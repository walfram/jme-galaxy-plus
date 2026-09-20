package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Race;
import galaxy.planet.Coordinates;
import galaxy.planet.PlanetId;
import galaxy.race.RaceId;
import galaxy.ship.ShipGroup;
import galaxy.ship.TechLevels;
import galaxy.ship.state.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonShipGroups {

	private final JsonNode source;
	private final Races races;
	private final Planets planets;

	public JsonShipGroups(JsonNode source, Races races, Planets planets) {
		this.source = source;
		this.races = races;
		this.planets = planets;
	}

	public Map<ShipGroup, ShipGroupState> parse() {
		Map<ShipGroup, ShipGroupState> result = new LinkedHashMap<>();
		this.source.valueStream().forEach(json -> {
			ShipGroup group = parseGroup(json);
			ShipGroupState state = parseState(json, group);
			result.put(group, state);
		});
		return result;
	}

	private ShipGroup parseGroup(JsonNode json) {
		Race owner = races.raceById(json.get("owner").asText());
		return new ShipGroup(
				owner,
				owner.shipType(json.get("type").asText()),
				json.get("size").asInt(),
				new TechLevels(json.get("tech"))
		);
	}

	private ShipGroupState parseState(JsonNode json, ShipGroup group) {
		String name = json.get("state").asText();
		return switch (name) {
			case "ORBIT" -> new InOrbit(group, planet(json, "planetId"));
			case "LAUNCHED" -> new Launched(group, planet(json, "planetId"), planet(json, "destinationId"));
			case "HYPERSPACE" ->
					new InHyperspace(group, planet(json, "planetId"), planet(json, "destinationId"), new Coordinates(json.get("coordinates")));
			case "UPGRADE" -> new InUpgrade(group, planet(json, "planetId"));
			case "TRANSFER" -> new InTransfer(group, planet(json, "planetId"),
					races.raceById(new RaceId(json.get("fromRaceId"))),
					races.raceById(new RaceId(json.get("toRaceId"))));
			default -> throw new IllegalArgumentException("Unknown ship group state: " + name);
		};
	}

	private Planet planet(JsonNode json, String field) {
		return planets.findById(new PlanetId(json.get(field).asText()));
	}

}
