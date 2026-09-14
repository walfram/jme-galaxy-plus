package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.race.RaceId;
import galaxy.ship.ShipType;

public final class Race {
	private final RaceId raceId;
	private final TechLevels techLevels;
	private final ShipTypes shipTypes;

	public Race(RaceId raceId, TechLevels techLevels, ShipTypes shipTypes) {
		this.raceId = raceId;
		this.techLevels = techLevels;
		this.shipTypes = shipTypes;
	}

	public Race(String raceId, JsonNode src) {
		this(
				new RaceId(raceId),
				new TechLevels(src.get("techLevels")),
				new ShipTypes(src.get("shipTypes"))
		);
	}

	public RaceId raceId() {
		return raceId;
	}

	public ShipType shipType(String shipTypeName) {
		return shipTypes.findByName(shipTypeName);
	}
}
