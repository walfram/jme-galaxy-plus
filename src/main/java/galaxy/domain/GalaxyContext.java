package galaxy.domain;

import galaxy.domain.planet.Planet;

import java.util.List;

public class GalaxyContext implements Galaxy {
	private final List<Race> races;
	private final List<Planet> planets;

	public GalaxyContext(List<Race> races, List<Planet> planets) {
		this.races = races;
		this.planets = planets;
	}

	@Override
	public PlanetInfo planetInfo(Race race, Planet planet) {
		if (race.planets().contains(planet))
			return new OwnedPlanet(planet);

//		if (race.monitoring(planet))
//			return new VisiblePlanet(planet);
//
//		if (race.visited(planet))
//			return new VisitedPlanet(planet);

		return new UnknownPlanet(planet);
	}
}
