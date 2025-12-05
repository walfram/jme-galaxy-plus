package alt.doman.orders;

import alt.doman.Order;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.planet.Planet;

public record AssignPlanet(Race race, Planet planet) implements Order {

	@Override
	public void invoke(Galaxy galaxy) {
		race.claim(planet);
	}

}
