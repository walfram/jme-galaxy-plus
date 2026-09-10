package galaxy;

import galaxy.order.Orders;

public interface Phase {
	GameContext process(GameContext context, Orders orders);
}
