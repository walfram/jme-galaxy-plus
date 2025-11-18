package galaxy.generator.sources;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import galaxy.generator.SeedSource;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class GoldenSpiralSeedSource implements SeedSource {

	private static final Logger logger = getLogger(GoldenSpiralSeedSource.class);

	private static final float PHI = (float) ((1.0 + Math.sqrt(5.0)) / 2.0);
	private static final float START_RADIUS_A = 1.0f;

	private final int arms;
	private final int seedCount;
	private final double radius;
	private final long seed;

	public GoldenSpiralSeedSource(int arms, int seedCount, double radius, long seed) {
		this.arms = arms;
		this.seedCount = seedCount;
		this.radius = radius;
		this.seed = seed;
	}

	@Override
	public List<Vector3f> points() {
		List<Vector3f> points = new ArrayList<>(seedCount);

		float maxAngle = (FastMath.PI / (2 * FastMath.log(PHI))) * FastMath.log((float) (radius / START_RADIUS_A));

		int pivotCount = 256;

		List<Vector3f> pivots = new ArrayList<>(pivotCount);
		for (int i = 0; i < pivotCount; i++) {
			// Calculate current angle (theta_i) - evenly distributed
			// The last point (i = n-1) will be at maxAngle
			float theta_i = i * maxAngle / (pivotCount - 1);

			// Calculate current radius (r_i) using the Golden Spiral formula:
			// r = a * PHI^(2 * theta / PI)
			float r_i = START_RADIUS_A * FastMath.pow(PHI, (2 * theta_i / FastMath.PI));

			// 3. Convert polar (r, theta) to Cartesian (x, y)
			float x_i = r_i * FastMath.cos(theta_i);
			float y_i = r_i * FastMath.sin(theta_i);

			pivots.add(new Vector3f(x_i, 0, y_i));
		}

		int count = seedCount / (pivotCount * arms);
		Generator random = new Generator(seed);

		for (int arm = 0; arm < arms; arm++) {
			Quaternion rotation = new Quaternion().fromAngleAxis(arm * (FastMath.TWO_PI / arms), Vector3f.UNIT_Y);

			List<Vector3f> armPivots = pivots.stream()
					.map(rotation::mult)
					.toList();

			for (int i = 0; i < armPivots.size() - 2; i++) {
				Vector3f from = armPivots.get(i);
				Vector3f to = armPivots.get(i + 1);
				SeedSource source = new ConstrainedDistanceSeedSource(
						new CylinderSeedSource(count, from, to, 20f, (float) radius, random),
						from, to, (float) radius, random
				);
				points.addAll(source.points());
			}
		}

		return points;
	}
}
