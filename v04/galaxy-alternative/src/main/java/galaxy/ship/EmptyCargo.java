package galaxy.ship;

import galaxy.CargoType;

public class EmptyCargo implements Cargo {
	private final CargoSize cargoSize;

	public EmptyCargo(CargoSize cargoSize) {
		this.cargoSize = cargoSize;
	}

	@Override
	public CargoType type() {
		return null;
	}

	@Override
	public Double quantity() {
		return 0.0;
	}

	@Override
	public Double mass() {
		return quantity() / cargoSize.value();
	}
}
