package galaxy;

import galaxy.ship.*;
import org.jspecify.annotations.Nullable;

public final class ShipGroup {
	private final Race race;
	private final ShipType shipType;
	private final TechLevels techLevels;
	private final int size;

	private final CargoHold cargoHold;

	public ShipGroup(Race race, ShipType shipType, TechLevels techLevels, int size) {
		this(race, shipType, techLevels, size, new CargoLoad());
	}

	public ShipGroup(Race race, ShipType shipType, TechLevels techLevels, int size, CargoLoad cargoLoad) {
		this.race = race;
		this.shipType = shipType;
		this.techLevels = new TechLevels(techLevels);
		this.size = size;
		this.cargoHold = shipType.cargoHold(size, this.techLevels, cargoLoad);
	}

	public double mass() {
		double typeMass = shipType.mass();

		typeMass += cargoHold.cargoMass() / techLevels.cargo();

		return typeMass;
	}

	public double speed() {
		return 20.0 * techLevels.engines() * shipType.engines().size() / mass();
	}

	public double cargoCapacity() {
		return cargoHold.cargoCapacity().value();
	}

	public void load(CargoLoad cargoLoad) {
		this.cargoHold.load(cargoLoad);
	}

	public double cargoMass() {
		return cargoHold.cargoMass();
	}

	public CargoType cargoType() {
		return cargoHold.cargoType();
	}

	public void upgrade(TechLevels techLevels) {
		this.techLevels.upgrade(techLevels);
	}
}
