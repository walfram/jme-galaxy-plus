package galaxy.grid;

import com.simsilica.mathd.Grid;
import com.simsilica.mathd.GridCell;
import com.simsilica.mathd.Vec3i;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpacePartitionTest {

	private static final Logger logger = LoggerFactory.getLogger(SpacePartitionTest.class);

	@Test
	void should_pick_n_cells_without_repeat() {
		int n = 10;

		Set<GridCell> taken = new HashSet<>(n * 20);

		Grid grid = new Grid(30);

		GridCell initialCell = grid.getContainingCell(0.0, 0.0, 0.0);
		assertEquals(new Vec3i(0, 0, 0), initialCell.getCell());

		Set<GridCell> neighbours = neighboursOf(initialCell, 2);
		assertEquals(24, neighbours.size());
		logger.info("neighbours size {}", neighbours.size());

		Set<GridCell> neighboursInRadius = neighboursInRadius(initialCell, 2);
		assertEquals(12, neighboursInRadius.size());
		logger.info("neighbours in radius 2 = {}", neighboursInRadius.size());

		Set<GridCell> candidates = candidatesFor(initialCell, 2, 4);
		assertTrue(candidates.size() > 12);
		assertTrue(candidates.size() < 81); // 81 => (4 + 4 + 1)^2
		logger.info("candidates size {}", candidates.size());
	}

	private Set<GridCell> candidatesFor(GridCell initialCell, int rMin, int rMax) {
		Set<GridCell> neighbours = neighboursOf(initialCell, rMax);
		neighbours.removeIf(c -> c.getCell().getDistance(initialCell.getCell()) > rMin && c.getCell().getDistance(initialCell.getCell()) <= rMax);
		return neighbours;
	}

	private Set<GridCell> neighboursInRadius(GridCell origin, int radius) {
		Set<GridCell> neighbours = neighboursOf(origin, radius);
		neighbours.removeIf(c -> c.getCell().getDistance(origin.getCell()) <= radius);
		return neighbours;
	}

	private Set<GridCell> neighboursOf(GridCell initialCell, int radius) {
		Set<GridCell> neighbours = new HashSet<>(25);

		for (int dx = -radius; dx <= radius; dx++) {
			for (int dy = -radius; dy <= radius; dy++) {
				GridCell cell = new GridCell(
						initialCell.getGrid(),
						new Vec3i(initialCell.getCell().x + dx, initialCell.getCell().y + dy, initialCell.getCell().z)
				);

				neighbours.add(cell);
			}
		}

		neighbours.remove(initialCell);

		return neighbours;
	}

}
