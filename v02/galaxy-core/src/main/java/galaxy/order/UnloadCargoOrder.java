package galaxy.order;

import galaxy.core.*;

public class UnloadCargoOrder implements Order {
	public UnloadCargoOrder(Race race, ShipGroup shipGroup, Planet planet) {
	}

	@Override
	public OrderResult modify(GameState state) {
		return null;
	}
}
