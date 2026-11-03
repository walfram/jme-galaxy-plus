package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BattleSimTest {

	private static final Logger logger = LoggerFactory.getLogger(BattleSimTest.class);

	private final Race red = new Race("Red");
	private final Race green = new Race("Green");
	private final Race blue = new Race("Blue");
	private final Race yellow = new Race("Yellow");

	private final Map<Race, Collection<ShipGroup>> fleets = Map.of(
			red, new RandomSystemFleet(red).generateFleet(),
			green, new RandomSystemFleet(green).generateFleet(),
			blue, new RandomSystemFleet(blue).generateFleet(),
			yellow, new RandomSystemFleet(yellow).generateFleet()
	);

	@Test
	void test_battle_between_drones_and_fleet() {
		Map<Race, Collection<ShipGroup>> participants = Map.of(
				red, new RandomSystemFleet(red).generateFleet(),
				green, new RandomDronesFleet(green).generateFleet()
		);

		BattleGenerator generator = new BattleGenerator(participants);

		Map<Race, Collection<ShipGroup>> result = generator.generate();

		assertEquals(1, result.size());
		assertTrue(result.containsKey(red));
	}

	@Test
	void test_battle_v2() {
		BattleGenerator generator = new BattleGenerator(fleets);
		Map<Race, Collection<ShipGroup>> result = generator.generate();

		assertEquals(1, result.size());
	}

}
