package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;
import galaxy.Planets;
import galaxy.order.Orders;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StandardTurnTest {

	@Test
	void should_return_context_without_exception() {
		Phase phase = new StandardTurn();

		GameContext source = mock(GameContext.class);

		Planets planets = mock(Planets.class);
		when(source.planets()).thenReturn(planets);

		Orders orders = mock(Orders.class);

		GameContext context = assertDoesNotThrow(() -> phase.process(source, orders));
		assertNotNull(context);
	}

}
