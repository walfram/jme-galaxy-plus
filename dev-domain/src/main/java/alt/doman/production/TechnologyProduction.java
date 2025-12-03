package alt.doman.production;

import alt.doman.Production;
import alt.doman.Race;
import alt.doman.Technology;
import alt.doman.planet.Planet;

public record TechnologyProduction(Race race, Planet planet, Technology technology) implements Production {

	public TechnologyProduction(Race race, Planet planet, Technology technology) {
		this.race = race;
		this.planet = planet;
		this.technology = technology;
		this.planet.updateProduction(this);
	}

	@Override
	public boolean ready() {
		return false;
	}

}
