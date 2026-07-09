package galaxy.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {

	@Test
	void order_is_a_request_to_modify_game_state() {
		GameState state = mock(GameState.class);
		Order order = mock(Order.class);

		when(order.modify(any())).thenReturn(true);

		boolean submitted = assertDoesNotThrow(() -> order.modify(state));
		assertTrue(submitted);
	}

}
