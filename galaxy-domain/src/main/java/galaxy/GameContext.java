package galaxy;

import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

import java.util.List;

public interface GameContext {
	List<Race> races();

	List<Planet> planets();

	Race findRace(String id);

	void changeOwner(Race race, Planet planet);

	Planet findPlanet(Id id);

	void createShipType(Race race, ShipType shipType);

	void createProduction(Race race, Planet planet, Production production);

	List<ShipType> findShipTypes(Race race);

	TechLevels findTechLevels(Race race);

	void updateTechLevels(Race race, TechLevels techLevels);

	List<ShipGroup> findShipGroups(Race race, Planet planet);

	List<ShipGroup> findShipGroups(Planet planet);

	ShipGroup findShipGroup(Id id);

	void createShipGroup(Race race, Planet planet, ShipGroup shipGroup);

	void removeShipGroup(ShipGroup shipGroup);

	List<Production> findProductions(Race race);

	ShipType findShipType(Race race, String name);

	List<ShipGroup> findShipGroups(Race race, ShipType shipType);
}
