package galaxy.domain;

import galaxy.domain.planet.PlanetRef;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Context {
	long shipCount(String team);

	long planetCount(String team);

	List<Entity> query(Collection<Class<? extends Component>> components);

	void remove(Collection<Entity> discarded);

	Map<PlanetRef, Entity> planets();

	Entity createPlanet();
}
