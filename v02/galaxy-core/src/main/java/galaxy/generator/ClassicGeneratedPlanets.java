package galaxy.generator;

import galaxy.core.Id;
import galaxy.core.Planet;
import galaxy.core.planet.DaughterWorld;
import galaxy.core.planet.HomeWorld;
import galaxy.generator.partition.SpacePartition2d;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class ClassicGeneratedPlanets implements GeneratedPlanets {

	private static final Logger logger = getLogger(ClassicGeneratedPlanets.class);

	private final int playerCount;
	private final int planetRatio;
	private final Generator generator;

	private final WeightedDistribution<PlanetType> distribution = new ClassicPlanetDistribution();

	public ClassicGeneratedPlanets(int playerCount, int planetRatio, long seed) {
		this.playerCount = playerCount;
		this.planetRatio = planetRatio;
		this.generator = new Generator(seed);
	}

	@Override
	public List<Planet> planets() {
		Collection<SpacePartition2d.Cell2d> homeWorldCells = generateHomeWorldCoordinates();
		logger.info("generated homeworld cells {}", homeWorldCells.size());

		List<Planet> homeWorlds = createHomeWorlds(homeWorldCells);
		logger.info("created homeworlds {}", homeWorlds.size());

		List<Planet> daughterWorlds = createDaughterWorlds(homeWorlds);
		logger.info("created daughter worlds {}", daughterWorlds.size());

		List<Planet> uninhabited = createUninhabitedPlanets(homeWorldCells);
		logger.info("created uninhabited planets {}", uninhabited.size());

		List<Planet> planets = new ArrayList<>(homeWorlds);
		planets.addAll(daughterWorlds);
		planets.addAll(uninhabited);

		logger.info("total planets {}", planets.size());

		return planets;
	}

	private List<Planet> createUninhabitedPlanets(Collection<SpacePartition2d.Cell2d> homeWorldCells) {
		int uninhabitedPlanetCount = playerCount * planetRatio - 3 * playerCount;

		List<Planet> uninhabited = new ArrayList<>(uninhabitedPlanetCount);

		Map<PlanetType, Set<SpacePartition2d.Cell2d>> positions = new HashMap<>();

		while (positions.values().stream().mapToInt(Set::size).sum() < uninhabitedPlanetCount) {
			PlanetType picked = distribution.pick(generator);
			Set<SpacePartition2d.Cell2d> set = positions.computeIfAbsent(picked, k -> new HashSet<>());

			HashSet<SpacePartition2d.Cell2d> combined = new HashSet<>(homeWorldCells);
			combined.addAll(set);

			GeneratedMinDistanceCells cellsSource = new GeneratedMinDistanceCells(
					picked.minDistance(),
					1,
					combined.stream().map(SpacePartition2d.Cell2d::centerAsVector3f).toList()
			);

			positions.get(picked).addAll(cellsSource.cells());
		}

		for (Map.Entry<PlanetType, Set<SpacePartition2d.Cell2d>> e : positions.entrySet()) {
			List<Planet> list = e.getValue().stream()
					.map(c -> e.getKey().generate(new Id(UUID.randomUUID()), c.toRandomVector3f(generator), generator))
					.toList();

			uninhabited.addAll(list);
		}

		return uninhabited;
	}

	private List<Planet> createDaughterWorlds(List<Planet> homeWorlds) {
		List<Planet> planets = new ArrayList<>(playerCount * 2);

		for (Planet p : homeWorlds) {
			Planet a = new Planet(new Id(UUID.randomUUID()), p.x() + dx(), p.y() + dy(), 500.0, 10.0);
			a.putProperty(new DaughterWorld());

			Planet b = new Planet(new Id(UUID.randomUUID()), p.x() + dx(), p.y() + dy(), 500.0, 10.0);
			b.putProperty(new DaughterWorld());

			planets.add(a);
			planets.add(b);
		}

		return planets;
	}

	private List<Planet> createHomeWorlds(Collection<SpacePartition2d.Cell2d> homeWorldCells) {
		List<Planet> hws = homeWorldCells.stream()
				.map(c -> c.toRandomVector3f(generator))
				.map(v -> new Planet(new Id(UUID.randomUUID()), v.x, v.y, 1000.0, 10.0))
				.toList();

		hws.forEach(p -> p.putProperty(new HomeWorld()));

		return hws;
	}

	private Collection<SpacePartition2d.Cell2d> generateHomeWorldCoordinates() {
		// List.of(), 30.0, playerCount, generator
		GeneratedMinDistanceCells origins = new GeneratedMinDistanceCells(30.0, playerCount, List.of());
		return origins.cells();
	}

	private double dx() {
		double theta = generator.nextDouble() * Math.PI * 2;
		return generator.nextDouble(5.0, 10.0) * Math.cos(theta);
	}

	private double dy() {
		double theta = generator.nextDouble() * Math.PI * 2;
		return generator.nextDouble(5.0, 10.0) * Math.sin(theta);
	}

}
