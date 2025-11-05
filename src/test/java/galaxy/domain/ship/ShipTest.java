package galaxy.domain.ship;

import galaxy.domain.Fixtures;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.domain.production.ShipProduction;
import galaxy.domain.technology.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {

	// TODO test that checks that ship's technologies are independent from race technologies

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
