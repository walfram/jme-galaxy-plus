package galaxy.domain;

import galaxy.domain.planet.ClassicHomeWorld;
import galaxy.domain.planet.Planet;
import galaxy.domain.ship.ShipTemplate;
import galaxy.domain.technology.*;

import java.util.ArrayList;
import java.util.List;

public class Race {

	private final String id;
	private final String name;

	private final List<Technology> technologies = List.of(
			new DriveTechnology(), new WeaponsTechnology(), new ShieldsTechnology(), new CargoTechnology()
	);

	private final List<Planet> planets = new ArrayList<>(128);
	private final List<Route> routes = new ArrayList<>(128);

	private final List<ShipTemplate> shipTemplates = new ArrayList<>(128);
	private final List<Ship> ships = new ArrayList<>(1024);


	public Race(String id, String name, List<Planet> initialPlanets) {
		this.id = id;
		this.name = name;
		this.planets.addAll(initialPlanets);
	}

	public List<Technology> technologies() {
		return List.copyOf(technologies);
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
}
