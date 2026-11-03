package galaxy.battle;

import galaxy.Race;
import galaxy.ship.Engines;
import galaxy.ship.Shields;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import jme3utilities.math.noise.Generator;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class RandomDronesFleet {

	private static final List<ShipType> droneTypes = List.of(
			new ShipType("drone-mk-1", new Engines(1)),
			new ShipType("drone-mk-2", new Engines(1), new Shields(1)),
			new ShipType("drone-mk-3", new Engines(1.5), new Shields(1.5)),
			new ShipType("drone-mk-4", new Engines(1), new Shields(3))
	);

	private final Race race;

	public RandomDronesFleet(Race race) {
		this.race = race;
	}

	public Collection<ShipGroup> generateFleet() {
		Generator generator = new Generator(42);

		int count = generator.nextInt(1, 256);
		ShipType type = generator.pick(droneTypes);

		return IntStream.range(0, count).mapToObj(i -> new ShipGroup(race, type)).toList();
	}
}
