package alt.doman.commands;

import alt.doman.Order;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.ShipTemplate;

public record CreateShipTemplate(Race race, ShipTemplate shipTemplate) implements Order {

	@Override
	public void invoke(Galaxy galaxy) {
		race.addShipTemplate(shipTemplate);
	}

}
