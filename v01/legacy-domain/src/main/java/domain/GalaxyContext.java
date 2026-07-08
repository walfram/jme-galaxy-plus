package domain;

import domain.planet.Planet;
import domain.planet.PlanetInfo;
import domain.planet.info.UnknownPlanet;
import domain.planet.info.VisiblePlanet;

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
		if (race.ownedPlanets().contains(planet))
			return new VisiblePlanet(planet);

//		if (race.monitoring(planet))
//			return new VisiblePlanet(planet);
//
//		if (race.visited(planet))
//			return new VisitedPlanet(planet);

		return new UnknownPlanet(planet);
	}

	@Override
	public List<Race> races() {
		return List.of();
	}

	@Override
	public Race findRaceByName(String raceId) {
		return null;
	}

	@Override
	public Race findRaceById(long id) {
		return null;
	}
}
