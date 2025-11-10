package galaxy.generator.simple;

import com.jme3.math.Vector3f;
import galaxy.domain.Race;
import galaxy.domain.planet.ClassicDaughterWorld;
import galaxy.domain.planet.ClassicHomeWorld;
import galaxy.domain.planet.Coordinates;
import galaxy.domain.planet.Planet;
import galaxy.generator.*;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SimplePlanetGenerator implements PlanetGenerator {

	private static final Logger logger = getLogger(SimplePlanetGenerator.class);

	private static final double DISTANCE_HW = 30.0;
	private static final double DISTANCE_DW_MIN = 5.0;
	private static final double DISTANCE_DW_MAX = 10.0;

	private final Generator random;

	private final List<Race> races;
	private final int planetRatio;

	private final SeedSource seedSource;

	public SimplePlanetGenerator(Generator random, List<Race> races, int planetRatio, SeedSource seedSource) {
		this.random = random;
		this.races = races;
		this.planetRatio = planetRatio;
		this.seedSource = seedSource;
	}

	int planetCount() {
		return races.size() * planetRatio;
	}

	@Override
	public List<Planet> planets() {
		List<Vector3f> origins = new ArrayList<>(races.size());

		List<Vector3f> seed = new ArrayList<>(seedSource.points());
		for (int i = 0; i < races.size(); i++) {
			Vector3f origin = random.pick(seed);

			seed.remove(origin);
			origins.add(origin);

			List<Vector3f> remove = seed.stream().filter(p -> p.distance(origin) <= DISTANCE_HW).toList();
			seed.removeAll(remove);
		}

		logger.debug("planet origins: {}", origins.size());

		List<Planet> planets = new ArrayList<>(planetCount());

		int planetIndex = 0;
		Iterator<Race> raceSource = races.iterator();

		for (Vector3f origin : origins) {
			Race race = raceSource.next();

			ClassicHomeWorld hw = new ClassicHomeWorld("hw-%s".formatted(planetIndex++), new Coordinates(origin));
			planets.add(hw);
			race.claim(hw);

			Vector3f dw1Origin = random
					.nextUnitVector3f()
					.multLocal((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX))
					.addLocal(origin);

			ClassicDaughterWorld dw1 = new ClassicDaughterWorld("dw-%s-1".formatted(planetIndex), new Coordinates(dw1Origin));
			planets.add(dw1);
			race.claim(dw1);

			Vector3f dw2Origin = random
					.nextUnitVector3f()
					.multLocal((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX))
					.addLocal(origin);

			ClassicDaughterWorld dw2 = new ClassicDaughterWorld("dw-%s-2".formatted(planetIndex), new Coordinates(dw2Origin));
			planets.add(dw2);
			race.claim(dw2);
		}

		PlanetNameSource planetNameSource = new SizeSuffixNameSource();
		WeightedDistribution<PlanetTemplate> distribution = new SimplePlanetDistribution();
		int remaining = planetCount() - planets.size();

		for (int i = 0; i < remaining; i++) {
			PlanetTemplate template = distribution.pick(random);

			// convert existing planets to List<Vector3f>
			List<Vector3f> coordinates = planets.stream().map(planet -> planet.coordinates().asVector3f()).toList();

			// copy seedSource
			List<Vector3f> seedSourceCopy = new ArrayList<>(seedSource.points());

			// remove from seedSource based on existing planets and template.minDistance
			List<Vector3f> filtered = seedSourceCopy.stream().filter(v -> coordinates.stream().noneMatch(c -> c.distance(v) > template.minDistance())).toList();
			seedSourceCopy.removeAll(filtered);

			// pick random point - use as planet origin
			Vector3f picked = random.pick(seedSourceCopy);
			Planet planet = template.createAtCoordinates(picked, random, planetNameSource);
			planets.add(planet);
		}

		logger.debug("planets: {}", planets.size());

		return planets;
	}

}
