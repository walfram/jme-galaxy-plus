package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.planet.Coordinates;
import galaxy.planet.PlanetId;
import galaxy.race.RaceId;
import galaxy.ship.ShipGroup;
import galaxy.ship.state.*;

import java.util.function.Supplier;

public final class JsonState implements Supplier<ShipGroupState> {

	private final JsonNode source;
	private final ShipGroup group;
	private final Races races;
	private final Planets planets;

	public JsonState(JsonNode source, ShipGroup group, Races races, Planets planets) {
		this.source = source;
		this.group = group;
		this.races = races;
		this.planets = planets;
	}

	@Override
	public ShipGroupState get() {
		String name = source.get("state").asText();
		return switch (name) {
			case "ORBIT" -> new InOrbit(group, planet("planetId"));

			case "LAUNCHED" -> new Launched(group,
					planet("planetId"), planet("destinationId"));

			case "HYPERSPACE" -> new InHyperspace(group,
					planet("planetId"), planet("destinationId"),
					new Coordinates(source.get("coordinates")));

			case "UPGRADE" -> new InUpgrade(group, planet("planetId"));

			case "TRANSFER" -> new InTransfer(group,
					planet("planetId"),
					races.raceById(new RaceId(source.get("fromRaceId"))),
					races.raceById(new RaceId(source.get("toRaceId"))));

			default -> throw new IllegalArgumentException("Unknown ship group state: " + name);
		};
	}

	private Planet planet(String field) {
		return planets.findById(new PlanetId(source.get(field)));
	}

}
