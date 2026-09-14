package galaxy.ship;

import galaxy.TechLevels;

public class NoCargoHold implements CargoHold {
	@Override
	public double cargoMass() {
		return 0.0;
	}

	@Override
	public CargoCapacity cargoCapacity() {
		return new CargoCapacity(new Cargo(0.0), new TechLevels());
	}

	@Override
	public void load(CargoLoad cargoLoad) {
		throw new IllegalStateException("No cargo hold");
	}

	@Override
	public CargoType cargoType() {
		return null;
	}
}
