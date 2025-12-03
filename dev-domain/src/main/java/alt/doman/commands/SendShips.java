package alt.doman.commands;

import alt.doman.Command;
import alt.doman.Galaxy;
import alt.doman.Race;
import alt.doman.Ship;
import alt.doman.planet.Planet;

import java.util.List;

public class SendShips implements Command {
	private final Race race;
	private final Planet from;
	private final Planet to;
	private final List<Ship> ships;

	public SendShips(Race race, Planet from, Planet to, List<Ship> ships) {
		this.race = race;
		this.from = from;
		this.to = to;
		this.ships = ships;
	}

	@Override
	public void invoke(Galaxy galaxy) {
		galaxy.notifyShipsMove(race, from, to, ships);
		for (Ship ship : ships) {
			ship.updateDestination(to);
		}
	}
}
