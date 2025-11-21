package galaxy.domain;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;

import java.util.List;

public class ClassicGalaxy implements Galaxy {
	public ClassicGalaxy(List<Race> races, List<Planet> planets) {

	}

	@Override
	public PlanetInfo planetInfo(Race race, Planet planet) {
		return null;
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
