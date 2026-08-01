package galaxy.grid;

import galaxy.generator.partition.SpacePartition;
import galaxy.generator.partition.SpacePartition2d;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpacePartitionTest {

	private static final Logger logger = LoggerFactory.getLogger(SpacePartitionTest.class);

	@Test
	void test_pick_n_cells_for_homeworlds() {
		SpacePartition<SpacePartition2d.Cell2d> partition = new SpacePartition2d(30.0);

		SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);

		Set<SpacePartition2d.Cell2d> discarded = new HashSet<>(origin.neighbours(1));
		assertEquals(8, discarded.size());

		Set<SpacePartition2d.Cell2d> candidates = new HashSet<>(origin.neighboursInRange(2, 3));

		int n = 20;
		Set<SpacePartition2d.Cell2d> origins = new HashSet<>(n);
		origins.add(origin);

		Generator random = new Generator(42);

		while (origins.size() < n) {
			long skip = random.nextInt(candidates.size());
			SpacePartition2d.Cell2d cell = candidates.stream().skip(skip).findFirst().orElseThrow();
			candidates.remove(cell);
			origins.add(cell);

			discarded.add(cell);
			discarded.addAll(cell.neighbours(1));

			candidates.addAll(cell.neighboursInRange(2, 3));
			candidates.removeAll(discarded);
		}

		assertEquals(n, origins.size());

		int minX = origins.stream().mapToInt(SpacePartition2d.Cell2d::x).min().orElseThrow();
		int maxX = origins.stream().mapToInt(SpacePartition2d.Cell2d::x).max().orElseThrow();
		int minY = origins.stream().mapToInt(SpacePartition2d.Cell2d::y).min().orElseThrow();
		int maxY = origins.stream().mapToInt(SpacePartition2d.Cell2d::y).max().orElseThrow();

		int width = maxX - minX + 1;
		int height = maxY - minY + 1;

		for (int row = height; row >= 0; row--) {
			StringBuilder sb = new StringBuilder();
			for (int col = 0; col < width; col++) {
				SpacePartition2d.Cell2d c = new SpacePartition2d.Cell2d(col + minX, row + minY, 30.0);
				if (origins.contains(c)) {
					sb.append("X");
				} else {
					sb.append(".");
				}
			}

			logger.info(sb.toString());
		}

	}
	
	@Test
	void test_cell_returns_neighbours_in_radius() {
		SpacePartition<SpacePartition2d.Cell2d> partition = new SpacePartition2d(30.0);

		SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);
		Set<SpacePartition2d.Cell2d> cells = origin.neighboursRadius(2);

		assertEquals(12, cells.size());
	}

	@Test
	void test_cell_returns_neighbours() {
		SpacePartition<SpacePartition2d.Cell2d> partition = new SpacePartition2d(30.0);

		SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);
		Set<SpacePartition2d.Cell2d> neighbours = origin.neighbours(2);

		assertEquals(24, neighbours.size());
	}

	@Test
	void test_world_to_cell_partition() {
		SpacePartition<SpacePartition2d.Cell2d> partition = new SpacePartition2d(30.0);

		SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);
		assertEquals(new SpacePartition2d.Cell2d(0, 0, 30.0), origin);

		SpacePartition2d.Cell2d a = partition.toCell(30.0, 0.0);
		assertEquals(new SpacePartition2d.Cell2d(1, 0, 30.0), a);

		SpacePartition2d.Cell2d b = partition.toCell(0.0, 30.0);
		assertEquals(new SpacePartition2d.Cell2d(0, 1, 30.0), b);

		SpacePartition2d.Cell2d c = partition.toCell(40.0, 40.0);
		assertEquals(new SpacePartition2d.Cell2d(1, 1, 30.0), c);
	}

}
