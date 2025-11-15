package galaxy.generator;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import jme3utilities.math.MyVector3f;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SpiralSeedSource implements SeedSource {

	private static final Logger logger = getLogger(SpiralSeedSource.class);

	private final int arms;
	private final int armChunks = 24;
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
		Generator random = new Generator(seed);

		float turns = 1.5f;

		float thetaMax = FastMath.TWO_PI * turns;
		float k = (float) (radius / thetaMax);

		float phiOffset = FastMath.TWO_PI / arms;

		List<List<Vector3f>> armPivots = new ArrayList<>(arms);

		for (int arm = 0; arm < arms; arm++) {
			float spiralOffset = phiOffset * arm;
			armPivots.add(new ArrayList<>(armChunks));

			for (int i = 0; i < armChunks; i++) {
				float theta = ((float) i / (armChunks - 1)) * thetaMax;
				float currentR = k * theta;
				float rotatedTheta = theta + spiralOffset;
				float x = currentR * FastMath.cos(rotatedTheta);
				float z = currentR * FastMath.sin(rotatedTheta);

				armPivots.get(arm).add(new Vector3f(x, 0, z));
			}
		}

		List<Vector3f> points = new ArrayList<>(seedCount);

		int windowSize = 2;
		int subPoints = (seedCount / armPivots.size()) / windowSize;

		for (List<Vector3f> arm: armPivots) {

			for (int i = 0; i < arm.size() - windowSize; i++) {
				Vector3f from = arm.get(i);
				Vector3f to = arm.get(i + 1);
				Vector3f vector = to.subtract(from);

				float invertedDistanceRatio = Math.max(0.25f, 1f - (float) (from.length() / radius));
				logger.debug("from.length = {}, distance ratio = {}", from.length(), invertedDistanceRatio);

				for (int j = 0; j < subPoints; j++) {
					Vector3f p = new Vector3f();
					MyVector3f.generateBasis(vector.clone(), p, new Vector3f());

					float theta = random.nextFloat(0, FastMath.TWO_PI);

					Vector3f rotated = new Quaternion().fromAngleAxis(theta, vector).mult(p);
					rotated.multLocal(random.nextFloat(0, invertedDistanceRatio * 40f));

					Vector3f offset = vector.mult(random.nextFloat());
					points.add(rotated.add(from.add(offset)));
				}
			}

		}

		logger.debug("points size = {}", points.size());
		return points;
	}

}
