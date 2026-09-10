package galaxy.ship;

import galaxy.CargoType;

public interface Cargo {
	CargoType type();

	Double quantity();

	Double mass();
}
