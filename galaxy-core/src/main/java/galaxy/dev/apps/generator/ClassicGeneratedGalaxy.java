package galaxy.dev.apps.generator;

import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.state.ClassicGalaxy;
import galaxy.generator.ClassicGeneratedPlanets;
import galaxy.generator.GeneratedPlanets;

import java.util.ArrayList;
import java.util.List;

public class ClassicGeneratedGalaxy {

	private final List<Race> races;
	private final int planetRatio;
	private final long seed;

	public ClassicGeneratedGalaxy(List<Race> races, int planetRatio, long seed) {
		this.races = races;
		this.planetRatio = planetRatio;
		this.seed = seed;
	}

	public GameState generate() {
		int planetCount = races.size() * planetRatio;
		List<Planet> planets = new ArrayList<>(planetCount);

		GeneratedPlanets generatedPlanets = new ClassicGeneratedPlanets(races.size(), planetRatio, seed);
		planets.addAll(generatedPlanets.planets());

		return new ClassicGalaxy(races, planets);
	}

}
