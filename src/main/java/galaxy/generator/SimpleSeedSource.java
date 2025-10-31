package galaxy.generator;

import com.jme3.math.Vector3f;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;

public class SimpleSeedSource implements SeedSource {

	private static final double K_COMPRESSION_STRENGTH = 0.9;
	private static final double N_COMPRESSION_POWER = 0.8;

	private final int seedCount;
	private final double seedScale;
	private final long randomSeed;

	public SimpleSeedSource() {
		this(16384, 256f, 42L);
	}

	public SimpleSeedSource(int seedCount, double seedScale, long randomSeed) {
		this.seedCount = seedCount;
		this.seedScale = seedScale;
		this.randomSeed = randomSeed;
	}

	@Override
	public List<Vector3f> points() {
		Generator random = new Generator(randomSeed);

		List<Vector3f> seedSource = new ArrayList<>(seedCount);
		for (int i = 0; i < seedCount; i++) {
//			Vector3f point = random.nextVector3f().multLocal(SEED_SCALE);
//			seedSource.add(point);

			// 1. Get the random vector uniformly distributed in the unit sphere
			Vector3f point = random.nextVector3f();

			// 2. Calculate the distance (magnitude) of the point from the origin
			float r = point.length();

			// 3. Determine the compression factor alpha(r).
			//    alpha = 1 - K * r^N
			double r_powered = Math.pow(r, N_COMPRESSION_POWER);
			double y_compression_factor = 1.0 - K_COMPRESSION_STRENGTH * r_powered;

			// Ensure the factor is not negative (though it shouldn't be with K <= 1)
			y_compression_factor = Math.max(0.0, y_compression_factor);

			// 4. Apply y-axis compression
			// The y-component is scaled down by a factor based on its distance from the center.
			point.y *= (float) y_compression_factor;

			// 5. Scale the vector to the final desired size
			point.multLocal((float) seedScale);

			seedSource.add(point);
		}

		return seedSource;
	}
}
