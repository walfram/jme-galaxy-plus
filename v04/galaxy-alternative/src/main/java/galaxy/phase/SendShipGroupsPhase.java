package galaxy.phase;

import galaxy.GameContext;
import galaxy.order.Orders;
import galaxy.Phase;

public class SendShipGroupsPhase implements Phase {

	@Override
	public GameContext process(GameContext context, Orders orders) {
		return orders.sendShipGroups().stream().reduce(
				context,
				(ctx, order) -> order.appliedTo(ctx),
				(a, b) -> b
		);
	}
}
