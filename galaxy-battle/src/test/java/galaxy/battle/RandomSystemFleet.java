package galaxy.battle;

import galaxy.Race;
import galaxy.ship.*;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import random.Distribution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.random.RandomGenerator;

public final class RandomSystemFleet {

	private static final Logger logger = LoggerFactory.getLogger(RandomSystemFleet.class);

	private static final int fleetSize = 256;

	private static final List<ShipType> droneTypes = List.of(
			new ShipType("drone-mk-1", new Engines(1)),
			new ShipType("drone-mk-2", new Engines(1), new Shields(1)),
			new ShipType("drone-mk-3", new Engines(1.5), new Shields(1.5)),
			new ShipType("drone-mk-4", new Engines(1), new Shields(3))
	);

	private static final List<ShipType> turretTypes = List.of(
			new ShipType("barrel-mk1", new Engines(49.0), new Weapons(1, 15.0), new Shields(1.0), new CargoBay(1.0)),
			new ShipType("barrel-mk2", new Engines(49.0), new Weapons(1, 21.0), new Shields(1.0), new CargoBay(1.0)),
			new ShipType("barrel-mk3", new Engines(49.0), new Weapons(1, 31.0), new Shields(1.0), new CargoBay(1.0)),
			new ShipType("cruiser-mk1", new Engines(49.0), new Weapons(5, 8.0), new Shields(1.0), new CargoBay(1.0)),
			new ShipType("cruiser-mk2", new Engines(49.0), new Weapons(5, 12.0), new Shields(1.0), new CargoBay(1.0))
	);

	private static final List<ShipType> perfTypes = List.of(
			new ShipType("light-perf-mk1", new Engines(50.0), new Weapons(60, 1.0), new Shields(10.0), new CargoBay(1.0)),
			new ShipType("light-perf-mk2", new Engines(50.0), new Weapons(60, 1.2), new Shields(10.0), new CargoBay(1.0)),
			new ShipType("light-perf-mk3", new Engines(50.0), new Weapons(60, 1.5), new Shields(10.0), new CargoBay(1.0)),
			new ShipType("heavy-perf-mk1", new Engines(50.0), new Weapons(70, 2.0), new Shields(10.0), new CargoBay(1.0)),
			new ShipType("heavy-perf-mk2", new Engines(50.0), new Weapons(70, 2.5), new Shields(10.0), new CargoBay(1.0))
	);

	private final RandomGenerator random = RandomGenerator.getDefault();

	private final Race race;

	public RandomSystemFleet(Race race) {
		this.race = race;
	}


	public Set<ShipGroup> generateFleet() {
		Set<ShipGroup> fleet = new HashSet<>(fleetSize);

		// 35-45 percent - cover ships
		// 55-65 percent - main ships
		double dronesRatio = random.nextDouble(0.35, 0.45);
		int droneAmount = (int) (fleetSize * dronesRatio);

		int remainder = fleetSize - droneAmount;

		// perf/turret - 2/1 up to 3/2
		double min = 3.0 / 5.0;
		double max = 3.0 / 2.0;
		double perfRatio = random.nextDouble(min, max);
		int perfAmount = (int) (remainder * perfRatio);
		int turretAmount = remainder - perfAmount;

		Distribution<RandomShipGroup> distribution = new Distribution<>(List.of(
				new RandomShipGroup(droneTypes, 99, droneAmount),
				new RandomShipGroup(perfTypes, 4, perfAmount),
				new RandomShipGroup(turretTypes, 4, turretAmount)
		));

		Generator generator = new Generator(1337);

		while (fleet.size() < fleetSize) {
			RandomShipGroup picked = distribution.pick(generator);
			ShipGroup group = picked.generate(generator, race);
			fleet.add(group);
		}

		double minSpeed = fleet.stream().mapToDouble(ShipGroup::speed).min().orElseThrow();
		double maxSpeed = fleet.stream().mapToDouble(ShipGroup::speed).max().orElseThrow();
		logger.info("generated fleet for race {}, min speed = {}, max speed = {}", race, minSpeed, maxSpeed);

		return fleet;
	}

}
