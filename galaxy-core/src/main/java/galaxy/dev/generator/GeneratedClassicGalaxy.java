package galaxy.dev.generator;

import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;

import java.util.List;

public class GeneratedClassicGalaxy implements GameState {
	public GeneratedClassicGalaxy(List<Race> races, int planetRatio) {
	}

	@Override
	public void serializeInto(ObjectNode target) {

	}

	@Override
	public Race findRace(Race.Id id) {
		return null;
	}

	@Override
	public List<Race> races() {
		return List.of();
	}

	@Override
	public List<Planet> planets() {
		return List.of();
	}
}
