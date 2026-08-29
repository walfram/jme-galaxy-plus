package galaxy.generator;

import com.jme3.math.Vector3f;
import galaxy.generator.partition.SpacePartition2d;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedMinDistanceCellsTest {

	private static final Logger logger = LoggerFactory.getLogger(GeneratedMinDistanceCellsTest.class);

	@Test
	void should_generate_30_cells_taking_existing_coordinates() {
		Generator random = new Generator(37);

		GeneratedMinDistanceCells source = new GeneratedMinDistanceCells(30.0, 10);
		Collection<SpacePartition2d.Cell2d> sourceCells = source.cells();
		logger.info("source cells = {}", sourceCells);

		List<Vector3f> list = sourceCells.stream().map(c -> c.toRandomVector3f(random)).toList();

		GeneratedMinDistanceCells target = new GeneratedMinDistanceCells(10.0, 30, list);
		Collection<SpacePartition2d.Cell2d> targetCells = target.cells();

		assertEquals(30, targetCells.size());
		logger.info("target cells = {}", targetCells);
	}

	@Test
	void should_generate_20_cells_in_partitioned_space_30() {
		GeneratedMinDistanceCells source = new GeneratedMinDistanceCells(30.0, 20);
		Collection<SpacePartition2d.Cell2d> cells = source.cells();
		assertEquals(20, cells.size());
		logger.info("cells = {}", cells);
	}

}
