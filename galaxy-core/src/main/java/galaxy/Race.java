package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.race.RaceId;

public final class Race {
	private final RaceId raceId;
	private final TechLevels techLevels;
	private final ShipTypes shipTypes;

	public Race(JsonNode src, String raceId) {
		this(
				new RaceId(raceId),
				new TechLevels(src.get("techLevels")),
				new ShipTypes(src.get("shipTypes"))
		);
	}

	public Race(RaceId raceId, TechLevels techLevels, ShipTypes shipTypes) {
		this.raceId = raceId;
		this.techLevels = techLevels;
		this.shipTypes = shipTypes;
	}
}
