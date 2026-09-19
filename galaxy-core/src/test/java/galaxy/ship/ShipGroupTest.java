package galaxy.ship;

import galaxy.Fixtures;
import galaxy.Planet;
import galaxy.Race;
import galaxy.TechLevels;
import galaxy.planet.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipGroupTest {

	private static final double DELTA = 1e-2;

	@Test
	void test_ship_group_state() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		when(planet.coordinates()).thenReturn(new Coordinates(1, 2));

		ShipGroup group = new ShipGroup(race, mock(ShipType.class), new TechLevels(), 1, planet);

		assertTrue(group.isInOrbit());
		assertNotNull(group.planet());
		assertNull(group.destination());
		assertNull(group.coordinates());

		Planet destination = mock(Planet.class);

		group.sendTo(destination);

		assertFalse(group.isInOrbit());
		assertTrue(group.isLaunched());
		assertNotNull(group.destination());
		assertNotNull(group.coordinates());

		group.move();

		assertTrue(group.isInHyperspace());

		group.arrive();

		assertTrue(group.isInOrbit());
		assertNull(group.destination());
		assertNull(group.coordinates());
		assertNotNull(group.planet());
		assertEquals(destination, group.planet());
	}

	@Test
	void test_turret_9x11() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		ShipType turret9x11 = new ShipType("Turret-9x11", new Engines(99.0), new Weapons(9, 11.0), new Shields(43.0), new Cargo(1.0));
		assertEquals(198.0, turret9x11.mass());

		ShipGroup group = new ShipGroup(race, turret9x11, new TechLevels(), 1, planet);
		assertEquals(10.0, group.speed());
		assertEquals(22.92382813162085, group.defencePower());

		group.upgrade(new TechLevels(4.34, 3.5, 3.91, 2.09));
		assertEquals(43.4, group.speed(), DELTA);
		// assertEquals(43.17, group.speedLoaded());
		assertEquals(38.50, group.attackPower(), DELTA);
		// assertEquals(561.5, group.bombingPower(), DELTA);
		assertEquals(89.63, group.defencePower(), DELTA);
	}

	@Test
	void test_ship_group_defence_with_cargo() {
		Planet planet = mock(Planet.class);

		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.droneMk2(), new TechLevels(), 1, planet, new CargoLoad(CargoType.COLONISTS, 1.05));
		assertEquals(1.81, drones.defencePower(), DELTA);

		ShipGroup freighters = new ShipGroup(mock(Race.class), Fixtures.freighter(), new TechLevels(), 1, planet, new CargoLoad(CargoType.COLONISTS, 15.0));
		assertEquals(7.36, freighters.defencePower(), DELTA);

		ShipGroup megaFreighters = new ShipGroup(mock(Race.class), Fixtures.megaFreighter(), new TechLevels(), 1, planet, new CargoLoad(CargoType.COLONISTS, 117.85));
		assertEquals(17.53, megaFreighters.defencePower(), DELTA);
	}

	@Test
	void test_ship_group_defense_without_cargo() {
		Planet planet = mock(Planet.class);

		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.drone(), new TechLevels(), 1, planet);
		assertEquals(0.0, drones.defencePower(), DELTA);

		ShipGroup fighters = new ShipGroup(mock(Race.class), Fixtures.fighter(), new TechLevels(), 1, planet);
		assertEquals(2.31, fighters.defencePower(), DELTA);

		ShipGroup battleships = new ShipGroup(mock(Race.class), Fixtures.battleship(), new TechLevels(), 1, planet);
		assertEquals(10.71, battleships.defencePower(), DELTA);
	}

	@Test
	void test_ship_group_offence() {
		Planet planet = mock(Planet.class);

		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.drone(), new TechLevels(), 1, planet);
		assertEquals(0.0, drones.attackPower());

		ShipGroup fighters = new ShipGroup(mock(Race.class), Fixtures.fighter(), new TechLevels(), 1, planet);
		assertEquals(1.2, fighters.attackPower());

		ShipGroup battleships = new ShipGroup(mock(Race.class), Fixtures.battleship(), new TechLevels(), 1, planet);
		assertEquals(25.0, battleships.attackPower());
	}

	@Test
	void test_ship_group_speed_with_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();

		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, hauler, techLevels, 1, planet, new CargoLoad(CargoType.COLONISTS, 1.05));
		assertEquals(9.87, group.speed(), DELTA);
	}

	@Test
	void test_ship_group_speed_without_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, hauler, techLevels, 1, planet);
		assertEquals(13.33, group.speed(), DELTA);
	}

	@Test
	void test_ship_group_mass_with_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, hauler, techLevels, 1, planet, new CargoLoad(CargoType.COLONISTS, 1.0));
		assertEquals(4.0, group.mass());
	}

	@Test
	void test_ship_group_mass_without_cargo() {
		Race race = mock(Race.class);
		ShipType shipType = new ShipType("drone-mk-2", new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new Cargo(1.0));
		TechLevels techLevels = new TechLevels();
		int size = 10;
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, shipType, techLevels, size, planet);

		assertEquals(4.0, group.mass());
	}

	@Test
	void should_be_able_to_load_all_ships_in_group() {
		Race race = mock(Race.class);
		ShipType shipType = Fixtures.hauler();
		Planet planet = mock(Planet.class);

		ShipGroup haulers = new ShipGroup(race, shipType, new TechLevels(), 10, planet);
		assertEquals(10.0 * 1.05, haulers.cargoCapacity());

		assertDoesNotThrow(() -> haulers.load(new CargoLoad(CargoType.COLONISTS, 10.0 * 1.05)));
		assertEquals(10.0 * 1.05, haulers.cargoMass());
		assertEquals(CargoType.COLONISTS, haulers.cargo());
	}

	@Test
	void should_increase_cargo_capacity_when_upgrading_ship_group_tech_levels() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, Fixtures.hauler(), new TechLevels(), 1, planet);
		assertEquals(1.05, group.cargoCapacity());

		group.upgrade(new TechLevels(1, 1, 1, 2));
		assertEquals(2.1, group.cargoCapacity());
	}

}
