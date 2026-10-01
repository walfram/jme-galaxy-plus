package galaxy.battle;

import galaxy.Race;
import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.random.RandomGenerator;

public class BattleSimTest {

	private static final int fleetSize = 256;

	private final RandomGenerator random = RandomGenerator.getDefault();

	private final Race red = new Race("Red");
	private final Race blue = new Race("Blue");

	@Test
	void test_battle_2_sides() {
		Set<ShipGroup> left = generateFleet(red);
		Set<ShipGroup> right = generateFleet(blue);
	}

	private Set<ShipGroup> generateFleet(Race race) {
		Set<ShipGroup> fleet = new HashSet<>(fleetSize);

		// 35-45 percent - cover ships
		// 55-65 percent - main ships
		int coverSize = random.nextInt(35, 45);

		return fleet;
	}

}
