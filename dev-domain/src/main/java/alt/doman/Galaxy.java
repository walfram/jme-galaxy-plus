package alt.doman;

import java.util.List;

public interface Galaxy {
	List<Race> races();

	List<Planet> planets();

	List<Planet> planetsOwned(Race race);

	List<Planet> planetsAll(Race race);

	List<Ship> ships(Race race);

	List<Planet> visiblePlanets(Race race);

	List<Planet> unknownPlanets(Race race);

	List<Planet> friendlyPlanets(Race race);

	List<Planet> hostilePlanets(Race race);

	List<Planet> visitedPlanets(Race race);

	void createShipTemplate(Race race, ShipTemplate template);

	List<ShipTemplate> shipTemplates(Race race);

	List<Ship> buildShips(Race race, Planet planet, ShipTemplate shipTemplate);

	double technologyLevel(Race race, Technology technology);

	double research(Race race, Planet planet, Technology tech);

	Science research(Race race, Planet planet, Science science);

	List<Ship> shipsAt(Race race, Planet planet);

	List<Ship> sendShips(Race race, Planet from, Planet to, List<Ship> ships);

	void produce(Race race, Planet planet, Production production);

	List<Ship> transfer(Race from, Race to, List<Ship> ships);

	List<Ship> upgrade(Race race, Planet planet, List<Ship> ships);

	Diplomacy diplomacyStatus(Race from, Race to);

	Diplomacy declare(Race from, Race to, Diplomacy diplomacy);

	Race ownerOf(Planet planet);

	void claimPlanet(Race race, Planet planet, Ship ship);
}
