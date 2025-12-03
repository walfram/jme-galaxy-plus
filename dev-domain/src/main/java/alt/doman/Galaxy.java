package alt.doman;

import alt.doman.planet.Planet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Galaxy {

	// use add/poll to poll for FIFO
	private final Deque<Production> productions = new ArrayDeque<>();

	public void addProduction(Production production) {
		productions.add(production);
	}

	public int productionQueueSize() {
		return productions.size();
	}

	public void notifyShipsMove(Race race, Planet from, Planet to, List<Ship> ships) {
	}
}
