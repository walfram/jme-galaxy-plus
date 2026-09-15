package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.race.RaceId;
import galaxy.ship.ShipType;

import java.util.HashMap;
import java.util.Map;

public final class Race {
	private final RaceId raceId;
	private final TechLevels techLevels;
	private final ShipTypes shipTypes;

	// TODO change to Set<RaceId> - if in set - then PEACE
	private final Map<RaceId, Diplomacy> diplomacy = new HashMap<>();
	private final Sciences sciences = new Sciences();

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

	public Race(String raceId) {
		this(new RaceId(raceId), new TechLevels(), new ShipTypes());
	}

	public RaceId raceId() {
		return raceId;
	}

	public ShipType shipType(String shipTypeName) {
		return shipTypes.findByName(shipTypeName);
	}

	public ShipTypes shipTypes() {
		return shipTypes;
	}

	public TechLevels techLevels() {
		return techLevels;
	}

	public Diplomacy statusWith(Race other) {
		return diplomacy.getOrDefault(other.raceId(), Diplomacy.WAR);
	}

	public void declarePeaceTo(Race other) {
		diplomacy.put(other.raceId(), Diplomacy.PEACE);
	}

	public void declareWarTo(Race other) {
		diplomacy.remove(other.raceId());
	}

	public Sciences sciences() {
		return sciences;
	}
}
