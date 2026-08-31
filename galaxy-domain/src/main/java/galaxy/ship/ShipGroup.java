package galaxy.ship;

import galaxy.CargoType;
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

	private CargoType cargoType;
	private double cargoWeight;

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

	public void upgradeTechLevels(TechLevels techLevels) {

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

	public void loadCargo(CargoType cargoType, double weight) {
		double maxWeight = size * shipType.effectiveCargoWeight();
		if (weight > maxWeight) {
			throw new IllegalArgumentException("Cargo weight exceeds max weight");
		}

		this.cargoType = cargoType;
		this.cargoWeight = weight;
	}

	public double cargoWeight() {
		return cargoWeight;
	}

	public double speed() {
		return shipType.speed(techLevels.engines(), cargoWeight / size);
	}

	public double maxCargoWeight() {
		return shipType.effectiveCargoWeight();
	}

	public double maxFlightDistance() {
		return 40.0 * techLevels().engines();
	}
}
