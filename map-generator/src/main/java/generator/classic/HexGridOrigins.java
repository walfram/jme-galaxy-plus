package generator.classic;

import hex.grid.HexCell;
import hex.grid.HexGrid;
import hex.grid.Vector2d;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HexGridOrigins {
	private final int raceCount;
	private final HexGrid grid = new HexGrid(15.0);

	public HexGridOrigins(int raceCount) {
		this.raceCount = raceCount;
	}

	public List<Vector2d> asList(Generator generator) {
		List<HexCell> result = new ArrayList<>(raceCount);

		Set<HexCell> candidates = new HashSet<>();
		Set<HexCell> occupied = new HashSet<>();

		HexCell origin = grid.cell(0, 0);
		result.add(origin);

		occupied.add(origin);
		occupied.addAll(origin.neighbours());

		candidates.addAll(origin.neighbourRing(2));

		while (result.size() < raceCount) {
			long skip = generator.nextLong(candidates.size());
			HexCell cell = candidates.stream().skip(skip).findFirst().orElseThrow();
			candidates.remove(cell);
			result.add(cell);

			occupied.add(cell);
			occupied.addAll(cell.neighbours());

			candidates.addAll(cell.neighbourRing(2));
			candidates.removeAll(occupied);
		}

		return result.stream().map(c -> c.randomPoint(generator)).toList();
	}
}
