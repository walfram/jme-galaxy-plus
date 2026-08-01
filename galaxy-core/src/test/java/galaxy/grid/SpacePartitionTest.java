package galaxy.grid;

import galaxy.generator.partition.SpacePartition;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpacePartitionTest {

	private static final Logger logger = LoggerFactory.getLogger(SpacePartitionTest.class);

	@Test
	void test_cell_returns_neighbours_in_radius() {
		SpacePartition<galaxy.generator.partition.SpacePartition2d.Cell2d> partition = new galaxy.generator.partition.SpacePartition2d(30.0);

		galaxy.generator.partition.SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);
		Set<galaxy.generator.partition.SpacePartition2d.Cell2d> cells = origin.neighboursRadius(2);

		assertEquals(12, cells.size());
	}

	@Test
	void test_cell_returns_neighbours() {
		SpacePartition<galaxy.generator.partition.SpacePartition2d.Cell2d> partition = new galaxy.generator.partition.SpacePartition2d(30.0);

		galaxy.generator.partition.SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);
		Set<galaxy.generator.partition.SpacePartition2d.Cell2d> neighbours = origin.neighbours(2);

		assertEquals(24, neighbours.size());
	}

	@Test
	void test_world_to_cell_partition() {
		SpacePartition<galaxy.generator.partition.SpacePartition2d.Cell2d> partition = new galaxy.generator.partition.SpacePartition2d(30.0);

		galaxy.generator.partition.SpacePartition2d.Cell2d origin = partition.toCell(0.0, 0.0);
		assertEquals(new galaxy.generator.partition.SpacePartition2d.Cell2d(0, 0, 30.0), origin);

		galaxy.generator.partition.SpacePartition2d.Cell2d a = partition.toCell(30.0, 0.0);
		assertEquals(new galaxy.generator.partition.SpacePartition2d.Cell2d(1, 0, 30.0), a);

		galaxy.generator.partition.SpacePartition2d.Cell2d b = partition.toCell(0.0, 30.0);
		assertEquals(new galaxy.generator.partition.SpacePartition2d.Cell2d(0, 1, 30.0), b);

		galaxy.generator.partition.SpacePartition2d.Cell2d c = partition.toCell(40.0, 40.0);
		assertEquals(new galaxy.generator.partition.SpacePartition2d.Cell2d(1, 1, 30.0), c);
	}

}
