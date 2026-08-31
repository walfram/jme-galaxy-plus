package hex.grid;

import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexCellPickingTest {

	private static final Logger logger = LoggerFactory.getLogger(HexCellPickingTest.class);

	@Test
	void should_pick_n_cell_m_cells_apart() {
		HexGrid grid = new HexGrid(15.0);
		int n = 10;
		int m = 1;

		HexCell origin = grid.cell(0, 0);

		Set<HexCell> picked = new HashSet<>(n);

		Set<HexCell> discarded = new HashSet<>(origin.neighbours());
		discarded.add(origin);

		Set<HexCell> candidates = new HashSet<>(origin.neighbourRing(m + 1));

		Generator random = new Generator(978);

		while (picked.size() < n) {
			long skip = random.nextLong(candidates.size());

			HexCell cell = candidates.stream().skip(skip).findFirst().orElseThrow();
			picked.add(cell);

			candidates.remove(cell);

			discarded.add(cell);
			discarded.addAll(cell.neighbours());

			Collection<HexCell> check = cell.neighbourRing(m + 1);
			check.removeAll(discarded);

			candidates.addAll(check);
		}

		assertEquals(n, picked.size());

		for (HexCell c : picked) {
			logger.info("picked cell {}", c);
		}
	}

}
