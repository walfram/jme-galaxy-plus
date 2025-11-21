package galaxy.domain;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;

import java.util.List;

public interface Galaxy {

	PlanetInfo planetInfo(Race race, Planet planet);

	List<Race> races();

	Race findRaceByName(String raceId);

	Race findRaceById(long id);

}
