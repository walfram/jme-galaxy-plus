package domain.ship;

import domain.Fixtures;
import domain.Race;
import domain.ShipTemplateFixtures;
import domain.planet.Planet;
import domain.planet.properties.Effort;
import domain.planet.properties.Industry;
import domain.planet.properties.Population;
import domain.production.Production;
import domain.production.ShipProduction;
import domain.technology.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

	@Test
	void test_ship_sending_to_another_planet() {
		Race race = Fixtures.race();

		Planet a = Fixtures.planetA();
		Planet b = Fixtures.planetB();

		Ship ship = ShipTemplateFixtures.drone().build(race.technologies(), race, a);

		assertEquals(a, ship.location());

		ship.flyTo(b);

		assertNull(ship.location());
	}

	@Test
	void test_ship_has_owner_and_created_at_planet() {
		Race race = Fixtures.race();
		Planet planet = Fixtures.homeworld();

		Ship ship = ShipTemplateFixtures.battleship().build(race.technologies(), race, planet);

		Race shipOwner = ship.owner();
		assertNotNull(shipOwner);
		assertEquals(race, shipOwner);

		Planet current = ship.location();
		assertNotNull(current);
		assertEquals(planet, current);
	}

	@Test
	void test_updating_race_technologies_does_not_affect_ship_technologies() {
		Race race = Fixtures.race();

		Technologies technologies = race.technologies();
		assertEquals(1.0, technologies.engines().value());
		assertEquals(1.0, technologies.weapons().value());
		assertEquals(1.0, technologies.shields().value());
		assertEquals(1.0, technologies.cargo().value());

		ShipTemplate droneTemplate = Fixtures.droneTemplate();
		Ship ship = droneTemplate.build(technologies, race, null);

		assertEquals(1.0, ship.engines().techLevel());
		assertEquals(1.0, ship.weapons().techLevel());
		assertEquals(1.0, ship.shields().techLevel());
		assertEquals(1.0, ship.cargo().techLevel());

		technologies.engines().upgrade(new Effort(new Industry(5000.0), new Population(5000.0)));
		assertEquals(2.0, technologies.engines().value());
		assertEquals(1.0, ship.engines().techLevel());

		technologies.weapons().upgrade(new Effort(new Industry(5000.0), new Population(5000.0)));
		assertEquals(2.0, technologies.weapons().value());
		assertEquals(1.0, ship.weapons().techLevel());

		technologies.shields().upgrade(new Effort(new Industry(5000.0), new Population(5000.0)));
		assertEquals(2.0, technologies.shields().value());
		assertEquals(1.0, ship.shields().techLevel());

		technologies.cargo().upgrade(new Effort(new Industry(5000.0), new Population(5000.0)));
		assertEquals(2.0, technologies.cargo().value());
		assertEquals(1.0, ship.cargo().techLevel());
	}

	@Test
	void test_ship_speed() {
		ShipTemplate droneTemplate = Fixtures.droneTemplate();

		Ship drone = droneTemplate.build(new Technologies(), null, null);
		assertEquals(20.0, drone.speed());

		Ship fastDrone = droneTemplate.build(new Technologies(
				new EnginesTechnology(2.0), new WeaponsTechnology(), new ShieldsTechnology(), new CargoTechnology()
		), null, null);
		assertEquals(40.0, fastDrone.speed());
	}

	@Test
	void test_building_drone_on_homeworld_with_material_stockpile() {
		Race race = Fixtures.race();
		Planet hw = Fixtures.homeworld();
		ShipTemplate drone = Fixtures.droneTemplate();

		hw.materials().update(100.0);

		assertEquals(0, race.ships().size());

		Production production = new ShipProduction(race, hw, drone);
		production.execute();

		assertEquals(100, race.ships().size());
	}

	@Test
	void test_building_drone_on_homeworld_without_material_stockpile() {
		Race race = Fixtures.race();
		Planet hw = Fixtures.homeworld();
		ShipTemplate drone = Fixtures.droneTemplate();

		assertEquals(0, race.ships().size());

		Production production = new ShipProduction(race, hw, drone);
		production.execute();

		assertEquals(99, race.ships().size());
	}

	@Test
	void test_building_battleship_on_homeworld() {
		Race race = Fixtures.race();
		Planet hw = Fixtures.homeworld();

		hw.materials().update(10.0);

		ShipTemplate battleship = ShipTemplateFixtures.battleshipAlt();
		assertEquals(90.0, battleship.weight());

		Production production = new ShipProduction(race, hw, battleship);
		production.execute();

		assertEquals(1, race.ships().size());

		assertEquals(9.10891089108911, hw.massFromPrevTurn());
	}

}
