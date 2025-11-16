package galaxy.generator.sources;

import com.jme3.math.Vector3f;
import galaxy.generator.SeedSource;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;

public class SphericalSeedSource implements SeedSource {
	private final int seedCount;
	private final double radius;
	private final long seed;

	public SphericalSeedSource(int seedCount, double radius, long seed) {
		this.seedCount = seedCount;
		this.radius = radius;
		this.seed = seed;
	}

	@Override
	public List<Vector3f> points() {
		List<Vector3f> points = new ArrayList<>(seedCount);
		Generator random = new Generator(seed);

		for (int i = 0; i < seedCount; i++) {
			points.add(random.nextVector3f().mult((float) radius));
		}

		return points;
	}
}
