package galaxy.ship;

import fixtures.ShipTypeFixtures;
import galaxy.CargoType;
import galaxy.Race;
import galaxy.TechLevels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipGroupSpeedTest {

	private static final double DELTA = 1e-2;

	private final Race race = mock(Race.class);

	@BeforeEach
	void setup() {
		when(race.techLevels()).thenReturn(new TechLevels());
	}

	@Test
	void test_mega_freighter_speed() {
		ShipGroup group = new ShipGroup(race, ShipTypeFixtures.megaFreighter(), 1);
		assertEquals(12.12, group.speed(), DELTA);

		double maxCargoWeight = group.maxCargoWeight();
		assertEquals(196.14, maxCargoWeight, DELTA);

		group.loadCargo(CargoType.MATERIALS, maxCargoWeight);
		assertEquals(6.09, group.speed(), DELTA);
	}

	@Test
	void test_freighter_speed() {
		ShipGroup group = new ShipGroup(race, ShipTypeFixtures.freighter(), 1);
		assertEquals(12.12, group.speed(), DELTA);

		double maxCargoWeight = group.maxCargoWeight();
		assertEquals(20.0, maxCargoWeight);

		group.loadCargo(CargoType.MATERIALS, maxCargoWeight);
		assertEquals(20.0, group.cargoWeight());

		assertEquals(8.63, group.speed(), DELTA);
	}

	@Test
	void test_hauler_speed() {
		ShipGroup group = new ShipGroup(race, ShipTypeFixtures.hauler(), 1);
		assertEquals(13.33, group.speed(), DELTA);

		double maxCargoWeight = group.maxCargoWeight();
		assertEquals(1.10, maxCargoWeight);

		group.loadCargo(CargoType.MATERIALS, maxCargoWeight);
		assertEquals(9.75, group.speed(), DELTA);
	}

	@Test
	void test_battleship_speed() {
		ShipGroup group = new ShipGroup(race, ShipTypeFixtures.battleship(), 1);
		assertEquals(6.60, group.speed(), DELTA);

		double maxCargoWeight = group.maxCargoWeight();
		assertEquals(1.10, maxCargoWeight);

		group.loadCargo(CargoType.MATERIALS, maxCargoWeight);
		assertEquals(6.52, group.speed(), DELTA);
	}

	@Test
	void test_battle_cruiser_speed() {
		ShipGroup group = new ShipGroup(race, ShipTypeFixtures.battleCruiser(), 1);
		assertEquals(10.0, group.speed(), DELTA);

		double maxCargoWeight = group.maxCargoWeight();
		assertEquals(1.10, maxCargoWeight);

		group.loadCargo(CargoType.MATERIALS, maxCargoWeight);
		assertEquals(9.89, group.speed(), DELTA);
	}

}
