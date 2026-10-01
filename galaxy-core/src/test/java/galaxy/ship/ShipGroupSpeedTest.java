package galaxy.ship;

import galaxy.Fixtures;
import galaxy.Race;
import galaxy.cargo.Colonists;
import galaxy.cargo.Materials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipGroupSpeedTest {

	private final Race race = new Race("test");

	@Test
	void test_drone_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.drone(), 32);
		assertEquals(20.0, group.speed());
	}

	@Test
	void test_flak_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.flak(), 32);
		assertEquals(6.666666666666667, group.speed());
	}

	@Test
	void test_fast_flak_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.fastFlak(), 22);
		assertEquals(10.0, group.speed());
	}

	@Test
	void test_fighter_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.fighter(), 32);
		assertEquals(10.020202020202023, group.speed());
	}

	@Test
	void test_turret_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.turret9x11(), 32);
		assertEquals(10.0, group.speed());
	}

	@Test
	void test_mega_freighter_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.megaFreighterMk2(), 32);
		assertEquals(7.511737089201878, group.speed());

		group.load(new Colonists(32 * 600.0));

		assertEquals(1.968019680196802, group.speed());
	}

	@Test
	void test_battle_cruiser_speed() {
		ShipGroup group = new ShipGroup(race, Fixtures.battleCruiser(), 32);
		assertEquals(10.0, group.speed());

		group.load(new Materials(32.0 * 1.05));

		assertEquals(9.89505247376312, group.speed());
	}

}
