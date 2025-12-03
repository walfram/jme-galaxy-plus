package alt.doman.production;

import alt.doman.Production;
import alt.doman.Race;
import alt.doman.Science;
import alt.doman.planet.Planet;

public record ScienceProduction(Race race, Planet planet, Science science) implements Production {
	public ScienceProduction(Race race, Planet planet, Science science) {
		this.race = race;
		this.planet = planet;
		this.science = science;
		this.planet.updateProduction(this);
	}

	@Override
	public boolean ready() {
		return false;
	}
}
