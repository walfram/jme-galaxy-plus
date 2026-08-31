package generator.classic;

import hex.grid.Vector2d;
import hex.grid.HexCell;
import hex.grid.HexGrid;
import jme3utilities.math.noise.Generator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HexGridPoint {

	private final HexGrid grid;
	private final Set<Vector2d> points;

	public HexGridPoint(List<Vector2d> origins, Set<Vector2d> points, double minDistance) {
		this.grid = new HexGrid(minDistance * 0.5);
		this.points = new HashSet<>(origins.size() + points.size());
		this.points.addAll(origins);
		this.points.addAll(points);
	}

	public Vector2d point(Generator generator) {
		Set<HexCell> occupied = new HashSet<>(points.size());

		Set<HexCell> candidates = new HashSet<>(points.size());

		for (Vector2d point : points) {
			HexCell cell = grid.toCell(point.x(), point.y());
			occupied.add(cell);
			occupied.addAll(cell.neighbours());

			candidates.addAll(cell.neighbourRing(2));
		}

		candidates.removeAll(occupied);

		long skip = generator.nextLong(candidates.size());
		HexCell c = candidates.stream().skip(skip).findFirst().orElseThrow();

		return c.randomPoint(generator);
	}
}
