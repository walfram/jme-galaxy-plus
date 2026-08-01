package galaxy.generator;

import com.simsilica.mathd.Vec3d;
import galaxy.core.Planet;
import galaxy.core.planet.DaughterWorld;
import galaxy.core.planet.HomeWorld;
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
		List<Vec3d> hwCoords = generateHomeWorldCoordinates();
		List<Planet> homeWorlds = createHomeWorlds(hwCoords);
		List<Planet> daughterWorlds = createDaughterWorlds(hwCoords);
		List<Planet> uninhabited = createUninhabitedPlanets(hwCoords);

		List<Planet> planets = new ArrayList<>(homeWorlds);
		planets.addAll(daughterWorlds);
		planets.addAll(uninhabited);
		return planets;
	}

	private List<Planet> createUninhabitedPlanets(List<Vec3d> hwCoords) {
		int uninhabitedPlanetCount = playerCount * planetRatio - 3 * playerCount;

		List<Planet> uninhabited = new ArrayList<>(uninhabitedPlanetCount);

		Map<PlanetType, Set<Vec3d>> positions = new HashMap<>();

		while (positions.values().stream().mapToInt(Set::size).sum() < uninhabitedPlanetCount) {
			PlanetType picked = distribution.pick(generator);
			Set<Vec3d> set = positions.computeIfAbsent(picked, k -> new HashSet<>());

			Set<Vec3d> combined = new HashSet<>(hwCoords);
			combined.addAll(set);

			GeneratedCoordinates coordinates = new GeneratedMinDistanceCoordinates(combined, picked.minDistance(), 1, generator);
			positions.get(picked).addAll(coordinates.coordinates());
		}

		for (Map.Entry<PlanetType, Set<Vec3d>> e : positions.entrySet()) {
			List<Planet> list = e.getValue().stream().map(c -> e.getKey().generate(planetIdx.incrementAndGet(), c, generator)).toList();
			uninhabited.addAll(list);
		}

		return uninhabited;
	}

	private List<Planet> createDaughterWorlds(List<Vec3d> hwCoords) {
		List<Planet> planets = new ArrayList<>(playerCount * 2);

		for (Vec3d c: hwCoords) {
			Planet a = new Planet(planetIdx.incrementAndGet(), c.x + dx(), c.y + dy(), 500.0, 10.0);
			a.putProperty(new DaughterWorld());

			Planet b = new Planet(planetIdx.incrementAndGet(), c.x + dx(), c.y + dy(), 500.0, 10.0);
			b.putProperty(new DaughterWorld());

			planets.add(a);
			planets.add(b);
		}

		return planets;
	}

	private List<Planet> createHomeWorlds(List<Vec3d> hwCoords) {
		List<Planet> hws = hwCoords.stream().map(c -> new Planet(planetIdx.incrementAndGet(), c.x, c.y, 1000.0, 10.0)).toList();
		hws.forEach(p -> p.putProperty(new HomeWorld()));
		return hws;
	}

	private List<Vec3d> generateHomeWorldCoordinates() {
		GeneratedCoordinates origins = new GeneratedMinDistanceCoordinates(List.of(), 30.0, playerCount, generator);
		return origins.coordinates();
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
