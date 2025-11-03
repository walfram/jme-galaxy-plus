package galaxy.domain.ship;

import galaxy.domain.Fixtures;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.domain.production.ShipProduction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {

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
	void test_building_drone_on_homeworld() {
		Race race = Fixtures.race();
		Planet hw = Fixtures.homeworld();
		ShipTemplate drone = Fixtures.droneTemplate();

		assertEquals(0, race.ships().size());

		Production production = new ShipProduction(race, hw, drone);
		production.execute();

		assertEquals(99, race.ships().size());
	}

}
