package galaxy.core;

public class ShipGroup {

	private final Race.Id raceId;
	
	private final ShipType type;
	private final int size;
	private final TechLevels techLevels;

	public ShipGroup(Race race, ShipType type, int size) {
		this.raceId = race.id();
		this.type = type;
		this.size = size;
		this.techLevels = new TechLevels(race.techLevels());
	}

	public TechLevels techLevels() {
		return techLevels;
	}

	public ShipType shipType() {
		return type;
	}

	public int size() {
		return size;
	}
}
