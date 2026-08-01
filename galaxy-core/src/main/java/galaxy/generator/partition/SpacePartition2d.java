package galaxy.generator.partition;

import java.util.HashSet;
import java.util.Set;

public class SpacePartition2d implements SpacePartition<SpacePartition2d.Cell2d> {
	private final double cellWidth;

	public SpacePartition2d(double cellWidth) {
		this.cellWidth = cellWidth;
	}

	@Override
	public Cell2d toCell(double worldX, double worldY) {
		return new Cell2d(
				(int) Math.floor(worldX / cellWidth),
				(int) Math.floor(worldY / cellWidth),
				cellWidth
		);
	}

	public record Cell2d(int x, int y, double width) implements PartitionCell<Cell2d> {
		@Override
		public Set<Cell2d> neighbours(int width) {
			int capacity = (width + width + 1) * (width + width + 1);
			Set<Cell2d> neighbours = new HashSet<>(capacity);

			for (int dx = -width; dx <= width; dx++) {
				for (int dy = -width; dy <= width; dy++) {
					neighbours.add(new Cell2d(x + dx, y + dy, this.width));
				}
			}

			neighbours.remove(this);

			return neighbours;
		}

		@Override
		public Set<Cell2d> neighboursRadius(int radius) {
			int radius2 = radius * radius;
			Set<Cell2d> neighbours = new HashSet<>(radius2);

			for (int dx = -radius; dx <= radius; dx++) {
				for (int dy = -radius; dy <= radius; dy++) {
					int d2 = dx * dx + dy * dy;
					if (d2 <= radius2) {
						neighbours.add(new Cell2d(x + dx, y + dy, width));
					}
				}
			}

			neighbours.remove(this);

			return neighbours;
		}

		@Override
		public Set<Cell2d> neighboursInRange(int innerRadius, int outerRadius) {
			Set<Cell2d> neighbours = neighboursRadius(outerRadius);

			neighbours.removeAll(neighboursRadius(innerRadius));

			return neighbours;
		}
	}
}
