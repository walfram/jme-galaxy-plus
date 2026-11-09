package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import jme3utilities.math.noise.Generator;
import random.Weighted;

import java.util.List;

public final class RandomShipGroup implements Weighted {

	private final List<ShipType> types;
	private final int maxShipsPerGroup;
	private final double weight;

	public RandomShipGroup(List<ShipType> types, int maxShipsPerGroup, double weight) {
		this.types = types;
		this.maxShipsPerGroup = maxShipsPerGroup;
		this.weight = weight;
	}

	@Override
	public double weight() {
		return weight;
	}

	public ShipGroup generate(Generator generator, Race race) {
		ShipType type = generator.pick(types);
		int size = generator.nextInt(1, maxShipsPerGroup);
		return new ShipGroup(race, type, size);
	}
}
