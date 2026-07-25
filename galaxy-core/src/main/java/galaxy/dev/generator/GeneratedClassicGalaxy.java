package galaxy.dev.generator;

import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.state.ClassicGalaxy;

import java.util.ArrayList;
import java.util.List;

public class GeneratedClassicGalaxy {

	private final List<Race> races;
	private final int planetRatio;

	public GeneratedClassicGalaxy(List<Race> races, int planetRatio) {
		this.races = races;
		this.planetRatio = planetRatio;
	}

	public GameState generate() {
		int planetCount = races.size() * planetRatio;
		List<Planet> planets = new ArrayList<>(planetCount);

		// generate homeworlds

		// generate uninhabited planets

		return new ClassicGalaxy(races, planets);
	}

}
