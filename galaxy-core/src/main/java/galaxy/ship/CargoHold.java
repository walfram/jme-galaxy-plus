package galaxy.ship;

public interface CargoHold {
	double cargoMass();

	CargoCapacity cargoCapacity();

	void load(CargoLoad cargoLoad);

	CargoType cargoType();
}
