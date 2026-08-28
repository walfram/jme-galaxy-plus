package fixtures;

import galaxy.*;
import galaxy.planet.Planet;
import galaxy.planet.PlanetProperty;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

import java.util.*;

public class TestGameContext implements GameContext {

	private final List<Race> races = List.of(
			new Race("foo"), new Race("bar"), new Race("baz")
	);

	private final List<Planet> planets = new TestPlanets(races.size()).asList();

	private final Map<Planet, Race> planetOwnership = new HashMap<>();

	private final Map<Planet, Production> productions = new HashMap<>();

	private final Set<ShipGroup> shipGroups = new HashSet<>();

	public TestGameContext() {
		int i = 0;
		for (Race race: races) {
			changeOwner(race, planets.get(i));
			changeOwner(race, planets.get(i + 1));
			changeOwner(race, planets.get(i + 2));
			i += 3;
		}
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
	public Race findRace(String id) {
		return races.stream().filter(race -> race.id().equals(new Id(id))).findFirst().orElseThrow();
	}

	@Override
	public void changeOwner(Race race, Planet planet) {
		planetOwnership.put(planet, race);
	}

	@Override
	public List<Planet> findPlanets(Race race, Class<? extends PlanetProperty> planetPropertyClass) {
		return planets
				.stream()
				.filter(planet -> race.equals(planetOwnership.get(planet)))
				.filter(planet -> planet.property(planetPropertyClass).isPresent())
				.toList();
	}

	@Override
	public Planet findPlanet(Id id) {
		return planets.stream().filter(p -> p.id().equals(id)).findFirst().orElseThrow();
	}

	@Override
	public void createShipType(Race race, ShipType shipType) {

	}

	@Override
	public void createProduction(Race race, Planet planet, Production production) {
		Race check = planetOwnership.get(planet);

		if (!Objects.equals(check, race))
			throw new IllegalArgumentException("Race %s is not owner of planet %s".formatted(race.id(), planet.id()));

		productions.put(planet, production);
	}

	@Override
	public List<ShipType> findShipTypes(Race race) {
		return List.of();
	}

	@Override
	public TechLevels findTechLevels(Race race) {
		return null;
	}

	@Override
	public void updateTechLevels(Race race, TechLevels techLevels) {

	}

	@Override
	public List<ShipGroup> findShipGroups(Race race, Planet planet) {
		return List.of();
	}

	@Override
	public List<ShipGroup> findShipGroups(Planet planet) {
		return List.of();
	}

	@Override
	public ShipGroup findShipGroup(Id id) {
		return shipGroups.stream().filter(group -> group.id().equals(id)).findFirst().orElseThrow();
	}

	@Override
	public void createShipGroup(Race race, Planet planet, ShipGroup shipGroup) {
		shipGroups.add(shipGroup);
	}

	@Override
	public void removeShipGroup(ShipGroup shipGroup) {

	}

	@Override
	public List<Production> findProductions() {
		return productions.values().stream().toList();
	}
}
