package galaxy.core;

import galaxy.core.planet.PlanetRef;
import galaxy.core.ship.ShipDesign;
import galaxy.core.ship.ShipId;
import galaxy.core.ship.TechLevel;
import galaxy.core.team.TeamRef;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Context {
	long shipCount(String team);

	long planetCount(String team);

	List<Entity> query(Collection<Class<? extends Component>> components);

	void remove(Collection<Entity> discarded);

	Map<PlanetRef, Entity> planets();

	Map<TeamRef, Entity> teams();

	Entity createTeam(String name);

	Entity createHomeWorld(Entity team);

	Entity createDaughterWorld(Entity team);

	Entity createUninhabitedPlanet();

	Entity createEntity();

	Entity createShip(PlanetRef planetRef, TeamRef teamRef, ShipDesign shipDesign, TechLevel techLevel);

	Entity team(TeamRef teamRef);

	Entity planet(PlanetRef planetRef);

	Map<ShipId, Entity> ships();

}
