package alt.doman.orders;

import alt.doman.Order;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.Ship;
import alt.doman.planet.Planet;

import java.util.List;

public record SendShips(Race race, Planet from, Planet to, List<Ship> ships) implements Order {

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.notifyShipsMove(race, from, to, ships);
		for (Ship ship : ships) {
			ship.updateDestination(to);
		}
	}

}
