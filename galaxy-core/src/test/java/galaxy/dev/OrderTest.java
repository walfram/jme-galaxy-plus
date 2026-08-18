package galaxy.dev;

import galaxy.core.GameState;
import galaxy.core.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

	@Test
	void test_submitting_order() {
		Order order = mock(Order.class);
		GameState state = mock(GameState.class);

		when(order.modify(state)).thenReturn(true);

		boolean modified = order.modify(state);

		assertTrue(modified);
	}

}
