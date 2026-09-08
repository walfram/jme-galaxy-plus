package galaxy;

import galaxy.ship.CargoHold;
import galaxy.ship.Engines;
import galaxy.ship.Shields;
import galaxy.ship.Weapons;

public record ShipType(String name, Engines engines, Weapons weapons, Shields shields, CargoHold cargoHold) {
	double mass() {
		return 0;
	}

	double speed(double engineTech, double effectiveCargoWeight) {
		return 0;
	}

	double effectiveCargoWeight() {
		return 0;
	}
}
