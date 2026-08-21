package galaxy.order;

import galaxy.core.GameState;
import galaxy.core.Order;
import galaxy.core.Race;
import galaxy.core.ShipType;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;
import galaxy.core.state.ClassicGalaxy;
import galaxy.Fixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefineShipTypeTest {

	@Test
	void should_add_ship_type_to_race() {
		GameState state = new ClassicGalaxy(Fixtures.testRaces(), Fixtures.testPlanets());

		Race race = state.findRace(new Race.Id("foo"));

		assertTrue(race.shipTypes().isEmpty());

		Order defineShipType = new DefineShipType(race, new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new Cargo(1.0), "armed-drone");
		defineShipType.modify(state);

		assertFalse(race.shipTypes().isEmpty());

		ShipType shipType = race.shipTypes().first();
		assertEquals(new ShipType(new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new Cargo(1.0), "armed-drone"), shipType);
	}

}
