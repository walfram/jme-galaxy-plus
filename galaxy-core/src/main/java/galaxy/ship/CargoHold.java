package galaxy.ship;

import galaxy.Effort;
import org.jspecify.annotations.Nullable;

public interface CargoHold {
	double cargoMass();

	CargoCapacity cargoCapacity();

	void load(CargoLoad cargoLoad);

	CargoType cargoType();
}
