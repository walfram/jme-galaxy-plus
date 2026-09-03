package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import galaxy.Planet;
import galaxy.Race;
import galaxy.ship.ShipGroup;

import java.util.List;

public class SendShipGroupOrder implements Order {
	public SendShipGroupOrder(Race race, ShipGroup shipGroup, List<Planet> planets) {

	}

	@Override
	public void modify(GameContext context) {

	}
}
