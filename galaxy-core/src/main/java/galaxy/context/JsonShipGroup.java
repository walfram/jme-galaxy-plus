package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Race;
import galaxy.planet.PlanetId;
import galaxy.ship.ShipGroup;
import galaxy.ship.TechLevels;

import java.util.function.Supplier;

public final class JsonShipGroup implements Supplier<ShipGroup> {
	private final JsonNode source;
	private final Races races;
	private final Planets planets;

	public JsonShipGroup(JsonNode source, Races races, Planets planets) {
		this.source = source;
		this.races = races;
		this.planets = planets;
	}

	@Override
	public ShipGroup get() {
		Race owner = races.raceById(source.get("owner").asText());
		return new ShipGroup(
				owner,
				planets.findById(new PlanetId(source.get("planetId").asText())),
				owner.shipType(source.get("type").asText()),
				source.get("size").asInt(),
				new TechLevels(source.get("tech"))
		);
	}
}
