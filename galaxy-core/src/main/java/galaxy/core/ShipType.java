package galaxy.core;

import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;

public record ShipType(double engines, int guns, double caliber, double shields, double cargo, String name) {
	public ShipType(Engines engines, Weapons weapons, Shields shields, Cargo cargo, String name) {
		this(
				engines.value(),
				weapons.guns(),
				weapons.caliber(),
				shields.value(),
				cargo.value(),
				name
		);
	}
}
