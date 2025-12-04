package alt.doman.commands;

import alt.doman.Order;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.ShipTemplate;
import alt.doman.planet.Planet;
import alt.doman.production.BuildShipsProduction;

public record BuildShips(Race race, Planet planet, ShipTemplate shipTemplate) implements Order {

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.addProduction(new BuildShipsProduction(race, planet, shipTemplate));
	}

}
