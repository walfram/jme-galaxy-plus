package generator.impl.classic;

import com.jme3.math.Vector3f;
import galaxy.core.ClassicGalaxy;
import galaxy.core.Component;
import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.planet.Coordinates;
import generator.GeneratedGalaxy;
import generator.PlanetTemplate;
import generator.SeedSource;
import generator.WeightedDistribution;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassicGeneratedGalaxy implements GeneratedGalaxy {

	private static final double MIN_HW_DISTANCE = 30.0;
	private static final double DISTANCE_DW_MIN = 5.0;
	private static final double DISTANCE_DW_MAX = 10.0;

	private final int teamCount;
	private final int planetRatio;
	private final SeedSource seedSource;
	private final long seed;

	public ClassicGeneratedGalaxy(int teamCount, int planetRatio, SeedSource seedSource, long seed) {
		this.teamCount = teamCount;
		this.planetRatio = planetRatio;
		this.seedSource = seedSource;
		this.seed = seed;
	}

	@Override
	public Context generate() {
		List<Entity> entities = new ArrayList<>(teamCount * planetRatio);

		Context galaxy = new ClassicGalaxy(entities);

		List<Vector3f> seedPoints = seedSource.points();
		Generator random = new Generator(seed);

		for (int i = 0; i < teamCount; i++) {
			galaxy.createTeam("team-%s".formatted(i));
		}

		for (Entity team: galaxy.teams().values()) {
			Entity hw = galaxy.createHomeWorld(team);
			Entity dw0 = galaxy.createDaughterWorld(team);
			Entity dw1 = galaxy.createDaughterWorld(team);

			Vector3f origin = random.pick(seedPoints);
			hw.put(new Coordinates(origin.x, origin.y, origin.z));

			List<Vector3f> remove = seedPoints.stream().filter(p -> origin.distance(p) <= MIN_HW_DISTANCE).toList();
			seedPoints.removeAll(remove);

			Vector3f offsetDw0 = random.nextUnitVector3f().mult((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX));
			dw0.put(new Coordinates(origin.x + offsetDw0.x, origin.y + offsetDw0.y, origin.z + offsetDw0.z));

			Vector3f offsetDw1 = random.nextUnitVector3f().mult((float) random.nextDouble(DISTANCE_DW_MIN, DISTANCE_DW_MAX));
			dw1.put(new Coordinates(origin.x + offsetDw1.x, origin.y + offsetDw1.y, origin.z + offsetDw1.z));
		}

		int otherPlanets = teamCount * planetRatio - teamCount * 3;
		WeightedDistribution<PlanetTemplate> distribution = new ClassicPlanetDistribution();

		List<Vector3f> seeds = new ArrayList<>(seedSource.points());
		while (otherPlanets > 0) {
			PlanetTemplate template = distribution.pick(random);

			List<Vector3f> existingCoordinates = galaxy.planets().values().stream()
					.map(e -> e.prop(Coordinates.class))
					.map(c -> new Vector3f((float) c.x(), (float) c.y(), (float) c.z()))
					.toList();

			List<Vector3f> filtered = filter(new ArrayList<>(seeds), existingCoordinates, template.minDistance());
			Vector3f coords = random.pick(filtered);

			Entity planet = galaxy.createUninhabitedPlanet();
			List<Component> components = template.planetComponents(coords, random);

			planet.putAll(components);

			otherPlanets--;
		}

		return galaxy;
	}

	private List<Vector3f> filter(List<Vector3f> points, List<Vector3f> existingCoordinates, double radius) {
		double radiusSquared = radius * radius;
		Map<Long, List<Vector3f>> buckets = new HashMap<>(existingCoordinates.size() * 2);

		for (Vector3f ep : existingCoordinates) {
			long gridKey = gridKey(ep, radius);
			buckets.computeIfAbsent(gridKey, k -> new ArrayList<>()).add(ep);
		}

		List<Vector3f> remove = points.stream().filter(p -> {
			int ix = (int) Math.floor(p.x / radius);
			int iy = (int) Math.floor(p.y / radius);
			int iz = (int) Math.floor(p.z / radius);

			for (int x = ix - 1; x <= ix + 1; x++) {
				for (int y = iy - 1; y <= iy + 1; y++) {
					for (int z = iz - 1; z <= iz + 1; z++) {
						List<Vector3f> cell = buckets.get(hash(x, y, z));
						if (cell != null) {
							for (Vector3f ep : cell) {
								// distanceSquared is significantly faster than distance()
								if (p.distanceSquared(ep) <= radiusSquared) {
									return true; // Found a match, remove from 'points'
								}
							}
						}
					}
				}
			}
			return false;
		}).toList();

		points.removeAll(remove);
		return points;
	}

	private long hash(int x, int y, int z) {
		return ((long) x & 0x1FFFFF) | (((long) y & 0x1FFFFF) << 21) | (((long) z & 0x3FFFFF) << 42);
	}

	private long gridKey(Vector3f v, double radius) {
		return hash((int) Math.floor(v.x / radius),
				(int) Math.floor(v.y / radius),
				(int) Math.floor(v.z / radius));
	}
}
