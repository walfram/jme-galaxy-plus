package galaxy.ship;

import fixtures.ShipTypeFixtures;
import galaxy.Fixtures;
import galaxy.Race;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTypeMassTest {

	@Test
	void test_mass_mega_freighter() {
		assertEquals(198.0, ShipTypeFixtures.megaFreighter().mass());
	}

	@Test
	void test_mass_freighter() {
		assertEquals(49.5, ShipTypeFixtures.freighter().mass());
	}

	@Test
	void test_mass_hauler() {
		assertEquals(3.0, ShipTypeFixtures.hauler().mass());
	}

	@Test
	void tesT_mass_space_gun() {
		assertEquals(19.8, ShipTypeFixtures.spaceGun().mass());
	}

	@Test
	void test_mass_orbital_fort() {
		assertEquals(99.0, ShipTypeFixtures.orbitalFort().mass());
	}

	@Test
	void test_mass_battle_station() {
		assertEquals(198.0, ShipTypeFixtures.battleStation().mass());
	}

	@Test
	void test_mass_battleship() {
		assertEquals(100.0, ShipTypeFixtures.battleship().mass());
	}

	@Test
	void test_mass_battle_cruiser() {
		assertEquals(99.0, ShipTypeFixtures.battleCruiser().mass());
	}

	@Test
	void test_mass_cruiser() {
		assertEquals(49.5, ShipTypeFixtures.cruiser().mass());
	}

	@Test
	void test_mass_destroyer() {
		assertEquals(18.0, ShipTypeFixtures.destroyer().mass());
	}

	@Test
	void test_mass_gunship() {
		assertEquals(11.0, ShipTypeFixtures.gunship().mass());
	}

	@Test
	void test_mass_fighter() {
		assertEquals(4.95, ShipTypeFixtures.fighter().mass(), 1e-6);
	}

	@Test
	void test_mass_fast_flak() {
		assertEquals(2.02, ShipTypeFixtures.fastFlak().mass());
	}

	@Test
	void test_mass_flak() {
		assertEquals(3.0, ShipTypeFixtures.flak().mass());
	}

	@Test
	void test_ship_group_weight_drone_99() {
		Race race = Fixtures.race();

		ShipType drone = ShipTypeFixtures.drone();
		ShipGroup group = new ShipGroup(race, drone, race.techLevels(), 99);

		assertEquals(99.0, group.weight());
	}

}
