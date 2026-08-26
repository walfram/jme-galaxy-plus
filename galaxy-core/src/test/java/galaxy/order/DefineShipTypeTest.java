package galaxy.order;

import galaxy.core.*;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;
import galaxy.core.state.ClassicGalaxy;
import galaxy.Fixtures;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefineShipTypeTest {

	@Test
	void should_replace_ship_type_if_no_ship_groups_exists_of_that_type() {
		Race race = mock(Race.class);
		when(race.shipTypes()).thenReturn(Fixtures.testShipTypes());

		ShipType drone = Fixtures.testShipTypeDrone();
		ShipType updated = new ShipType(
				drone.engines(), new Weapons(1, 1.0), drone.shields(), drone.cargo(), "drone"
		);

		assertEquals(drone.name(), updated.name());

		GameState state = mock(GameState.class);
		when(state.findRace(any())).thenReturn(race);

		Order order = new DefineShipType(race, updated);
		assertDoesNotThrow(() -> order.modify(state));
		assertEquals(1, race.shipTypes().size());

		ShipType check = race.shipTypes().find("drone");
		assertNotNull(check);
		assertEquals(updated, check);
	}

	@Test
	void test_create_ship_type() {
		Race race = mock(Race.class);
		when(race.shipTypes()).thenReturn(Fixtures.testShipTypes());

		ShipType type = mock(ShipType.class);

		GameState state = mock(GameState.class);
		when(state.findRace(any())).thenReturn(race);

		Order order = new DefineShipType(race, type);
		assertDoesNotThrow(() -> order.modify(state));

		assertEquals(2, race.shipTypes().size());
	}

	@Test
	void should_add_ship_type_to_race() {
		GameState state = new ClassicGalaxy(Fixtures.testRaces(), Fixtures.testPlanets());

		Race race = state.findRace(new Id("foo"));

		assertTrue(race.shipTypes().isEmpty());

		Order defineShipType = new DefineShipType(race, new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new Cargo(1.0), "armed-drone");
		defineShipType.modify(state);

		assertFalse(race.shipTypes().isEmpty());

		ShipType shipType = race.shipTypes().first();
		assertEquals(new ShipType(new Engines(1.0), new Weapons(1, 1.0), new Shields(1.0), new Cargo(1.0), "armed-drone"), shipType);
	}

}
