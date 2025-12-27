package domain;

import domain.planet.PlanetRef;
import domain.ship.ShipDesign;
import domain.ship.ShipId;
import domain.ship.TechLevel;
import domain.team.TeamRef;

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
