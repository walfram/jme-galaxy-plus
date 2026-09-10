package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;
import galaxy.decorators.GameContextOf;
import galaxy.order.Orders;

public final class QuitBySleep implements Phase {
	@Override
	public GameContext process(GameContext context, Orders orders) {
		return new GameContextOf(context);
	}
}
