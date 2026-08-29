package galaxy.generator;

import com.jme3.math.Vector3f;
import galaxy.generator.partition.SpacePartition;
import galaxy.generator.partition.SpacePartition2d;
import jme3utilities.math.noise.Generator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneratedMinDistanceCells {
	private final double cellWidth;
	private final int cellCount;
	private final List<Vector3f> preOccupied;

	// TODO seed as constructor param
	private final Generator random = new Generator(42);

	public GeneratedMinDistanceCells(double cellWidth, int cellCount) {
		this(cellWidth, cellCount, List.of());
	}

	public GeneratedMinDistanceCells(double cellWidth, int cellCount, List<Vector3f> preOccupied) {
		this.cellWidth = cellWidth;
		this.cellCount = cellCount;
		this.preOccupied = preOccupied;
	}

	public Collection<SpacePartition2d.Cell2d> cells() {
		SpacePartition<SpacePartition2d.Cell2d> partition = new SpacePartition2d(cellWidth);

		HashSet<SpacePartition2d.Cell2d> discarded = preOccupied.stream()
				.map(v -> partition.toCell(v.x, v.y))
				.collect(Collectors.toCollection(HashSet::new));

		HashSet<SpacePartition2d.Cell2d> candidates = discarded.stream()
				.map(c -> c.neighboursInRange(2, 3))
				.flatMap(Set::stream)
				.collect(Collectors.toCollection(HashSet::new));
		candidates.removeAll(discarded);

		if (candidates.isEmpty() && discarded.isEmpty()) {
			candidates.add(new SpacePartition2d.Cell2d(0, 0, cellWidth));
		}

		SpacePartition2d.Cell2d origin = candidates.stream().skip(random.nextInt(candidates.size())).findFirst().orElseThrow();

		discarded.add(origin);
		discarded.addAll(origin.neighbours(1));

		candidates.addAll(origin.neighboursInRange(2, 3));
		candidates.removeAll(discarded);

		Set<SpacePartition2d.Cell2d> origins = new HashSet<>(cellCount);
		origins.add(origin);

		while (origins.size() < cellCount) {
			long skip = random.nextInt(candidates.size());
			SpacePartition2d.Cell2d cell = candidates.stream().skip(skip).findFirst().orElseThrow();
			candidates.remove(cell);
			origins.add(cell);

			discarded.add(cell);
			discarded.addAll(cell.neighbours(1));

			candidates.addAll(cell.neighboursInRange(2, 3));
			candidates.removeAll(discarded);
		}

		return origins;
	}
}
