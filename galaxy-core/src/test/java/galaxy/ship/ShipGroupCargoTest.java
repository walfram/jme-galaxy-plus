package galaxy.ship;

import galaxy.cargo.Cargo;
import galaxy.Fixtures;
import galaxy.Race;
import galaxy.cargo.Colonists;
import galaxy.cargo.Materials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipGroupCargoTest {

	private final Race race = new Race("test");

	@Test
	void test_cannot_load_cargo_if_no_cargo_hold_present() {
		ShipGroup group = new ShipGroup(race, Fixtures.drone(), 32);
		assertThrows(IllegalArgumentException.class, () -> group.load(new Colonists(129)));
	}

	@Test
	void test_load_cargo_on_multiple_ships() {
		ShipGroup group = new ShipGroup(race, Fixtures.megaFreighterMk2(), 32);
		assertEquals(32 * 600.0, group.cargoCapacity());

		assertDoesNotThrow(() -> group.load(new Colonists(32 * 600.0)));
	}

	@Test
	void should_increase_cargo_capacity_when_upgrading_ship_group_tech_levels() {
		ShipGroup group = new ShipGroup(race, Fixtures.hauler());
		assertEquals(1.05, group.cargoCapacity());

		group.upgrade(new TechLevels(1, 1, 1, 2));
		assertEquals(2.1, group.cargoCapacity());
	}

	@Test
	void test_can_only_load_one_cargo_type() {
		ShipGroup group = new ShipGroup(race, Fixtures.megaFreighterMk2(), 2);
		group.load(new Colonists(600.0));

		assertThrows(IllegalArgumentException.class, () -> group.load(new Materials(600.0)));
	}

	@Test
	void test_can_load_cargo_partially() {
		ShipGroup group = new ShipGroup(race, Fixtures.megaFreighterMk2(), 2);
		assertEquals(1200.0, group.cargoCapacity());

		group.load(new Colonists(800.0));
		assertEquals(800.0, group.cargo().quantity());

		group.load(new Colonists(400.0));
		assertEquals(1200.0, group.cargo().quantity());
	}

	@Test
	void test_unload_cargo() {
		ShipGroup group = new ShipGroup(race, Fixtures.megaFreighterMk2(), 2);
		group.load(new Colonists(1200.0));

		Cargo unloaded = group.unload();
		assertEquals(1200.0, unloaded.quantity());
		assertEquals(0.0, group.cargo().quantity());
	}

}
