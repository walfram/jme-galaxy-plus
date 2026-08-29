package galaxy.core.state;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.*;

import java.util.*;

public class ClassicGalaxy implements GameState, Serializable {

	private final List<Race> races;
	private final List<Planet> planets;

//	private final Map<Integer, ShipGroup> shipGroups;

	private final Set<PlanetOwnership> planetOwnership = new HashSet<>();

	public ClassicGalaxy(List<Race> races, List<Planet> planets) {
		this.races = races;
		this.planets = planets;
	}

	public ClassicGalaxy(GameState source) {
		this(
				source.races(),
				source.planets()
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
	public Race findRace(Id id) {
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

//	@Override
//	public ShipGroup shipGroup(int id) {
//		return null;
//	}

//	public Map<Integer, ShipGroup> shipGroups() {
//		return new HashMap<>(shipGroups);
//	}

	@Override
	public List<Planet> racePlanets(Id raceId) {
		return planetOwnership.stream().filter(po -> po.race().id().equals(raceId)).map(po -> findPlanet(po.planet().id())).toList();
	}

	@Override
	public void colonizePlanet(Race race, Planet planet) {
		PlanetOwnership key = new PlanetOwnership(race, planet);

		if (planetOwnership.contains(key))
			throw new IllegalArgumentException("Planet %s is already owned by %s".formatted(planet.id(), race.id()));

		planetOwnership.add(key);
		race.registerPlanet(planet);
	}

	@Override
	public Planet findPlanet(Id id) {
		return planets.stream().filter(p -> p.id().equals(id)).findFirst().orElseThrow();
	}

//	@Override
//	public void createShipGroup(Race race, ShipGroup shipGroup) {
//
//	}

}
