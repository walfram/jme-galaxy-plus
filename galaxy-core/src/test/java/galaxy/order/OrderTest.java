package galaxy.order;

import galaxy.core.GameState;
import galaxy.core.Order;
import galaxy.core.OrderResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

	@Test
	void should_return_failure_order_result() {
		Order order = mock(Order.class);
		GameState state = mock(GameState.class);

		when(order.modify(any())).thenReturn(new OrderResult(false, "failed to execute order"));

		OrderResult result = assertDoesNotThrow(() -> order.modify(state));
		assertFalse(result.success());
	}

	@Test
	void should_return_success_order_result() {
		Order order = mock(Order.class);
		GameState state = mock(GameState.class);

		when(order.modify(any())).thenReturn(new OrderResult(true, "order executed"));

		OrderResult result = assertDoesNotThrow(() -> order.modify(state));
		assertTrue(result.success());
	}

}
