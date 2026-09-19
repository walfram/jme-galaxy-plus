package galaxy.ship;

import galaxy.TechLevels;
import galaxy.Transportable;

import java.util.Optional;

public final class StandardCargoHold implements CargoHold {

	private final CargoCapacity cargoCapacity;

	private CargoLoad cargoLoad;

	public StandardCargoHold(int size, Cargo cargo, TechLevels techLevels) {
		this.cargoCapacity = new CargoCapacity(size, cargo, techLevels);
	}

	@Override
	public double cargoMass() {
		return Optional.ofNullable(cargoLoad).map(CargoLoad::quantity).orElse(0.0);
	}

	@Override
	public CargoCapacity cargoCapacity() {
		return cargoCapacity;
	}

	@Override
	public void load(CargoLoad cargoLoad) {
		if (cargoLoad.quantity() > cargoCapacity.value())
			throw new IllegalArgumentException("Cargo capacity exceeded, available %s, requested %s".formatted(cargoCapacity.value(), cargoLoad.quantity()));

		this.cargoLoad = cargoLoad;
	}

	@Override
	public Transportable cargo() {
		return cargoLoad.transportable();
	}

}
