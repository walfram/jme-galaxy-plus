package galaxy.context;

import galaxy.core.*;

import java.util.List;

public interface GameContext {
	List<PlanetView> planetViews(Race race);

	Race findRace(String id);

	Planet findPlanet(String id);

	List<Race> findRaces();

	List<ShipGroup> findShipGroups(Race race);

	List<ShipGroup> findShipGroups(Race race, Planet planet);
}
