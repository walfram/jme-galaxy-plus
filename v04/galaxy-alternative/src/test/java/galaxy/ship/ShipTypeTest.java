package galaxy.ship;

import galaxy.ShipType;
import galaxy.fixtures.ShipTypeFixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTypeTest {

	private static final double DELTA = 1e-2;
	
	@Test
	void test_drone() {
		ShipType type = ShipTypeFixtures.drone();
		assertEquals(1.0, new ShipTypeMass(type).value());
		assertEquals(20.00, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_flak() {
		ShipType type = ShipTypeFixtures.flak();
		assertEquals(3.0, new ShipTypeMass(type).value());
		assertEquals(6.66, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_fast_flak() {
		ShipType type = ShipTypeFixtures.fastFlak();
		assertEquals(2.02, new ShipTypeMass(type).value());
		assertEquals(10.00, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_fighter() {
		ShipType type = ShipTypeFixtures.fighter();
		assertEquals(4.95, new ShipTypeMass(type).value(), 1e-6);
		assertEquals(10.02, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_gunship() {
		ShipType type = ShipTypeFixtures.gunship();
		assertEquals(11.0, new ShipTypeMass(type).value());
		assertEquals(7.27, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_destroyer() {
		ShipType type = ShipTypeFixtures.destroyer();
		assertEquals(18.0, new ShipTypeMass(type).value());
		assertEquals(6.66, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_cruiser() {
		ShipType type = ShipTypeFixtures.cruiser();
		assertEquals(49.5, new ShipTypeMass(type).value());
		assertEquals(6.66, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_battle_cruiser() {
		ShipType type = ShipTypeFixtures.battleCruiser();
		assertEquals(99.0, new ShipTypeMass(type).value());
		assertEquals(10.00, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_battleship() {
		ShipType type = ShipTypeFixtures.battleship();
		assertEquals(100.0, new ShipTypeMass(type).value());
		// NOTE: table listed 6.66, but that implies a type Mass of 99.00.
		// ShipTypeMassTest asserts battleship().mass() == 100.0, so the
		// mass()-derived speed here is 20 * (33.00 / 100.00) = 6.60.
		assertEquals(6.60, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_battle_station() {
		ShipType type = ShipTypeFixtures.battleStation();
		assertEquals(198.0, new ShipTypeMass(type).value());
		assertEquals(10.00, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_orbital_fort() {
		ShipType type = ShipTypeFixtures.orbitalFort();
		assertEquals(99.0, new ShipTypeMass(type).value());
		assertEquals(0.00, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_space_gun() {
		ShipType type = ShipTypeFixtures.spaceGun();
		assertEquals(19.8, new ShipTypeMass(type).value());
		assertEquals(0.00, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_hauler() {
		ShipType type = ShipTypeFixtures.hauler();
		assertEquals(3.0, new ShipTypeMass(type).value());
		assertEquals(13.33, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_freighter() {
		ShipType type = ShipTypeFixtures.freighter();
		assertEquals(49.5, new ShipTypeMass(type).value());
		assertEquals(12.12, new ShipTypeSpeed(type).value(), DELTA);
	}

	@Test
	void test_mega_freighter() {
		ShipType type = ShipTypeFixtures.megaFreighter();
		assertEquals(198.0, new ShipTypeMass(type).value());
		assertEquals(12.12, new ShipTypeSpeed(type).value(), DELTA);
	}
	
}
