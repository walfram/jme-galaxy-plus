package hex.grid;

import com.jme3.math.Vector3f;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SpacePartitionTest {

	private static final Logger logger = LoggerFactory.getLogger(SpacePartitionTest.class);

	@Test
	void should_convert_coordinates_to_hex_cells() {
		float radius = 128f;
		int size = 1024;

		Generator random = new Generator(1337);
		List<Vector3f> seedPoints = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			seedPoints.add(random.nextVector3f().multLocal(radius));
		}

		HexGrid grid = new HexGrid(8);
		Set<HexCell> cells = new HashSet<>(512);
		Map<HexCell, List<Vector3f>> relation = new HashMap<>(512);

		for (Vector3f point: seedPoints) {
			HexCell cell = grid.toCell(point);
			cells.add(cell);
			relation.computeIfAbsent(cell, k -> new ArrayList<>(128)).add(point);
		}

		assertFalse(cells.isEmpty());
		logger.info("cells = {}", cells.size());
		logger.info("relation = {}", relation.size());

		for (Map.Entry<HexCell, List<Vector3f>> entry: relation.entrySet()) {
			logger.info("cell = {}, points = {}", entry.getKey(), entry.getValue().size());
		}
	}

}
