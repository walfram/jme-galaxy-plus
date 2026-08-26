package galaxy.core;

import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;

public record ShipType(Engines engines, Weapons weapons, Shields shields, Cargo cargo, String name) {

	private static final double ONE_THIRD = 1.0 / 3.0;
	private static final double K = Math.pow(30.0, ONE_THIRD);

	public double mass() {
		return engines.mass() + weapons.mass() + shields.mass() + cargo.mass();
	}

	public double baseSpeed() {
		// 20.0 * engine's tech level, for ShipType assumed == 1.0
		// also mass() should be mass() + carried cargo mass, but for type that is 0.0
		return 20.0 * (engines.mass() / mass());
	}

	public double effectiveAttack() {
		return weapons().caliber(); // should be multiplied by weapons tech level for ship
	}

	public double effectiveDefence() {
		double n = shields().mass(); // * shields tech level for ship
		double d = Math.pow(mass(), ONE_THIRD); // mass() + carried cargo mass for ship
		return (n / d) * K;
	}
}
