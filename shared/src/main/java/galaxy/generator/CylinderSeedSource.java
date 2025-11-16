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

public class CylinderSeedSource implements SeedSource {

	private static final Logger logger = getLogger(CylinderSeedSource.class);

	private final int seedCount;
	private final Vector3f from;
	private final Vector3f to;
	private final float radius;
	private final Generator random;

	public CylinderSeedSource(int seedCount, Vector3f from, Vector3f vector3f, float radius, Generator random) {
		this.seedCount = seedCount;
		this.from = from;
		to = vector3f;
		this.radius = radius;
		this.random = random;
	}

	@Override
	public List<Vector3f> points() {
		List<Vector3f> points = new ArrayList<>(seedCount);

		Vector3f vector = to.subtract(from);

		float invertedDistanceRatio = Math.max(0.25f, 1f - (from.length() / radius));
		logger.debug("from.length = {}, distance ratio = {}", from.length(), invertedDistanceRatio);

		for (int j = 0; j < seedCount; j++) {
			Vector3f p = new Vector3f();
			MyVector3f.generateBasis(vector.clone(), p, new Vector3f());

			float theta = random.nextFloat(0, FastMath.TWO_PI);

			Vector3f rotated = new Quaternion().fromAngleAxis(theta, vector).mult(p);
			rotated.multLocal(random.nextFloat(0, invertedDistanceRatio * radius));

			Vector3f offset = vector.mult(random.nextFloat());
			points.add(rotated.add(from.add(offset)));
		}

		return points;
	}
}
