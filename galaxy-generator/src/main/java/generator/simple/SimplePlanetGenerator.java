package generator.simple;

import com.jme3.math.Vector3f;
import galaxy.core.Entity;
import galaxy.core.planet.Coordinates;
import galaxy.core.planet.Planet;
import galaxy.core.planet.PlanetRef;
import generator.*;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.slf4j.LoggerFactory.getLogger;

@Deprecated
public class SimplePlanetGenerator implements PlanetGenerator {

	private static final Logger logger = getLogger(SimplePlanetGenerator.class);

	private static final double DISTANCE_HW = 30.0;
	private static final double DISTANCE_DW_MIN = 5.0;
	private static final double DISTANCE_DW_MAX = 10.0;

	private final Generator random;

	private final List<Entity> races;
	private final int planetRatio;

	private final SeedSource seedSource;

	public SimplePlanetGenerator(Generator random, List<Entity> races, int planetRatio, SeedSource seedSource) {
		this.random = random;
		this.races = races;
		this.planetRatio = planetRatio;
		this.seedSource = seedSource;
	}

	public int planetCount() {
		return races.size() * planetRatio;
	}

	@Override
	public List<Entity> planets() {
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

		List<Entity> planets = new ArrayList<>(planetCount());

		int planetIndex = 0;
		Iterator<Entity> raceSource = races.iterator();
		AtomicLong idSource = new AtomicLong(0);

		for (Vector3f origin : origins) {
			Entity race = raceSource.next();

			Entity hw = new Entity(new PlanetRef(idSource.incrementAndGet()), new Planet(), new Coordinates(origin.x, origin.y, origin.z));
			planets.add(hw);
			// race.claim(hw);

			Vector3f dw1Origin = random
					.nextUnitVector3f()
					.multLocal((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX))
					.addLocal(origin);

			Entity dw1 = new Entity(new PlanetRef(idSource.incrementAndGet()), new Planet(), new Coordinates(dw1Origin.x, dw1Origin.y, dw1Origin.z));
			planets.add(dw1);
			// race.claim(dw1);

			Vector3f dw2Origin = random
					.nextUnitVector3f()
					.multLocal((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX))
					.addLocal(origin);

			Entity dw2 = new Entity(new PlanetRef(idSource.incrementAndGet()), new Planet(), new Coordinates(dw2Origin.x, dw2Origin.y, dw2Origin.z));
			planets.add(dw2);
			// race.claim(dw2);
		}

		PlanetNameSource planetNameSource = new SizeSuffixNameSource();
		WeightedDistribution<PlanetTemplate> distribution = new SimplePlanetDistribution();
		int remaining = planetCount() - planets.size();

		for (int i = 0; i < remaining; i++) {
			PlanetTemplate template = distribution.pick(random);

			// convert existing planets to List<Vector3f>
			List<Vector3f> coordinates = planets.stream()
					.map(planet -> planet.prop(Coordinates.class))
					.map(c -> new Vector3f((float) c.x(), (float) c.y(), (float) c.z()))
					.toList();

			// copy seedSource
			List<Vector3f> seedSourceCopy = new ArrayList<>(seedSource.points());

			// remove from seedSource based on existing planets and template.minDistance
			List<Vector3f> filtered = seedSourceCopy.stream().filter(v -> coordinates.stream().noneMatch(c -> c.distance(v) > template.minDistance())).toList();
			seedSourceCopy.removeAll(filtered);

			// pick random point - use as planet origin
			Vector3f picked = random.pick(seedSourceCopy);
			Entity planet = template.createAtCoordinates(picked, random, idSource.incrementAndGet());
			planets.add(planet);
		}

		logger.debug("planets: {}", planets.size());

		return planets;
	}

}
