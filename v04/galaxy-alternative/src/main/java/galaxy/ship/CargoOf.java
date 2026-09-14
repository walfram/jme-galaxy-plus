package galaxy.ship;

import galaxy.CargoType;

public class CargoOf implements Cargo {
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
		return 0.0;
	}

}
