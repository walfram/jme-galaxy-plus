package alt.doman.orders;

import alt.doman.Order;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.Science;
import alt.doman.planet.Planet;
import alt.doman.production.ScienceProduction;

public record ResearchScience(Race race, Planet planet, Science science) implements Order {

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.addProduction(new ScienceProduction(race, planet, science));
	}

}
