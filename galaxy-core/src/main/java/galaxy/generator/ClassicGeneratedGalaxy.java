package galaxy.generator;

import galaxy.core.Planet;
import galaxy.core.planet.DaughterWorld;
import galaxy.core.planet.HomeWorld;
import galaxy.generator.partition.SpacePartition2d;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class ClassicGeneratedGalaxy implements GeneratedPlanets {

	private static final Logger logger = getLogger(ClassicGeneratedGalaxy.class);

	private final int playerCount;
	private final int planetRatio;
	private final Generator generator;

	private final WeightedDistribution<PlanetType> distribution = new ClassicPlanetDistribution();

	private final AtomicInteger planetIdx = new AtomicInteger(0);

	public ClassicGeneratedGalaxy(int playerCount, int planetRatio, long seed) {
		this.playerCount = playerCount;
		this.planetRatio = planetRatio;
		this.generator = new Generator(seed);
	}

	@Override
	public List<Planet> planets() {
		Collection<SpacePartition2d.Cell2d> hwCoords = generateHomeWorldCoordinates();
		List<Planet> homeWorlds = createHomeWorlds(hwCoords);
		List<Planet> daughterWorlds = createDaughterWorlds(homeWorlds);
		List<Planet> uninhabited = createUninhabitedPlanets(hwCoords);

		List<Planet> planets = new ArrayList<>(homeWorlds);
		planets.addAll(daughterWorlds);
		planets.addAll(uninhabited);

		return planets;
	}

	private List<Planet> createUninhabitedPlanets(Collection<SpacePartition2d.Cell2d> hwCoords) {
		int uninhabitedPlanetCount = playerCount * planetRatio - 3 * playerCount;

		List<Planet> uninhabited = new ArrayList<>(uninhabitedPlanetCount);

		Map<PlanetType, Set<SpacePartition2d.Cell2d>> positions = new HashMap<>();

		while (positions.values().stream().mapToInt(Set::size).sum() < uninhabitedPlanetCount) {
			PlanetType picked = distribution.pick(generator);
			Set<SpacePartition2d.Cell2d> set = positions.computeIfAbsent(picked, k -> new HashSet<>());

			HashSet<SpacePartition2d.Cell2d> combined = new HashSet<>(hwCoords);
			combined.addAll(set);

			GeneratedMinDistanceCells coordinates = new GeneratedMinDistanceCells(
					picked.minDistance(),
					1,
					combined.stream().map(SpacePartition2d.Cell2d::centerAsVector3f).toList()
			);

			positions.get(picked).addAll(coordinates.cells());
		}

		for (Map.Entry<PlanetType, Set<SpacePartition2d.Cell2d>> e : positions.entrySet()) {
			List<Planet> list = e.getValue().stream()
					.map(c -> e.getKey().generate(planetIdx.incrementAndGet(), c.toRandomVector3f(generator), generator))
					.toList();

			uninhabited.addAll(list);
		}

		return uninhabited;
	}

	private List<Planet> createDaughterWorlds(List<Planet> homeWorlds) {
		List<Planet> planets = new ArrayList<>(playerCount * 2);

		for (Planet p : homeWorlds) {
			Planet a = new Planet(planetIdx.incrementAndGet(), p.x() + dx(), p.y() + dy(), 500.0, 10.0);
			a.putProperty(new DaughterWorld());

			Planet b = new Planet(planetIdx.incrementAndGet(), p.x() + dx(), p.y() + dy(), 500.0, 10.0);
			b.putProperty(new DaughterWorld());

			planets.add(a);
			planets.add(b);
		}

		return planets;
	}

	private List<Planet> createHomeWorlds(Collection<SpacePartition2d.Cell2d> hwCoords) {
		List<Planet> hws = hwCoords.stream()
				.map(c -> c.toRandomVector3f(generator))
				.map(v -> new Planet(planetIdx.incrementAndGet(), v.x, v.y, 1000.0, 10.0))
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
