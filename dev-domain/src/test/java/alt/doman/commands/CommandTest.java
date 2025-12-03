package alt.doman.commands;

import alt.doman.*;
import alt.doman.planet.Planet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CommandTest {

	@Test
	void test_produce_materials() {
		Race race = new Race("foo");
		Planet planet = new Planet(0, null, null, null);

		Galaxy galaxy = new Galaxy();
		assertEquals(0, galaxy.productionQueueSize());
		assertNull(planet.production());

		Command command = new ProduceMaterials(race, planet);
		command.invoke(galaxy);

		assertNotNull(planet.production());

		assertEquals(1, galaxy.productionQueueSize());
	}

	@Test
	void test_upgrade_science() {
		Race race = new Race("foo");
		Planet planet = new Planet(0, null, null, null);
		Science science = mock(Science.class);

		Galaxy galaxy = new Galaxy();
		assertEquals(0, galaxy.productionQueueSize());

		assertNull(planet.production());

		Command command = new ResearchScience(race, planet, science);
		command.invoke(galaxy);

		assertNotNull(planet.production());
		assertEquals(1, galaxy.productionQueueSize());
	}

	@Test
	void test_upgrade_technology_level() {
		Race race = new Race("foo");
		Planet planet = new Planet(0, null, null, null);
		Technology technology = Technology.ENGINES;

		Galaxy galaxy = new Galaxy();
		assertEquals(0, galaxy.productionQueueSize());

		assertNull(planet.production());

		Command command = new ResearchTechnology(race, planet, technology);
		command.invoke(galaxy);

		assertNotNull(planet.production());

		assertEquals(1, galaxy.productionQueueSize());
	}

	@Test
	void test_send_ships() {
		Race race = new Race("foo");

		Planet a = new Planet(1, null, null, null);
		Planet b = new Planet(2, null, null, null);

		ShipTemplate shipTemplate = mock(ShipTemplate.class);
		TechnologyLevel technologyLevel = new TechnologyLevel();

		List<Ship> ships = List.of(
				new Ship(shipTemplate, technologyLevel, race, a)
		);

		for (Ship ship : ships) {
			assertEquals(a, ship.location());
			assertNull(ship.destination());
		}

		assertFalse(a.ships().isEmpty());
		assertTrue(b.ships().isEmpty());

		Galaxy galaxy = new Galaxy();

		Command command = new SendShips(race, a, b, ships);
		command.invoke(galaxy);

		assertTrue(a.ships().isEmpty());

		for (Ship ship : ships) {
			assertNull(ship.location());
			assertEquals(b, ship.destination());
		}
	}

	@Test
	void test_enqueue_ship_production() {
		Race race = new Race("foo");
		Planet planet = new Planet(0, null, null, null);
		ShipTemplate shipTemplate = mock(ShipTemplate.class);

		Galaxy galaxy = new Galaxy();
		assertEquals(0, galaxy.productionQueueSize());

		assertNull(planet.production());

		Command buildShips = new BuildShips(race, planet, shipTemplate);
		buildShips.invoke(galaxy);

		assertNotNull(planet.production());

		assertEquals(1, galaxy.productionQueueSize());
	}

	@Test
	void test_create_ship_template() {
		Race race = new Race("foo");

		assertTrue(race.shipTemplates().isEmpty());

		ShipTemplate template = mock(ShipTemplate.class);

		Command command = new CreateShipTemplate(race, template);
		Galaxy galaxy = mock(Galaxy.class);

		command.invoke(galaxy);

		assertFalse(race.shipTemplates().isEmpty());
		assertEquals(1, race.shipTemplates().size());
	}

	@Test
	void test_assign_planet() {
		Planet planet = new Planet(0, null, null, null);
		assertNull(planet.owner());

		Race race = new Race("foo");
		assertTrue(race.planets().isEmpty());

		Command command = new AssignPlanet(race, planet);

		Galaxy galaxy = mock(Galaxy.class);
		command.invoke(galaxy);

		assertFalse(race.planets().isEmpty());
		assertEquals(1, race.planets().size());
		assertEquals(race, planet.owner());
	}

}
