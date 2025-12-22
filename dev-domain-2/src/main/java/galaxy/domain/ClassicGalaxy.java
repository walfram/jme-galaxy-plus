package galaxy.domain;

import galaxy.domain.planet.*;
import galaxy.domain.ship.ShipId;
import galaxy.domain.team.Team;
import galaxy.domain.team.TeamRef;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class ClassicGalaxy implements Context {

	private static final Logger logger = getLogger(ClassicGalaxy.class);

	private final AtomicLong planetIdSource = new AtomicLong(0);
	private final List<Entity> entities = new ArrayList<>();
	private final Map<TeamRef, TeamGalaxyView> galaxyViews = new HashMap<>();

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

	private Entity createPlanet() {
		Entity entity = new Entity();

		entity.put(new PlanetRef(planetIdSource.incrementAndGet()));
		entity.put(new Planet());

		entities.add(entity);

		return entity;
	}

	@Override
	public Map<TeamRef, Entity> teams() {
		List<Entity> teams = query(List.of(Team.class));

		return teams.stream().collect(Collectors.toMap(
				e -> e.prop(Team.class).teamRef(),
				e -> e
		));
	}

	@Override
	public Entity createTeam(String name) {
		Entity team = new Entity();
		team.put(new Team(name));
		entities.add(team);

		galaxyViews.put(new TeamRef( team.prop(Team.class).teamRef() ), new TeamGalaxyView());

		return team;
	}

	@Override
	public Entity createHomeWorld(TeamRef teamRef) {
		Entity planet = createPlanet();

		planet.put(new Position());
		planet.put(new Size(1000.0));
		planet.put(new Resources(10.0));
		planet.put(new HomeWorldTag());
		planet.put(new Population(1000.0));
		planet.put(new Industry(1000.0));

		return updateVisibility(teamRef, planet);
	}

	@Override
	public Entity createDaughterWorld(TeamRef teamRef) {
		Entity planet = createPlanet();

		planet.put(new Position());
		planet.put(new Size(500.0));
		planet.put(new Resources(10.0));
		planet.put(new DaughterWorld());
		planet.put(new Population(500.0));
		planet.put(new Industry(500.0));

		return updateVisibility(teamRef, planet);
	}

	private Entity updateVisibility(TeamRef teamRef, Entity planet) {
		planet.put(new TeamRef(teamRef.value()));

		galaxyViews.get(teamRef).updateVisibility(planet, PlanetVisibility.OWNED);
		galaxyViews.keySet().stream()
				.filter(tr -> !Objects.equals(teamRef, tr))
				.forEach(tr -> galaxyViews.get(tr).updateVisibility(planet, PlanetVisibility.UNKNOWN));

		return planet;
	}

	@Override
	public Entity createUninhabitedPlanet() {
		Entity uninhabited = createPlanet();

		for (TeamRef teamRef : teams().keySet()) {
			galaxyViews.get(teamRef).updateVisibility(uninhabited, PlanetVisibility.UNKNOWN);
		}

		return uninhabited;
	}

	@Override
	public TeamGalaxyView galaxyView(TeamRef teamRef) {
		return galaxyViews.get(teamRef);
	}

	@Override
	public Entity createEntity() {
		Entity entity = new Entity();
		entities.add(entity);
		return entity;
	}

}
