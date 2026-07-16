package galaxy.core.state;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;

import java.util.List;
import java.util.NoSuchElementException;

public class ClassicGalaxy implements GameState {

	private final List<Race> races;
	private final List<Planet> planets;

	public ClassicGalaxy(List<Race> races, List<Planet> planets) {
		this.races = races;
		this.planets = planets;
	}

	@Override
	public void serializeInto(ObjectNode target) {
		ArrayNode racesJson = target.putArray("races");
		for (Race race : races) {
			ObjectNode o = racesJson.addObject();
			race.serializeInto(o);
		}

		ArrayNode planetsJson = target.putArray("planets");
		for (Planet planet : planets) {
			ObjectNode o = planetsJson.addObject();
			planet.serializeInto(o);
		}
	}

	@Override
	public Race findRace(Race.Id id) {
		return races.stream().filter(r -> r.id().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("No such race %s".formatted(id)));
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
