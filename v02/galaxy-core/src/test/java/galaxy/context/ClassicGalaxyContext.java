package galaxy.context;

import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.ShipGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ClassicGalaxyContext implements GameContext {

	private final List<Race> races;
	private final Map<String, Race> raceCache;

	private final List<Planet> planets;
	private final Map<String, Planet> planetCache;

	private final Map<Race, List<ShipGroup>> shipGroups = new HashMap<>();

	public ClassicGalaxyContext(List<Race> races, List<Planet> planets) {
		this.races = races;
		this.raceCache = toRaceCache(races);

		this.planets = planets;
		this.planetCache = toPlanetCache(planets);
	}

	private Map<String, Planet> toPlanetCache(List<Planet> planets) {
		return planets.stream().collect(toMap(p -> p.id().value(), p -> p));
	}

	private Map<String, Race> toRaceCache(List<Race> races) {
		return races.stream().collect(toMap(p -> p.id().value(), p -> p));
	}

	@Override
	public List<PlanetView> planetViews(Race race) {
		return List.of();
	}

	@Override
	public Race findRace(String id) {
		return raceCache.get(id);
	}

	@Override
	public Planet findPlanet(String id) {
		return planetCache.get(id);
	}

	@Override
	public List<Race> findRaces() {
		return List.copyOf(races);
	}

	@Override
	public List<ShipGroup> findShipGroups(Race race) {
		return shipGroups.get(race);
	}

	@Override
	public List<ShipGroup> findShipGroups(Race race, Planet planet) {
		return shipGroups.get(race).stream().filter(group -> group.currentPlanet().equals(planet)).toList();
	}

}
