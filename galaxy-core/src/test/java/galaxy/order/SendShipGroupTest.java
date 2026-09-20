package galaxy.order;

import galaxy.Fixtures;
import galaxy.Order;
import galaxy.Planet;
import galaxy.Race;
import galaxy.context.GameContext;
import galaxy.context.ShipGroups;
import galaxy.ship.ShipGroup;
import galaxy.ship.state.InOrbit;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SendShipGroupTest {

	@Test
	void should_send_ship_group() {
		Race race = new Race("foo");
		ShipGroup shipGroup = new ShipGroup(race, Fixtures.hauler());

		Planet origin = mock(Planet.class);
		Planet destination = mock(Planet.class);

		GameContext context = mock(GameContext.class);
		when(context.shipGroups()).thenReturn(new ShipGroups(Map.of(shipGroup, new InOrbit(shipGroup, origin))));

		assertEquals(1, context.shipGroups().orbiting().size());

		Order order = new SendShipGroup(race, shipGroup, origin, destination);
		assertDoesNotThrow(() -> order.applyTo(context));

		assertEquals(0, context.shipGroups().orbiting().size());
		assertEquals(1, context.shipGroups().launched().size());
	}

}
