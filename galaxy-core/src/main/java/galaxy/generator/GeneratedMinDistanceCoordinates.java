package galaxy.generator;

import com.simsilica.mathd.Grid;
import com.simsilica.mathd.GridCell;
import com.simsilica.mathd.Vec3d;
import com.simsilica.mathd.Vec3i;
import jme3utilities.math.noise.Generator;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class GeneratedMinDistanceCoordinates implements GeneratedCoordinates {

	private static final Logger logger = getLogger(GeneratedMinDistanceCoordinates.class);

	private final Collection<Vec3d> occupied;
	private final double minDistance;
	private final int count;
	private final Generator generator;

	public GeneratedMinDistanceCoordinates(Collection<Vec3d> occupied, double minDistance, int count, Generator generator) {
		this.occupied = occupied;
		this.minDistance = minDistance;
		this.count = count;
		this.generator = generator;
	}

	@Override
	public List<Vec3d> coordinates() {
		Grid grid = new Grid((int) minDistance);

		Set<GridCell> discarded = new HashSet<>();
		Set<GridCell> candidates = new HashSet<>();
		Set<GridCell> origins = new HashSet<>();

//		logger.debug("occupied.size = {}", occupied.size());

		for (Vec3d v : occupied) {
			GridCell cell = grid.getContainingCell(v);
			discarded.add(cell);
			discarded.addAll(neighboursInRadius(cell, 2));

			candidates.addAll(candidatesFor(cell, 2, 4));
		}

//		logger.debug("candidates.size = {}", candidates.size());
		candidates.removeAll(discarded);
		// TODO removed all candidates?
		if (candidates.isEmpty()) {
			// throw new IllegalStateException("no candidates");
			GridCell origin = grid.getContainingCell(0, 0, 0);
			origins.add(origin);
			candidates.addAll(candidatesFor(origin, 2, 4));
			discarded.add(origin);
			discarded.addAll(neighboursInRadius(origin, 2));
		}

		while (origins.size() < count) {
			// pick random cell from candidates
			GridCell candidate = candidates.stream().skip(generator.nextInt(candidates.size())).findFirst().orElseThrow();

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
			// TODO check if candidates are exhausted
		}

		return origins.stream().map(e -> e.getCell().toVec3d()).toList();
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
