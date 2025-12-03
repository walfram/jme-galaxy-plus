package alt.doman;

import java.util.List;

public interface Galaxy {
	List<Race> races();
	List<Planet> planets();

	List<Planet> racePlanets(Race race);

	List<Planet> planetsAll(Race race);

	List<Planet> knownPlanets(Race race);

	List<Planet> unknownPlanets(Race race);

	List<Planet> friendlyPlanets(Race race);

	List<Planet> hostilePlanets(Race race);

	List<ShipTemplate> shipTemplates(Race race);

	List<Ship> ships(Race race);
	List<Ship> shipsAtPlanet(Race race, Planet planet);

	TechnologyLevel technologyLevel(Race race);
	List<Science> sciences(Race race);

	Diplomacy diplomacyStatus(Race from, Race to);

	void execute(Command command);

}
