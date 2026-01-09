package generator.sources;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import generator.SeedSource;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SpiralSeedSource implements SeedSource {

	private static final Logger logger = getLogger(SpiralSeedSource.class);

	private final int arms;
	private final int armPivots;
	private final int seedCount;
	private final double radius;
	private final long seed;

	public SpiralSeedSource(int arms, int armPivots, int seedCount, double radius, long seed) {
		this.arms = arms;
		this.armPivots = armPivots;
		this.seedCount = seedCount;
		this.radius = radius;
		this.seed = seed;
	}

	@Override
	public List<Vector3f> points() {
		Generator random = new Generator(seed);

		float turns = 1.5f;

		float thetaMax = FastMath.TWO_PI * turns;
		float k = (float) (radius / thetaMax);

		float phiOffset = FastMath.TWO_PI / arms;

		List<List<Vector3f>> armPivots = new ArrayList<>(arms);

		for (int arm = 0; arm < arms; arm++) {
			float spiralOffset = phiOffset * arm;
			armPivots.add(new ArrayList<>(this.armPivots));

			for (int i = 0; i < this.armPivots; i++) {
				float theta = ((float) i / (this.armPivots - 1)) * thetaMax;
				float currentR = k * theta;
				float rotatedTheta = theta + spiralOffset;
				float x = currentR * FastMath.cos(rotatedTheta);
				float z = currentR * FastMath.sin(rotatedTheta);

				armPivots.get(arm).add(new Vector3f(x, 0, z));
			}
		}

		List<Vector3f> points = new ArrayList<>(seedCount);

		int windowSize = 2;
		int subPoints = seedCount / (armPivots.size() * this.armPivots);

		for (List<Vector3f> arm: armPivots) {

			for (int i = 0; i < arm.size() - windowSize; i++) {
				Vector3f from = arm.get(i);
				Vector3f to = arm.get(i + 1);

				SeedSource source = new ConstrainedDistanceSeedSource(
						new CylinderSeedSource(subPoints, from, to, 20f, (float) radius, random),
						from, to, (float) radius, random
				);

//				SeedSource source = new CylinderSeedSource(subPoints, from, to, 10f, (float) radius, random);

				points.addAll(source.points());

			}

		}

		logger.debug("points size = {}", points.size());
		return points;
	}

}
