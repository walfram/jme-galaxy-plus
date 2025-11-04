package galaxy.domain;

import galaxy.domain.planet.Planet;

import java.util.List;

public abstract class Galaxy {
	private final List<Race> races;
	private final List<Planet> planets;

	public Galaxy(List<Race> races, List<Planet> planets) {
		this.races = races;
		this.planets = planets;
	}
}
