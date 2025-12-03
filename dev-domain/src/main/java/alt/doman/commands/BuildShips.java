package alt.doman.commands;

import alt.doman.Command;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.ShipTemplate;
import alt.doman.planet.Planet;
import alt.doman.production.BuildShipsProduction;

public class BuildShips implements Command {
	private final Race race;
	private final Planet planet;
	private final ShipTemplate shipTemplate;

	public BuildShips(Race race, Planet planet, ShipTemplate shipTemplate) {
		this.race = race;
		this.planet = planet;
		this.shipTemplate = shipTemplate;
	}

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.addProduction(new BuildShipsProduction(race, planet, shipTemplate));
	}
}
