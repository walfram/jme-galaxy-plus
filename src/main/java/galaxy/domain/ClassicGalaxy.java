package galaxy.domain;

import galaxy.domain.planet.Planet;

import java.util.List;

public class ClassicGalaxy implements Galaxy {
	public ClassicGalaxy(List<Race> races, List<Planet> planets) {

	}

	@Override
	public PlanetInfo planetInfo(Race race, Planet planet) {
		return null;
	}
}
