package galaxy.ship;

import galaxy.Fixtures;
import galaxy.Planet;
import galaxy.Race;
import galaxy.planet.Capital;
import galaxy.planet.Colonists;
import galaxy.planet.Materials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ShipGroupTest {

	private final Race race = new Race("test");

	@Test
	void test_drones_speed() {
		ShipGroup drones = new ShipGroup(race, Fixtures.drone());
		assertEquals(20.0, drones.speed());

		drones.upgrade(new TechLevels(2.0, 1.0, 1.0, 1.0));
		assertEquals(40.0, drones.speed());
	}

	@Test
	void test_mega_freighter_mk2_fully_loaded_speed() {
		ShipGroup megaFreighters = new ShipGroup(race, Fixtures.megaFreighterMk2());
		assertEquals(1, megaFreighters.size());
		assertDoesNotThrow(() -> megaFreighters.load(new Colonists(600.0)));
		assertEquals(1.968019680196802, megaFreighters.speed());

		ShipGroup freighters = new ShipGroup(race, Fixtures.megaFreighterMk2(), 2);
		assertEquals(2, freighters.size());
		assertDoesNotThrow(() -> freighters.load(new Colonists(1200.0)));
		assertEquals(1.968019680196802, freighters.speed());
	}

	@Test
	void test_group_mass_and_speed_loaded() {
		ShipGroup haulers = new ShipGroup(race, Fixtures.hauler());

		assertEquals(1, haulers.size());
		haulers.load(new Colonists(1.05));

		assertEquals(4.05, haulers.mass());
		assertEquals(9.876543209876544, haulers.speed());
	}

	@Test
	void test_group_mass_and_speed_unloaded() {
		ShipGroup haulers = new ShipGroup(race, Fixtures.hauler());

		assertEquals(3.0, haulers.mass());
		assertEquals(13.333333333333334, haulers.speed());
	}

	@Test
	void test_ship_group_cargo_loading() {
		ShipGroup colTransport = new ShipGroup(race, Fixtures.freighter());
		assertEquals(15.0, colTransport.cargoCapacity());
		assertDoesNotThrow(() -> colTransport.load(new Colonists(10.0)));

		ShipGroup capTransport = new ShipGroup(race, Fixtures.megaFreighter());
		assertEquals(117.85924500000002, capTransport.cargoCapacity());
		assertDoesNotThrow(() -> capTransport.load(new Capital(100.0)));

		ShipGroup matTransport = new ShipGroup(race, Fixtures.megaFreighter(), new TechLevels(1.0, 1.0, 1.0, 2.0));
		assertEquals(2.0 * 117.85924500000002, matTransport.cargoCapacity());
		assertDoesNotThrow(() -> matTransport.load(new Materials(200.0)));
	}

	@Test
	void test_group_created() {
		Race race = new Race("foo");
		ShipGroup group = new ShipGroup(race, Fixtures.drone(), 1);

		assertEquals(1.0, group.mass());
		assertEquals(20.0, group.speed());

		assertThrows(IllegalArgumentException.class, () -> group.load(new Colonists(1.0)));
	}

	@Test
	void test_turret_9x11() {
		Race race = mock(Race.class);

		ShipType turret9x11 = new ShipType("Turret-9x11", new EnginesOf(99.0), new WeaponsOf(9, 11.0), new ShieldsOf(43.0), new CargoBayOf(1.0));
		assertEquals(198.0, turret9x11.mass());
		assertEquals(1.05, turret9x11.cargoBay().capacity());

		ShipGroup group = new ShipGroup(race, turret9x11, 1, new TechLevels());
		assertEquals(10.0, group.speed());
		// TODO: assertEquals(22.92382813162085, group.defencePower());

		group.upgrade(new TechLevels(4.34, 3.5, 3.91, 2.09));
		assertEquals(43.39999999999999, group.speed());
		// TODO group.load(new Colonists(1.05));
		// TODO assertEquals(43.17, group.speed());
		// TODO assertEquals(38.50, group.attackPower());
		// assertEquals(561.5, group.bombingPower());
		// TODO assertEquals(89.63, group.defencePower());
	}

	@Test
	void test_ship_group_defence_with_cargo() {
		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.droneMk2(), new TechLevels());
		drones.load(new Colonists(1.05));
		// TODO: assertEquals(1.81, drones.defencePower());

		ShipGroup freighters = new ShipGroup(mock(Race.class), Fixtures.freighter(), 1, new TechLevels());
		freighters.load(new Colonists(15.0));
		// TODO: assertEquals(7.36, freighters.defencePower());

		ShipGroup megaFreighters = new ShipGroup(mock(Race.class), Fixtures.megaFreighter(), 1, new TechLevels());
		megaFreighters.load(new Colonists(117.85));
		// TODO: assertEquals(17.53, megaFreighters.defencePower());
	}

	@Test
	void test_ship_group_defense_without_cargo() {
		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.drone(), 1, new TechLevels());
		// TODO: assertEquals(0.0, drones.defencePower());

		ShipGroup fighters = new ShipGroup(mock(Race.class), Fixtures.fighter(), 1, new TechLevels());
		// TODO: assertEquals(2.31, fighters.defencePower());

		ShipGroup battleships = new ShipGroup(mock(Race.class), Fixtures.battleship(), 1, new TechLevels());
		// TODO: assertEquals(10.71, battleships.defencePower());
	}

	@Test
	void test_ship_group_offence() {
		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.drone(), 1, new TechLevels());
		// TODO: assertEquals(0.0, drones.attackPower());

		ShipGroup fighters = new ShipGroup(mock(Race.class), Fixtures.fighter(), 1, new TechLevels());
		// TODO: assertEquals(1.2, fighters.attackPower());

		ShipGroup battleships = new ShipGroup(mock(Race.class), Fixtures.battleship(), 1, new TechLevels());
		// TODO: assertEquals(25.0, battleships.attackPower());
	}

	@Test
	void test_ship_group_speed_with_cargo() {
		Race race = mock(Race.class);

		ShipGroup haulers = new ShipGroup(race, Fixtures.hauler());
		assertEquals(3.0, haulers.mass());

		haulers.load(new Colonists(1.05));
		assertEquals(9.876543209876544, haulers.speed());
	}

	@Test
	void test_ship_group_speed_without_cargo() {
		Race race = mock(Race.class);

		ShipGroup haulers = new ShipGroup(race, Fixtures.hauler());
		assertEquals(13.3333333333333334, haulers.speed());
	}

	@Test
	void test_ship_group_mass_with_cargo() {
		ShipGroup haulers = new ShipGroup(race, Fixtures.hauler());

		haulers.load(new Colonists(1.0));
		assertEquals(4.0, haulers.mass());
	}

	@Test
	void test_ship_group_mass_without_cargo() {
		Race race = mock(Race.class);

		ShipGroup group = new ShipGroup(race, Fixtures.droneMk2(), 10);

		assertEquals(4.0, group.mass());
	}

	@Test
	void should_be_able_to_load_all_ships_in_group() {
		Race race = mock(Race.class);

		ShipGroup haulers = new ShipGroup(race, Fixtures.hauler(), 10);
		assertEquals(10 * 1.05, haulers.cargoCapacity());

		assertDoesNotThrow(() -> haulers.load(new Colonists(10 * 1.05)));
		// TODO: assertEquals(10.0 * 1.05, haulers.cargoMass());
		// TODO: assertEquals(CargoType.COLONISTS, haulers.cargo());
	}

	@Test
	void should_increase_cargo_capacity_when_upgrading_ship_group_tech_levels() {
		Race race = mock(Race.class);

		ShipGroup group = new ShipGroup(race, Fixtures.hauler());
		assertEquals(1.05, group.cargoCapacity());

		// TODO
		// group.upgrade(new TechLevels(1, 1, 1, 2));
		// assertEquals(2.1, group.cargoBayCapacity());
	}

}
