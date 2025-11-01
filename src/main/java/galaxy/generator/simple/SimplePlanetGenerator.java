package galaxy.generator.simple;

import com.jme3.math.Vector3f;
import galaxy.domain.*;
import galaxy.generator.PlanetGenerator;
import galaxy.generator.PlanetTemplate;
import galaxy.generator.SeedSource;
import galaxy.generator.WeightedDistribution;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SimplePlanetGenerator implements PlanetGenerator {

	private static final Logger logger = getLogger(SimplePlanetGenerator.class);

	private static final double DISTANCE_HW = 30.0;
	private static final double DISTANCE_DW_MIN = 5.0;
	private static final double DISTANCE_DW_MAX = 10.0;

	private final Generator random;

	private final int raceCount;
	private final int planetRatio;

	private final SeedSource seedSource;

	public SimplePlanetGenerator(Generator random, int raceCount, int planetRatio, SeedSource seedSource) {
		this.random = random;
		this.raceCount = raceCount;
		this.planetRatio = planetRatio;
		this.seedSource = seedSource;
	}

//	public SimplePlanetGenerator() {
//		this(
//				new Generator(42),
//				10,
//				10,
//				new SimpleSeedSource()
//		);
//	}

	int planetCount() {
		return raceCount * planetRatio;
	}

	@Override
	public List<Planet> planets() {
		List<Vector3f> origins = new ArrayList<>(raceCount);

		List<Vector3f> seed = new ArrayList<>(seedSource.points());
		for (int i = 0; i < raceCount; i++) {
			Vector3f origin = random.pick(seed);

			seed.remove(origin);
			origins.add(origin);

			List<Vector3f> remove = seed.stream().filter(p -> p.distance(origin) <= DISTANCE_HW).toList();
			seed.removeAll(remove);
		}

		logger.debug("planet origins: {}", origins.size());

		List<Planet> planets = new ArrayList<>(planetCount());

		int planetIndex = 0;
		for (Vector3f origin : origins) {
			planets.add(new Planet(
					"hw-%s".formatted(planetIndex++),
					new Coordinates(origin),
					new Size(1000),
					new Resources(10),
					new Population(1000),
					new Industry(1000),
					new Materials(0)
			));

			Vector3f dw1Origin = random
					.nextVector3f()
					.multLocal((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX))
					.addLocal(origin);

			planets.add(new Planet(
					"dw-%s-1".formatted(planetIndex),
					new Coordinates(dw1Origin),
					new Size(500),
					new Resources(10),
					new Population(500),
					new Industry(500),
					new Materials(0)
			));

			Vector3f dw2Origin = random
					.nextVector3f()
					.multLocal((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX))
					.addLocal(origin);

			planets.add(new Planet(
					"dw-%s-2".formatted(planetIndex),
					new Coordinates(dw2Origin),
					new Size(500),
					new Resources(10),
					new Population(500),
					new Industry(500),
					new Materials(0)
			));
		}

		WeightedDistribution<PlanetTemplate> distribution = new SimplePlanetDistribution();
		int remaining = planetCount() - planets.size();
		for (int i = 0; i < remaining; i++) {
			PlanetTemplate template = distribution.pick(random);
			// convert existing planets to List<Vector3f>
			List<Vector3f> coordinates = planets.stream().map(planet -> planet.coordinates().asVector3f()).toList();
			// copy seedSource
			List<Vector3f> seedSourceCopy = new ArrayList<>(seedSource.points());
			// remove from seedSource based on existing planets and template.minDistance
			seedSourceCopy.removeIf(v -> coordinates.stream().anyMatch(c -> c.distance(v) <= template.minDistance()));

			// pick random point - use as planet origin
			Vector3f picked = random.pick(seedSourceCopy);
			Planet planet = template.createAtCoordinates(picked, random);
			planets.add(planet);
		}

		logger.debug("planets: {}", planets.size());

		return planets;
	}

}
