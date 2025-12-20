package galaxy.domain;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.ShipId;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ClassicGalaxy implements Context {

	private final AtomicLong idGenerator = new AtomicLong(0);
	private final List<Entity> entities = new ArrayList<>();

	public ClassicGalaxy(Entity... source) {
		this(List.of(source));
	}

	public ClassicGalaxy(List<Entity> source) {
		this.entities.addAll(source);
	}

	@Override
	public long shipCount(String team) {
		return entities.stream()
				.filter(e -> e.has(ShipId.class))
				.filter(e -> Objects.equals(new TeamRef(team), e.prop(TeamRef.class)))
				.count();
	}

	@Override
	public long planetCount(String team) {
		return entities.stream()
				.filter(e -> e.has(PlanetRef.class))
				.filter(e -> Objects.equals(new TeamRef(team), e.prop(TeamRef.class)))
				.count();
	}

	@Override
	public List<Entity> query(Collection<Class<? extends Component>> components) {
		return entities.stream().filter(e -> components.stream().allMatch(e::has)).toList();
	}

	@Override
	public void remove(Collection<Entity> discarded) {
		entities.removeAll(discarded);
	}

	@Override
	public Map<PlanetRef, Entity> planets() {
		List<Entity> planets = query(List.of(PlanetRef.class, Planet.class));

		return planets.stream().collect(Collectors.toMap(
				e -> e.prop(PlanetRef.class),
				e -> e
		));
	}

	@Override
	public Entity createPlanet() {
		Entity entity = new Entity();

		entity.put(new PlanetRef(idGenerator.incrementAndGet()));
		entity.put(new Planet());

		entities.add(entity);
		return entity;
	}

}
