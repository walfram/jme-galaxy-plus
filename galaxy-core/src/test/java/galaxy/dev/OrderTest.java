package galaxy.dev;

import galaxy.core.GameState;
import galaxy.core.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class OrderTest {

	@Test
	void test_submitting_order() {
		Order order = mock(Order.class);
		GameState state = mock(GameState.class);

		boolean modified = order.modify(state);

		assertTrue(modified);
	}

}
