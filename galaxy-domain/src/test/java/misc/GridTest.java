package misc;

import com.jme3.math.Vector3f;
import com.simsilica.mathd.Grid;
import com.simsilica.mathd.Vec3d;
import com.simsilica.mathd.Vec3i;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GridTest {

	private static final Logger logger = LoggerFactory.getLogger(GridTest.class);

	@Test
	void test_bucketing() {
		Generator random = new Generator(42);

		List<Vector3f> seedPoints = new ArrayList<>(4096);
		for (int idx = 0; idx < 4096; idx++) {
			seedPoints.add(random.nextVector3f().multLocal(256f));
		}

		logger.info("seedPoints = {}", seedPoints.size());

		Map<Long, List<Vector3f>> buckets = new HashMap<>(4096);

		Grid grid = new Grid(30);
		for (Vector3f point: seedPoints) {
			Vec3d v = new Vec3d(point);
			long id = grid.worldToId(v);

			buckets.computeIfAbsent(id, k -> new ArrayList<>()).add(point);
		}

		logger.info("buckets = {}", buckets.size());
		assertFalse(buckets.isEmpty());

//		List<Vec3i> list = buckets.stream().sorted(
//				(l, r) -> Float.compare(l.toVector3f().lengthSquared(), r.toVector3f().lengthSquared())
//		).limit(16).toList();
//		list.forEach(e -> logger.info("closest to origin = {}", e));
//		logger.info("ids = {}", ids.size());
//		ids.stream().sorted().limit(32).forEach(e -> logger.info("world id: {}", e));
//		assertFalse(ids.isEmpty());
	}

}
