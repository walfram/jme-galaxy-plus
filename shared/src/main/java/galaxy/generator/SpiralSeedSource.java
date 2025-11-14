package galaxy.generator;

import com.jme3.math.Vector3f;

import java.util.List;

public class SpiralSeedSource implements SeedSource {

	private final int arms;
	private final double radius;
	private final long seed;

	public SpiralSeedSource(int arms, double radius, long seed) {
		this.arms = arms;
		this.radius = radius;
		this.seed = seed;
	}

	@Override
	public List<Vector3f> points() {
		return List.of();
	}
}
