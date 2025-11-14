package galaxy.generator;

import com.google.common.collect.Iterators;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class SpiralSeedSource implements SeedSource {

	private static final Logger logger = getLogger(SpiralSeedSource.class);

	private final int arms;
	private final int armChunks = 32;
	private final int seedCount;
	private final double radius;
	private final long seed;

	public SpiralSeedSource(int arms, int seedCount, double radius, long seed) {
		this.arms = arms;
		this.seedCount = seedCount;
		this.radius = radius;
		this.seed = seed;
	}

	@Override
	public List<Vector3f> points() {
		List<Vector3f> pivots = new ArrayList<>(arms * armChunks);

		Generator random = new Generator(seed);

		float turns = 1.5f;

		float thetaMax = FastMath.TWO_PI * turns;
		float k = (float) (radius / thetaMax);

		float phiOffset = FastMath.TWO_PI / arms;

		for (int arm = 0; arm < arms; arm++) {
			float spiralOffset = phiOffset * arm;
			for (int i = 0; i < armChunks; i++) {
				float theta = ((float) i / (armChunks - 1)) * thetaMax;
				float currentR = k * theta;
				float rotatedTheta = theta + spiralOffset;
				float x = currentR * FastMath.cos(rotatedTheta);
				float z = currentR * FastMath.sin(rotatedTheta);
				pivots.add(new Vector3f(x, 0, z));
			}
		}

		logger.debug("points size = {}", pivots.size());
		Set<Vector3f> set = new HashSet<>(pivots);
		logger.debug("points unique size = {}", set.size());

		List<Vector3f> points = new ArrayList<>(seedCount);

		Iterator<Vector3f> pivotSource = Iterators.cycle(pivots);
		while (points.size() < seedCount) {
			Vector3f pivot = pivotSource.next();

			float r = 25f * (float) (1.75f - (pivot.length() / radius));

			Vector3f point = pivot.add(random.nextVector3f().mult(r));
			points.add(point);
		}

		return points;
	}
}
