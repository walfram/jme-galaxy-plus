package galaxy.grid;

import com.simsilica.mathd.Grid;
import com.simsilica.mathd.GridCell;
import com.simsilica.mathd.Vec3i;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridPartitionTest {

	private static final Logger logger = LoggerFactory.getLogger(GridPartitionTest.class);

	@Test
	void should_pick_n_cells_without_repeat() {
		int required = 10;
		Set<GridCell> origins = new HashSet<>(required * 20);

		Grid grid = new Grid(30);

		GridCell initialCell = grid.getContainingCell(0.0, 0.0, 0.0);
		assertEquals(new Vec3i(0, 0, 0), initialCell.getCell());
		origins.add(initialCell);

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

		Set<GridCell> discarded = new HashSet<>(neighboursInRadius);
		discarded.add(initialCell);

		Generator random = new Generator(42);

		while (origins.size() < required) {
			// pick random cell from candidates
			GridCell candidate = candidates.stream().skip(random.nextInt(candidates.size())).findFirst().orElseThrow();

			// if random cell is valid: add to origins, add neighbors to "discarded", update candidates
			if (!discarded.contains(candidate)) {
				origins.add(candidate);
				discarded.add(candidate);

				Set<GridCell> localDiscarded = neighboursInRadius(candidate, 2);
				discarded.addAll(localDiscarded);

				Set<GridCell> localCandidates = candidatesFor(candidate, 2, 4);
				candidates.addAll(localCandidates);

				candidates.removeAll(discarded);
			}

			candidates.remove(candidate);
			// check if candidates are exhausted
		}

		assertEquals(required, origins.size());

		origins.forEach(c -> logger.info("origin {}", c.getCell()));

		int left = origins.stream().min(Comparator.comparingInt(c -> c.getCell().x)).orElseThrow().getCell().x - 1;
		int right = origins.stream().max(Comparator.comparingInt(c -> c.getCell().x)).orElseThrow().getCell().x + 1;

		int top = origins.stream().max(Comparator.comparingInt(c -> c.getCell().y)).orElseThrow().getCell().y + 1;
		int bottom = origins.stream().min(Comparator.comparingInt(c -> c.getCell().y)).orElseThrow().getCell().y - 1;

		int height = top - bottom;
		assertTrue(height > 0);

		int width = right - left;
		assertTrue(width > 0);

		for (int row = top; row >= bottom; row--) {
			StringBuilder sb = new StringBuilder();
			for (int col = left; col <= right; col++) {
				GridCell c = new GridCell(grid, new Vec3i(col, row, 0));
				if (origins.contains(c)) {
					sb.append("X");
				} else {
					sb.append(".");
				}
			}

			logger.info(sb.toString());
		}
	}

	private Set<GridCell> candidatesFor(GridCell initialCell, int rMin, int rMax) {
		Set<GridCell> neighbours = neighboursOf(initialCell, rMax);
		neighbours.removeIf(c -> {
			double d = c.getCell().getDistance(initialCell.getCell());
			return d <= rMin || d > rMax;
		});
		return neighbours;
	}

	private Set<GridCell> neighboursInRadius(GridCell origin, int radius) {
		Set<GridCell> neighbours = neighboursOf(origin, radius);
		neighbours.removeIf(c -> c.getCell().getDistance(origin.getCell()) > radius);
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
