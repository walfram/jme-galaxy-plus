package alt.doman.commands;

import alt.doman.Command;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.ShipTemplate;

public class CreateShipTemplate implements Command {
	private final Race race;
	private final ShipTemplate shipTemplate;

	public CreateShipTemplate(Race race, ShipTemplate shipTemplate) {
		this.race = race;
		this.shipTemplate = shipTemplate;
	}

	@Override
	public void invoke(Galaxy galaxy) {
		race.addShipTemplate(shipTemplate);
	}
}
