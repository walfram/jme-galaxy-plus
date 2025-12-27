package galaxy.domain.shared;

import galaxy.domain.Entity;
import galaxy.domain.ship.TechLevel;
import galaxy.domain.ship.Weapons;

import java.util.List;

public class CalculatedBombing {
	private final List<Entity> ships;

	public CalculatedBombing(List<Entity> ships) {
		this.ships = ships;
	}

	public double value() {
		return ships.stream()
				.mapToDouble(ship -> {
					int guns = ship.prop(Weapons.class).guns();
					double caliber = ship.prop(Weapons.class).caliber();
					double weaponsLevel = ship.prop(TechLevel.class).weapons();
					double n = 1 + 0.1 * Math.pow(caliber * weaponsLevel, 0.5);
					return n * caliber * weaponsLevel * guns;
				})
				.sum();
	}
}
