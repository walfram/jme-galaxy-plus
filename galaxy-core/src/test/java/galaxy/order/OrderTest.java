package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class OrderTest {

	@Test
	void order_execution_api_test() {
		Order order = mock(Order.class);
		GameContext context = mock(GameContext.class);

		order.applyTo(context);
	}

	// diplomacy change
	// split ship group
	// define ship type
	// gift ship group
	// define science
	// join groups
	// scrap ship group
	// load ship group
	// rename planet
	// change production
	// quit game
	// set route
	// send ship group
	// rename ship type
	// unload ship group
	// upgrade ship group

}
