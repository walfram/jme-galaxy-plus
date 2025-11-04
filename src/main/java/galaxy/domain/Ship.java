package galaxy.domain;

import galaxy.domain.ship.Cargo;
import galaxy.domain.ship.Engines;
import galaxy.domain.ship.Shields;
import galaxy.domain.ship.Weapons;

public class Ship {

	private static final double BASE_SPEED = 20.0;
	private final Engines engines;
	private final Weapons weapons;
	private final Shields shields;
	private final Cargo cargo;

	public Ship(Engines engines, Weapons weapons, Shields shields, Cargo cargo) {
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargo = cargo;
	}

	public double speed() {
		return BASE_SPEED * engines.value() * engines.techLevel() / weight();
	}

	public double weight() {
		double weight = engines.weight() + weapons.weight() + shields.weight() + cargo.weight();

		double cargoTechLevel = cargo.techLevel();

		if (cargoTechLevel > 0) {
			weight += cargo.loadedQuantity() / cargoTechLevel;
		}

		return weight;
	}

}
