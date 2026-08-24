package galaxy.core.state;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ClassicGalaxy implements GameState, Serializable {

	private final List<Race> races;
	private final List<Planet> planets;

	private final Map<Integer, ShipGroup> shipGroups;

	public ClassicGalaxy(List<Race> races, List<Planet> planets, Map<Integer, ShipGroup> shipGroups) {
		this.races = races;
		this.planets = planets;
		this.shipGroups = shipGroups;
	}

	public ClassicGalaxy(GameState source) {
		this(
				source.races(),
				source.planets(),
				source.shipGroups()
		);
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
		return List.copyOf(races);
	}

	@Override
	public List<Planet> planets() {
		return List.copyOf(planets);
	}

	@Override
	public ShipGroup shipGroup(int id) {
		return null;
	}

	public Map<Integer, ShipGroup> shipGroups() {
		return new HashMap<>(shipGroups);
	}

}
