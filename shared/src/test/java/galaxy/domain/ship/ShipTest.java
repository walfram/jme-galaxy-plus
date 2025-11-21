package galaxy.domain.ship;

import galaxy.domain.Fixtures;
import galaxy.domain.Race;
import galaxy.domain.planet.properties.Effort;
import galaxy.domain.planet.properties.Industry;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.properties.Population;
import galaxy.domain.production.Production;
import galaxy.domain.production.ShipProduction;
import galaxy.domain.technology.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {

	@Test
	void test_updating_race_technologies_does_not_affect_ship_technologies() {
		Race race = Fixtures.race();

		Technologies technologies = race.technologies();
		assertEquals(1.0, technologies.engines().value());
		assertEquals(1.0, technologies.weapons().value());
		assertEquals(1.0, technologies.shields().value());
		assertEquals(1.0, technologies.cargo().value());

		ShipTemplate droneTemplate = Fixtures.droneTemplate();
		Ship ship = droneTemplate.build(technologies);

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

		Ship drone = droneTemplate.build(new Technologies());
		assertEquals(20.0, drone.speed());

		Ship fastDrone = droneTemplate.build(new Technologies(
				new EnginesTechnology(2.0), new WeaponsTechnology(), new ShieldsTechnology(), new CargoTechnology()
		));
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

}
