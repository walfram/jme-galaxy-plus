package galaxy.generator.sources;

import com.jme3.math.Plane;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import galaxy.generator.SeedSource;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ConstrainedDistanceSeedSource implements SeedSource {

	private static final Logger logger = getLogger(ConstrainedDistanceSeedSource.class);

	private final SeedSource other;
	private final Vector3f from;
	private final Vector3f to;
	private final float maxDistanceFromCenter;
	private final Generator random;

	public ConstrainedDistanceSeedSource(SeedSource other, Vector3f from, Vector3f to, float maxDistanceFromCenter, Generator random) {
		this.other = other;
		this.from = from;
		this.to = to;
		this.maxDistanceFromCenter = maxDistanceFromCenter;
		this.random = random;
	}

	@Override
	public List<Vector3f> points() {
		float invertedDistanceRatio = Math.max(0.125f, 1f - (from.length() / maxDistanceFromCenter));

		Vector3f v = to.subtract(from);
		Vector3f vNorm = v.normalize();
		Ray ray = new Ray(from, vNorm);

		return other.points()
				.stream()
				.map(p -> {
					Plane plane = new Plane(vNorm, p);
					Vector3f a = new Vector3f();

					float constrainedLength = random.nextFloat(0, invertedDistanceRatio * 20f);

					if (ray.intersectsWherePlane(plane, a)) {
						Vector3f direction = p.subtract(a).normalizeLocal();
						return a.add(direction.mult(constrainedLength));
					} else {
						logger.warn("Ray does not intersect plane");
					}

					return p;
				})
				.toList();
	}
}
