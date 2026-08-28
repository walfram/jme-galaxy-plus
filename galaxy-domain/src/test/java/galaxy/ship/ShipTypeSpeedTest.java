package galaxy.ship;

import fixtures.ShipTypeFixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTypeSpeedTest {

	private static final double DELTA = 1e-2;

	@Test
	void test_speed_drone() {
		assertEquals(20.00, ShipTypeFixtures.drone().speed(), DELTA);
	}

	@Test
	void test_speed_flak() {
		assertEquals(6.66, ShipTypeFixtures.flak().speed(), DELTA);
	}

	@Test
	void test_speed_fast_flak() {
		assertEquals(10.00, ShipTypeFixtures.fastFlak().speed(), DELTA);
	}

	@Test
	void test_speed_fighter() {
		assertEquals(10.02, ShipTypeFixtures.fighter().speed(), DELTA);
	}

	@Test
	void test_speed_gunship() {
		assertEquals(7.27, ShipTypeFixtures.gunship().speed(), DELTA);
	}

	@Test
	void test_speed_destroyer() {
		assertEquals(6.66, ShipTypeFixtures.destroyer().speed(), DELTA);
	}

	@Test
	void test_speed_cruiser() {
		assertEquals(6.66, ShipTypeFixtures.cruiser().speed(), DELTA);
	}

	@Test
	void test_speed_battle_cruiser() {
		assertEquals(10.00, ShipTypeFixtures.battleCruiser().speed(), DELTA);
	}

	@Test
	void test_speed_battleship() {
		// NOTE: table listed 6.66, but that implies a Ship Mass of 99.00.
		// ShipTypeMassTest asserts battleship().mass() == 100.0, so the
		// mass()-derived speed here is 20 * (33.00 / 100.00) = 6.60.
		assertEquals(6.60, ShipTypeFixtures.battleship().speed(), DELTA);
	}

	@Test
	void test_speed_battle_station() {
		assertEquals(10.00, ShipTypeFixtures.battleStation().speed(), DELTA);
	}

	@Test
	void test_speed_orbital_fort() {
		assertEquals(0.00, ShipTypeFixtures.orbitalFort().speed(), DELTA);
	}

	@Test
	void test_speed_space_gun() {
		assertEquals(0.00, ShipTypeFixtures.spaceGun().speed(), DELTA);
	}

	@Test
	void test_speed_hauler() {
		assertEquals(13.33, ShipTypeFixtures.hauler().speed(), DELTA);
	}

	@Test
	void test_speed_freighter() {
		assertEquals(12.12, ShipTypeFixtures.freighter().speed(), DELTA);
	}

	@Test
	void test_speed_mega_freighter() {
		assertEquals(12.12, ShipTypeFixtures.megaFreighter().speed(), DELTA);
	}

}
