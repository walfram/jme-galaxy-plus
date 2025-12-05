package alt.doman;

import alt.doman.phases.*;
import alt.doman.planet.Planet;
import alt.doman.planet.PlanetView;
import alt.doman.planet.UnknownPlanet;
import alt.doman.planet.VisiblePlanet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Galaxy {

	// use add/poll to poll for FIFO
	private final Deque<Production> productions = new ArrayDeque<>();

	private final List<Race> races;
	private final List<Planet> planets;

	private final List<Phase> phases = List.of(
			new CombantPhase(),
			new BombingPhase(),

			new LoadPhase(),
			new UpgradePhase(),
			new FlightPhase(),

			new CombantPhase(),
			new BombingPhase(),

			new ProductionPhase(),
			new PopulationPhase(),
			new UnloadPhase()
	);

	public Galaxy(List<Race> races, List<Planet> planets) {
		this.races = races;
		this.planets = planets;
	}

	public void addProduction(Production production) {
		productions.add(production);
	}

	public int productionQueueSize() {
		return productions.size();
	}

	public void notifyShipsMove(Race race, Planet from, Planet to, List<Ship> ships) {
	}

	public Race race(String name) {
		return races.stream().filter(race -> race.name().equals(name)).findFirst().orElseThrow();
	}

	public List<PlanetView> planets(Race race) {
		List<Planet> visible = race.planets();

		List<UnknownPlanet> unknown = planets.stream()
				.filter(planet -> !visible.contains(planet))
				.map(UnknownPlanet::new)
				.toList();

		List<PlanetView> combined = new ArrayList<>(visible.stream().map(VisiblePlanet::new).toList());
		combined.addAll(unknown);

		return combined;
	}

	public void updateState(double tpf) {
		for (Phase phase : phases) {
			phase.run(this);
		}
	}

}
