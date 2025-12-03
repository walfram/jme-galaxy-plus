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
	void test_colonizing() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		List<Planet> owned = galaxy.racePlanets(race);
		assertEquals(1, owned.size());

		Ship ship = mock(Ship.class);

		Command unloadColonists = mock(Command.class);
		galaxy.execute(unloadColonists);

		assertEquals(2, owned.size());
	}

	@Test
	void test_race_diplomacy() {
		Race raceFoo = mock(Race.class);
		Race raceBar = mock(Race.class);

		Diplomacy war = galaxy.diplomacyStatus(raceFoo, raceBar);
		assertEquals(Diplomacy.WAR, war);

		Command changeDiplomacy = mock(Command.class);
		galaxy.execute(changeDiplomacy);

		Diplomacy status = galaxy.diplomacyStatus(raceFoo, raceBar);
		assertEquals(Diplomacy.PEACE, status);

		Diplomacy diplomacy = galaxy.diplomacyStatus(raceBar, raceFoo);
		assertEquals(Diplomacy.WAR, diplomacy);
	}

	@Test
	void test_ship_upgrade() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		List<Ship> ships = galaxy.shipsAtPlanet(race, planet);
		List<TechnologyLevel> shipTechLevelsBefore = ships.stream().map(Ship::technologyLevel).toList();

		Command upgradeShips = mock(Command.class);
		galaxy.execute(upgradeShips);

		List<TechnologyLevel> shipTechLevelsAfter = ships.stream().map(Ship::technologyLevel).toList();
		assertNotEquals(shipTechLevelsBefore, shipTechLevelsAfter);
	}

	@Test
	void test_ship_transfer() {
		Planet planet = mock(Planet.class);
		Race raceFoo = mock(Race.class);
		Race raceBar = mock(Race.class);

		List<Ship> shipsFooBefore = galaxy.shipsAtPlanet(raceFoo, planet);
		assertNotNull(shipsFooBefore);
		assertFalse(shipsFooBefore.isEmpty());

		List<Ship> shipsBarBefore = galaxy.shipsAtPlanet(raceBar, planet);
		assertNotNull(shipsBarBefore);
		assertTrue(shipsBarBefore.isEmpty());

//		List<Ship> transferred = galaxy.transfer(raceFoo, raceBar, shipsFooBefore);
//		assertNotNull(transferred);
//		assertFalse(transferred.isEmpty());

		Command transferShips = mock(Command.class);
		galaxy.execute(transferShips);

		List<Ship> shipsBarAfter = galaxy.shipsAtPlanet(raceBar, planet);
		assertNotNull(shipsBarAfter);
		assertFalse(shipsBarAfter.isEmpty());
	}

	@Test
	void test_race_production() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		Command capitalProduction = mock(Command.class);
		galaxy.execute(capitalProduction);

		// TODO check production status
	}

	@Test
	void test_race_send_ships() {
		Race race = mock(Race.class);

		Planet from = mock(Planet.class);
		Planet to = mock(Planet.class);

		List<Ship> ships = galaxy.shipsAtPlanet(race, from);
		assertNotNull(ships);
		assertFalse(ships.isEmpty());

		Command sendShips = mock(Command.class);
		galaxy.execute(sendShips);

		assertTrue(ships.isEmpty());

		// TODO check all ships' status "in flight"?
	}

	@Test
	void test_race_research_science() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		Science science = mock(Science.class);

		TechnologyLevel before = galaxy.technologyLevel(race);
		assertEquals(1.0, before.level(Technology.ENGINES));

		List<Science> sciences = galaxy.sciences(race);
		assertTrue(sciences.isEmpty());

		Command research = mock(Command.class);
		galaxy.execute(research);

		assertFalse(sciences.isEmpty());

		TechnologyLevel after = galaxy.technologyLevel(race);
		assertEquals(1.1, after.level(Technology.ENGINES));
	}

	@Test
	void test_race_research_tech() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);

		TechnologyLevel before = galaxy.technologyLevel(race);
		assertEquals(1.0, before.level(Technology.ENGINES));

		// TODO use effort 500 == 0.1 difference
		// double upgraded = galaxy.research(race, planet, tech);
		Command research = mock(Command.class);
		galaxy.execute(research);

		TechnologyLevel after = galaxy.technologyLevel(race);

		assertEquals(1.1, after.level(Technology.ENGINES));
	}

	@Test
	void test_race_build_ships() {
		Race race = mock(Race.class);
		Planet planet = mock(Planet.class);
		ShipTemplate shipTemplate = mock(ShipTemplate.class);

		Command buildShips = mock(Command.class);

		galaxy.execute(buildShips);

		List<Ship> ships = galaxy.shipsAtPlanet(race, planet);

		assertNotNull(ships);
		assertFalse(ships.isEmpty());
	}

	@Test
	void test_race_create_ship_template() {
		Race race = mock(Race.class);

		ShipTemplate template = mock(ShipTemplate.class);
		Command createShipTemplate = mock(Command.class);
		galaxy.execute(createShipTemplate);

		List<ShipTemplate> templates = galaxy.shipTemplates(race);
		assertNotNull(templates);
		assertFalse(templates.isEmpty());
	}

	@Test
	void test_planet_visibility() {
		Race race = mock(Race.class);

		List<Planet> visible = galaxy.knownPlanets(race);
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
		List<Planet> planets = galaxy.racePlanets(race);
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
