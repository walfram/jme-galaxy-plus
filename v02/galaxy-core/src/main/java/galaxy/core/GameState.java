package galaxy.core;

import java.util.List;
import java.util.Map;

public interface GameState {

	Race findRace(Id id);

	List<Race> races();

	List<Planet> planets();

//	ShipGroup shipGroup(int id);

//	Map<Integer, ShipGroup> shipGroups();

	List<Planet> racePlanets(Id raceId);

	void colonizePlanet(Race race, Planet planet);

	Planet findPlanet(Id planetId);

//	void createShipGroup(Race race, ShipGroup shipGroup);
}
