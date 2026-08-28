package galaxy.ship;

import galaxy.Id;
import galaxy.Race;
import galaxy.TechLevels;

import java.util.UUID;

public class ShipGroup {

	private final Id id;

	private final Race race;
	private final ShipType shipType;
	private final TechLevels techLevels;
	private final int size;

	public ShipGroup(Race race, ShipType shipType, TechLevels techLevels, int size) {
		this(
				new Id(UUID.randomUUID()),
				race,
				shipType,
				techLevels,
				size
		);
	}

	public ShipGroup(Id id, Race race, ShipType shipType, TechLevels techLevels, int size) {
		this.id = id;
		this.race = race;
		this.shipType = shipType;
		this.techLevels = new TechLevels(techLevels);
		this.size = size;
	}

	public Race race() {
		return race;
	}

	public void updateTechLevels(TechLevels techLevels) {

	}

	public ShipType shipType() {
		return shipType;
	}

	public TechLevels techLevels() {
		return techLevels;
	}

	public int size() {
		return size;
	}

	public Id id() {
		return id;
	}

	public double weight() {
		return size * shipType.mass();
	}
}
