package galaxy.order;

import galaxy.core.GameState;
import galaxy.core.Order;
import galaxy.core.Race;
import galaxy.core.ShipType;
import galaxy.Fixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

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
