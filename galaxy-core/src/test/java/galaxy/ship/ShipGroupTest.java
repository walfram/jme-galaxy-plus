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

	private final ShipType drone = new ShipType("drone", new EnginesOf(1.0));
	private final ShipType colonizer = new ShipType("colonizer", new EnginesOf(1.0), new WeaponsOf(1, 1.0), new ShieldsOf(1.0), new CargoBayOf(1.0));

	private final ShipType hauler = new ShipType("hauler", new EnginesOf(2.0), new CargoBayOf(1.0));

	private final ShipType freighter = new ShipType("freighter", new EnginesOf(30.0), new ShieldsOf(9.5), new CargoBayOf(10.0));
	private final ShipType megaFreighter = new ShipType("megaFreighter", new EnginesOf(120.0), new ShieldsOf(38.43), new CargoBayOf(39.57));

	private final Planet planet = mock(Planet.class);

	private final Race race = new Race("test");

	@Test
	void test_group_mass_and_speed_loaded() {
		ShipGroup haulers = new ShipGroup(race, hauler);

		assertEquals(1, haulers.size());
		haulers.load(new Colonists(1.05));

		assertEquals(4.05, haulers.mass());
		assertEquals(9.876543209876544, haulers.speed());
	}

	@Test
	void test_group_mass_and_speed_unloaded() {
		ShipGroup haulers = new ShipGroup(race, hauler);

		assertEquals(3.0, haulers.mass());
		assertEquals(13.333333333333334, haulers.speed());
	}

	@Test
	void test_ship_group_cargo_loading() {
		ShipGroup colTransport = new ShipGroup(race, freighter);
		assertEquals(15.0, colTransport.cargoCapacity());
		assertDoesNotThrow(() -> colTransport.load(new Colonists(10.0)));

		ShipGroup matTransport = new ShipGroup(race, megaFreighter, new TechLevels(1.0, 1.0, 1.0, 2.0));
		assertDoesNotThrow(() -> matTransport.load(new Materials(200.0)));

		ShipGroup capTransport = new ShipGroup(race, megaFreighter);
		assertDoesNotThrow(() -> capTransport.load(new Capital(100.0)));
	}

	@Test
	void test_group_created() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);
		ShipGroup group = new ShipGroup(race, drone, 1);

		assertEquals(1.0, group.mass());
		assertEquals(20.0, group.speed());

		assertThrows(IllegalArgumentException.class, () -> group.load(new Colonists(1.0)));
	}

	@Test
	void test_turret_9x11() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

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
		Planet planet = mock(Planet.class);

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
		Planet planet = mock(Planet.class);

		ShipGroup drones = new ShipGroup(mock(Race.class), Fixtures.drone(), 1, new TechLevels());
		// TODO: assertEquals(0.0, drones.defencePower());

		ShipGroup fighters = new ShipGroup(mock(Race.class), Fixtures.fighter(), 1, new TechLevels());
		// TODO: assertEquals(2.31, fighters.defencePower());

		ShipGroup battleships = new ShipGroup(mock(Race.class), Fixtures.battleship(), 1, new TechLevels());
		// TODO: assertEquals(10.71, battleships.defencePower());
	}

	@Test
	void test_ship_group_offence() {
		Planet planet = mock(Planet.class);

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

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();

		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, hauler, 1, techLevels);
		group.load(new Colonists(1.05));
		assertEquals(9.876543209876544, group.speed());
	}

	@Test
	void test_ship_group_speed_without_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, hauler, 1, techLevels);
		assertEquals(13.3333333333333334, group.speed());
	}

	@Test
	void test_ship_group_mass_with_cargo() {
		Race race = mock(Race.class);

		ShipType hauler = Fixtures.hauler();
		assertEquals(3.0, hauler.mass());

		TechLevels techLevels = new TechLevels();
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, hauler, 1, techLevels);
		group.load(new Colonists(1.0));
		assertEquals(4.0, group.mass());
	}

	@Test
	void test_ship_group_mass_without_cargo() {
		Race race = mock(Race.class);
		ShipType shipType = new ShipType("drone-mk-2", new EnginesOf(1.0), new WeaponsOf(1, 1.0), new ShieldsOf(1.0), new CargoBayOf(1.0));
		TechLevels techLevels = new TechLevels();
		int size = 10;
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, shipType, size, techLevels);

		assertEquals(4.0, group.mass());
	}

	@Test
	void should_be_able_to_load_all_ships_in_group() {
		Race race = mock(Race.class);
		ShipType shipType = Fixtures.hauler();
		Planet planet = mock(Planet.class);

		ShipGroup haulers = new ShipGroup(race, shipType, 10, new TechLevels());
		assertEquals(10 * 1.05, haulers.cargoCapacity());

		assertDoesNotThrow(() -> haulers.load(new Colonists(10 * 1.05)));
		// TODO: assertEquals(10.0 * 1.05, haulers.cargoMass());
		// TODO: assertEquals(CargoType.COLONISTS, haulers.cargo());
	}

	@Test
	void should_increase_cargo_capacity_when_upgrading_ship_group_tech_levels() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, Fixtures.hauler(), 1, new TechLevels());
		assertEquals(1.05, group.cargoCapacity());

		// TODO
		// group.upgrade(new TechLevels(1, 1, 1, 2));
		// assertEquals(2.1, group.cargoBayCapacity());
	}

}
