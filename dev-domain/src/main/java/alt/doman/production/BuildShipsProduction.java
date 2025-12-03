package alt.doman.production;

import alt.doman.Production;
import alt.doman.Race;
import alt.doman.ShipTemplate;
import alt.doman.planet.Planet;

public record BuildShipsProduction(Race race, Planet planet, ShipTemplate shipTemplate) implements Production {

	public BuildShipsProduction(Race race, Planet planet, ShipTemplate shipTemplate) {
		this.race = race;
		this.planet = planet;
		this.shipTemplate = shipTemplate;
		this.planet.updateProduction(this);
	}

	@Override
	public boolean ready() {
		return false;
	}

}
