package galaxy.order;

import galaxy.Fixtures;
import galaxy.core.GameState;
import galaxy.core.Order;
import galaxy.core.Race;
import galaxy.core.ShipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

	@Test
	void should_replace_ship_type_if_no_ship_groups_exists_of_that_type() {
		Race race = mock(Race.class);
		when(race.shipTypes()).thenReturn(Fixtures.testShipTypes());

		ShipType drone = Fixtures.testShipTypeDrone();
		ShipType updated = new ShipType(
				drone.engines(), 1, 1.0, drone.shields(), drone.cargo(), "drone"
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

}
