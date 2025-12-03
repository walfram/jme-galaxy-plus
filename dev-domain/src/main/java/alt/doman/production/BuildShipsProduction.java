package alt.doman.production;

import alt.doman.Production;
import alt.doman.Race;
import alt.doman.ShipTemplate;
import alt.doman.planet.Planet;

public record BuildShipsProduction(Race race, Planet planet, ShipTemplate shipTemplate) implements Production {
	@Override
	public boolean ready() {
		return false;
	}
}
