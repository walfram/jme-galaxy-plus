package alt.doman.production;

import alt.doman.Production;
import alt.doman.Race;
import alt.doman.planet.Planet;

public record MaterialsProduction(Race race, Planet planet) implements Production {
	public MaterialsProduction(Race race, Planet planet) {
		this.race = race;
		this.planet = planet;
		this.planet.updateProduction(this);
	}

	@Override
	public boolean ready() {
		return false;
	}
}
