package alt.doman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ContextTest {

	private Galaxy galaxy;

	@BeforeEach
	void setup() {
		galaxy = mock(Galaxy.class);
	}

	@Test
	void test_ship_upgrade() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		List<Ship> ships = galaxy.shipsAt(race, planet);

		List<Ship> upgraded = galaxy.upgrade(race, planet, ships);

		assertNotNull(upgraded);
		assertFalse(upgraded.isEmpty());
		assertEquals(ships.size(), upgraded.size());
	}

	@Test
	void test_ship_transfer() {
		Planet planet = mock(Planet.class);
		Race raceFoo = mock(Race.class);
		Race raceBar = mock(Race.class);

		List<Ship> shipsFooBefore = galaxy.shipsAt(raceFoo, planet);
		assertNotNull(shipsFooBefore);
		assertFalse(shipsFooBefore.isEmpty());

		List<Ship> shipsBarBefore = galaxy.shipsAt(raceBar, planet);
		assertNotNull(shipsBarBefore);
		assertTrue(shipsBarBefore.isEmpty());

		List<Ship> transferred = galaxy.transfer(raceFoo, raceBar, shipsFooBefore);
		assertNotNull(transferred);
		assertFalse(transferred.isEmpty());

		List<Ship> shipsBarAfter = galaxy.shipsAt(raceBar, planet);
		assertNotNull(shipsBarAfter);
		assertFalse(shipsBarAfter.isEmpty());

		assertEquals(transferred, shipsBarAfter);
	}

	@Test
	void test_race_production() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		Production production = mock(Production.class);

		assertFalse(production.ready());
		assertDoesNotThrow(() -> galaxy.produce(race, planet, production));
		assertTrue(production.ready());
	}

	@Test
	void test_race_send_ships() {
		Race race = mock(Race.class);

		Planet from = mock(Planet.class);
		Planet to = mock(Planet.class);

		List<Ship> ships = galaxy.shipsAt(race, from);
		assertNotNull(ships);
		assertFalse(ships.isEmpty());

		List<Ship> sent = galaxy.sendShips(race, from, to, ships);
		assertNotNull(sent);
		assertFalse(sent.isEmpty());

		assertEquals(ships, sent);
		// TODO check all ships' status "in flight"?
	}

	@Test
	void test_race_research_science() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		Science science = mock(Science.class);

		Science researched = galaxy.research(race, planet, science);
		assertNotNull(researched);
		assertNotEquals(science, researched);
	}

	@Test
	void test_race_research_tech() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		Technology tech = mock(Technology.class);

		double level = galaxy.technologyLevel(race, tech);
		assertEquals(1.0, level);

		// TODO use effort 500 == 0.1 difference
		double upgraded = galaxy.research(race, planet, tech);
		assertEquals(1.1, upgraded);
	}

	@Test
	void test_race_build_ships() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		ShipTemplate shipTemplate = mock(ShipTemplate.class);

		List<Ship> ships = galaxy.buildShips(race, planet, shipTemplate);
		assertNotNull(ships);
		assertFalse(ships.isEmpty());
	}

	@Test
	void test_race_create_ship_template() {
		Race race = mock(Race.class);

		ShipTemplate template = mock(ShipTemplate.class);
		galaxy.createShipTemplate(race, template);

		List<ShipTemplate> templates = galaxy.shipTemplates(race);
		assertNotNull(templates);
		assertFalse(templates.isEmpty());
	}

	@Test
	void test_planet_visibility() {
		Race race = mock(Race.class);

		List<Planet> visible = galaxy.visiblePlanets(race);
		assertNotNull(visible);
		assertFalse(visible.isEmpty());

		List<Planet> unknown = galaxy.unknownPlanets(race);
		assertNotNull(unknown);
		assertFalse(unknown.isEmpty());

		List<Planet> friendly = galaxy.friendlyPlanets(race);
		assertNotNull(friendly);
		assertFalse(friendly.isEmpty());

		List<Planet> hostile = galaxy.hostilePlanets(race);
		assertNotNull(hostile);
		assertFalse(hostile.isEmpty());

		List<Planet> visited = galaxy.visitedPlanets(race);
		assertNotNull(visited);
		assertFalse(visited.isEmpty());

		List<Planet> all = galaxy.planetsAll(race);
		assertNotNull(all);
		assertFalse(all.isEmpty());
	}

	@Test
	void test_race_ships() {
		Race race = mock(Race.class);
		List<Ship> ships = galaxy.ships(race);
		assertNotNull(ships);
		assertFalse(ships.isEmpty());
	}

	@Test
	void test_race_have_planets() {
		Race race = mock(Race.class);
		List<Planet> planets = galaxy.planetsOwned(race);
		assertNotNull(planets);
		assertFalse(planets.isEmpty());
	}

	@Test
	void test_galaxy_has_planets() {
		assertNotNull(galaxy.planets());
		assertFalse(galaxy.planets().isEmpty());
	}

	@Test
	void test_galaxy_has_races() {
		assertNotNull(galaxy.races());
		assertFalse(galaxy.races().isEmpty());
	}

}
