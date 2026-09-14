package galaxy;

import galaxy.ship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ShipGroupTest {

	private static final double DELTA = 1e-2;

	@Test
	void test_ship_group_defense() {

	}

	@Test
	void test_ship_group_offence() {

	}

	@Test
	void test_ship_group_speed_with_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();

		ShipGroup group = new ShipGroup(race, hauler, techLevels, 1, new CargoLoad(CargoType.COLONISTS, 1.05));
		assertEquals(9.87, group.speed(), DELTA);
	}

	@Test
	void test_ship_group_speed_without_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();

		ShipGroup group = new ShipGroup(race, hauler, techLevels, 1);
		assertEquals(13.33, group.speed(), DELTA);
	}

	@Test
	void test_ship_group_mass_with_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();

		ShipGroup group = new ShipGroup(race, hauler, techLevels, 1, new CargoLoad(CargoType.COLONISTS, 1.0));
		assertEquals(4.0, group.mass());
	}

	@Test
	void test_ship_group_mass_without_cargo() {
		Race race = mock(Race.class);
		ShipType shipType = new ShipType("drone-mk-2", new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new Cargo(1.0));
		TechLevels techLevels = new TechLevels();
		int size = 10;

		ShipGroup group = new ShipGroup(race, shipType, techLevels, size);

		assertEquals(4.0, group.mass());
	}

	@Test
	void should_be_able_to_load_all_ships_in_group() {
		Race race = mock(Race.class);
		ShipType shipType = Fixtures.hauler();

		ShipGroup haulers = new ShipGroup(race, shipType, new TechLevels(), 10);
		assertEquals(10.0 * 1.05, haulers.cargoCapacity());

		assertDoesNotThrow(() -> haulers.load(new CargoLoad(CargoType.COLONISTS, 10.0 * 1.05)));
		assertEquals(10.0 * 1.05, haulers.cargoMass());
		assertEquals(CargoType.COLONISTS, haulers.cargoType());
	}

	@Test
	void should_increase_cargo_capacity_when_upgrading_ship_group_tech_levels() {
		ShipGroup group = new ShipGroup(mock(Race.class), Fixtures.hauler(), new TechLevels(), 1);
		assertEquals(1.05, group.cargoCapacity());

		group.upgrade(new TechLevels(1, 1, 1, 2));
		assertEquals(2.1, group.cargoCapacity());
	}

}
