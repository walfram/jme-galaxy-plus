package galaxy.ship;

import galaxy.CargoType;

public interface Cargo {
	CargoType type();

	double amountPerShip();
}
