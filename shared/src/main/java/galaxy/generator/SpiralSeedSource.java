package galaxy.generator;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class SpiralSeedSource implements SeedSource {

	private static final Logger logger = getLogger(SpiralSeedSource.class);

	private final int arms;
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
		List<Vector3f> points = new ArrayList<>(seedCount);

		Generator random = new Generator(seed);

		float turns = 1.5f;

		float thetaMax = FastMath.TWO_PI * turns;
		float k = (float) (radius / thetaMax);

		float phiOffset = FastMath.TWO_PI / arms;

		for (int arm = 0; arm < arms; arm++) {
			float spiralOffset = phiOffset * arm;
			for (int i = 0; i < 32; i++) {
				float theta = (i / 31f) * thetaMax;
				float currentR = k * theta;
				float rotatedTheta = theta + spiralOffset;
				float x = currentR * FastMath.cos(rotatedTheta);
				float z = currentR * FastMath.sin(rotatedTheta);
				points.add(new Vector3f(x, 0, z));
			}
		}

		logger.debug("points size = {}", points.size());
		Set<Vector3f> set = new HashSet<>(points);
		logger.debug("points unique size = {}", set.size());

		return points;
	}
}
