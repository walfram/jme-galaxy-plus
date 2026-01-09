package generator.sources;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import generator.SeedSource;
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
	private final float cylinderRadius;
	private final float maxDistanceFromCenter;
	private final Generator random;

	public CylinderSeedSource(int seedCount, Vector3f from, Vector3f to, float cylinderRadius, float maxDistanceFromCenter, Generator random) {
		this.seedCount = seedCount;
		this.from = from;
		this.to = to;
		this.cylinderRadius = cylinderRadius;
		this.maxDistanceFromCenter = maxDistanceFromCenter;
		this.random = random;
	}

	@Override
	public List<Vector3f> points() {
		List<Vector3f> points = new ArrayList<>(seedCount);

		Vector3f baseDirection = to.subtract(from);

		float invertedDistanceRatio = Math.max(0.125f, 1f - (from.length() / maxDistanceFromCenter));

		for (int j = 0; j < seedCount; j++) {
			Vector3f p = new Vector3f();
			MyVector3f.generateBasis(baseDirection.clone(), p, new Vector3f());

			float theta = random.nextFloat(0, FastMath.TWO_PI);

			Vector3f rotated = new Quaternion().fromAngleAxis(theta, baseDirection).mult(p);
//			rotated.multLocal(random.nextFloat(0, invertedDistanceRatio * cylinderRadius));
			rotated.multLocal(random.nextFloat(0, cylinderRadius));

			Vector3f offset = baseDirection.mult(random.nextFloat());
			points.add(rotated.add(from.add(offset)));
		}

		return points;
	}
}
