package alt.doman.orders;

import alt.doman.Order;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.planet.Planet;
import alt.doman.production.MaterialsProduction;

public record ProduceMaterials(Race race, Planet planet) implements Order {

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.addProduction(new MaterialsProduction(race, planet));
	}

}
