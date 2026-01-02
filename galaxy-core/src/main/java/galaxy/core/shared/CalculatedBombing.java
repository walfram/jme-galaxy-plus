package galaxy.core.shared;

import galaxy.core.Entity;
import galaxy.core.TechLevels;
import galaxy.core.ship.Weapons;

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
					double weaponsLevel = ship.prop(TechLevels.class).weapons();
					double n = 1 + 0.1 * Math.pow(caliber * weaponsLevel, 0.5);
					return n * caliber * weaponsLevel * guns;
				})
				.sum();
	}
}
