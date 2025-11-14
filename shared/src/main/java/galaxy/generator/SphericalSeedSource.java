package galaxy.generator;

import com.jme3.math.Vector3f;

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
		return List.of();
	}
}
