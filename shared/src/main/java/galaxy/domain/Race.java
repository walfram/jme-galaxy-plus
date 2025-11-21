package galaxy.domain;

import galaxy.domain.planet.Planet;
import galaxy.domain.ship.Ship;
import galaxy.domain.ship.ShipTemplate;
import galaxy.domain.technology.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Race {

	private final String id;
	private final String name;

	private final Technologies technologies = new Technologies(new EnginesTechnology(), new WeaponsTechnology(), new ShieldsTechnology(), new CargoTechnology());

	private final List<Planet> planets = new ArrayList<>(128);
	private final List<Route> routes = new ArrayList<>(128);

	private final List<ShipTemplate> shipTemplates = new ArrayList<>(128);
	private final List<Ship> ships = new ArrayList<>(1024);

	public Race(String id, String name) {
		this(id, name, List.of());
	}

	public Race(String id, String name, List<Planet> initialPlanets) {
		this.id = id;
		this.name = name;
		this.planets.addAll(initialPlanets);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;

		if (getClass() != obj.getClass()) return false;

		Race other = (Race) obj;

		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass(), id);
	}

	public Technologies technologies() {
		return technologies;
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
	}

	public List<Route> routes() {
		return List.copyOf(routes);
	}

	public List<Ship> ships() {
		return List.copyOf(ships);
	}

	public List<ShipTemplate> shipTemplates() {
		return List.copyOf(shipTemplates);
	}

	public String name() {
		return name;
	}

	public String id() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

	// TODO temporary method, does not use Planet at the moment
	public void addShipGroup(int count, ShipTemplate shipTemplate, Planet planet) {
		List<Ship> group = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			group.add(shipTemplate.build(technologies()));
		}

		ships.addAll(group);
	}

	public void claim(Planet planet) {
		this.planets.add(planet);
		planet.updateOwner(this);
	}

	public Optional<Planet> ownedPlanet(Long planetId) {
		return planets.stream().filter(planet -> planet.id().equals(planetId)).findFirst();
	}

	public Optional<Planet> visiblePlanet(Long planetId) {
		return Optional.empty();
	}

	public Optional<Planet> visitedPlanet(Long planetId) {
		return Optional.empty();
	}

	public List<Planet> ownedPlanets() {
		return List.of();
	}
}
