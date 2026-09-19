package galaxy.ship;

import galaxy.Transportable;

public interface CargoHold {
	double cargoMass();

	CargoCapacity cargoCapacity();

	void load(CargoLoad cargoLoad);

	Transportable cargo();
}
