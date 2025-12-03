package alt.doman;

import alt.doman.planet.Planet;

import java.util.*;

public class Race {

	private final String name;
	private final Map<Race, Diplomacy> diplomacy = new HashMap<>();

	private final List<Planet> planets = new ArrayList<>();
	private final List<ShipTemplate> shipTemplates = new ArrayList<>();

	public Race(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Race race = (Race) o;
		return Objects.equals(name, race.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	public Diplomacy diplomacyStatus(Race other) {
		return diplomacy.computeIfAbsent(other, k -> Diplomacy.WAR);
	}

	public String name() {
		return name;
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
	}

	public void claim(Planet planet) {
		planet.updateOwner(this);
		planets.add(planet);
	}

	public List<ShipTemplate> shipTemplates() {
		return List.copyOf(shipTemplates);
	}

	public void addShipTemplate(ShipTemplate shipTemplate) {
		shipTemplates.add(shipTemplate);
	}

}
