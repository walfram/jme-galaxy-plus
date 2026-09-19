package galaxy.ships;

import galaxy.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ShipGroupTest {

	private final ShipType drone = new ShipType("drone", new EnginesOf(1.0));
	private final ShipType colonizer = new ShipType("colonizer", new EnginesOf(1.0), new WeaponsOf(1, 1.0), new ShieldsOf(1.0), new CargoBayOf(1.0));

	private final ShipType hauler = new ShipType("hauler", new EnginesOf(2.0), new CargoBayOf(1.0));

	private final ShipType freighter = new ShipType("freighter", new EnginesOf(30.0), new ShieldsOf(9.5), new CargoBayOf(10.0));
	private final ShipType megaFreighter = new ShipType("megaFreighter", new EnginesOf(120.0), new ShieldsOf(38.43), new CargoBayOf(39.57));

	private final Planet planet = mock(Planet.class);

	private final Race race = new Race("test");

	@Test
	void test_group_mass_and_speed_loaded() {
		ShipGroup haulers = new ShipGroup(race, planet, hauler);

		assertEquals(1, haulers.size());
		haulers.load(new Colonists(1.05));

		assertEquals(4.05, haulers.mass());
		assertEquals(9.876543209876544, haulers.speed());
	}

	@Test
	void test_group_mass_and_speed_unloaded() {
		ShipGroup haulers = new ShipGroup(race, planet, hauler);

		assertEquals(3.0, haulers.mass());
		assertEquals(13.333333333333334, haulers.speed());
	}

	@Test
	void test_ship_group_cargo_loading() {
		ShipGroup colTransport = new ShipGroup(race, planet, freighter);
		assertEquals(15.0, colTransport.cargoBayCapacity());
		assertDoesNotThrow(() -> colTransport.load(new Colonists(10.0)));

		ShipGroup matTransport = new ShipGroup(race, planet, megaFreighter, new TechLevels(1.0, 1.0, 1.0, 2.0));
		assertDoesNotThrow(() -> matTransport.load(new Materials(200.0)));

		ShipGroup capTransport = new ShipGroup(race, planet, megaFreighter);
		assertDoesNotThrow(() -> capTransport.load(new Capital(100.0)));
	}

	@Test
	void test_group_created() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);
		ShipGroup group = new ShipGroup(race, planet, drone, 1);

		assertEquals(1.0, group.mass());
		assertEquals(20.0, group.speed());

		assertThrows(IllegalArgumentException.class, () -> group.load(new Colonists(1.0)));
	}

}
