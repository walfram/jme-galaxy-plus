package domain;

import domain.planet.*;
import domain.ship.*;
import domain.ship.state.InOrbit;
import domain.team.Diplomacy;
import domain.team.GalaxyView;
import domain.team.Team;
import domain.team.TeamRef;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class ClassicGalaxy implements Context {

	private static final Logger logger = getLogger(ClassicGalaxy.class);

	private final AtomicLong planetIdSource = new AtomicLong(0);
	private final AtomicLong shipIdSource = new AtomicLong(0);

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
				e -> e.prop(TeamRef.class),
				e -> e
		));
	}

	@Override
	public Entity createTeam(String name) {
		Entity team = new Entity();

		team.put(new Team());
		team.put(new TeamRef(name));
		team.put(new GalaxyView());
		team.put(new Diplomacy());

		entities.add(team);

		return team;
	}

	@Override
	public Entity createHomeWorld(Entity team) {
		Entity planet = createPlanet();

		planet.put(new Position());
		planet.put(new Size(1000.0));
		planet.put(new Resources(10.0));
		planet.put(new HomeWorldTag());
		planet.put(new Population(1000.0));
		planet.put(new Industry(1000.0));

		team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.OWNED);
		planet.put(new TeamRef(team.prop(TeamRef.class)));

		return planet;
	}

	@Override
	public Entity createDaughterWorld(Entity team) {
		Entity planet = createPlanet();

		planet.put(new Position());
		planet.put(new Size(500.0));
		planet.put(new Resources(10.0));
		planet.put(new DaughterWorld());
		planet.put(new Population(500.0));
		planet.put(new Industry(500.0));

		team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.OWNED);
		planet.put(new TeamRef(team.prop(TeamRef.class)));

		return planet;
	}

	@Override
	public Entity createUninhabitedPlanet() {
		Entity planet = createPlanet();

		teams().values().forEach(team -> team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.UNKNOWN));

		return planet;
	}

	@Override
	public Entity createEntity() {
		Entity entity = new Entity();
		entities.add(entity);
		return entity;
	}

	@Override
	public Entity createShip(PlanetRef planetRef, TeamRef teamRef, ShipDesign shipDesign, TechLevel techLevel) {
		Entity ship = new Entity();

		ship.put(planetRef);
		ship.put(teamRef);
		ship.put(shipDesign);
		ship.put(techLevel);

		// TODO add Engines, Weapons, Shields, Cargo
		if (shipDesign.engines() >= 1) {
			ship.put(new Engines(shipDesign.engines()));
		}

		if (shipDesign.guns() >= 1 && shipDesign.caliber() >= 1) {
			ship.put(new Weapons(shipDesign.guns(), shipDesign.caliber()));
		}

		if (shipDesign.shields() >= 1) {
			ship.put(new Shields(shipDesign.shields()));
		}

		if (shipDesign.cargo() >= 1) {
			ship.put(new CargoHold(Cargo.Empty, shipDesign.cargo(), 0));
		}

		ship.put(new ShipId(shipIdSource.incrementAndGet()));
		ship.put(new InOrbit());

		entities.add(ship);
		return ship;
	}

	@Override
	public Entity team(TeamRef teamRef) {
		return entities.stream()
				.filter(e -> e.has(Team.class))
				.filter(e -> Objects.equals(teamRef, e.prop(TeamRef.class)))
				.findFirst().orElseThrow();
	}

	@Override
	public Entity planet(PlanetRef planetRef) {
		return entities.stream()
				.filter(e -> e.has(PlanetRef.class))
				.filter(e -> Objects.equals(planetRef, e.prop(PlanetRef.class)))
				.findFirst().orElseThrow();
	}

	@Override
	public Map<ShipId, Entity> ships() {
		List<Entity> ships = query(List.of(ShipId.class));
		return ships.stream().collect(Collectors.toMap(e -> e.prop(ShipId.class), e -> e));
	}

}
