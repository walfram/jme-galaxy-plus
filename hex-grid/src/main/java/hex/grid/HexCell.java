package hex.grid;

import com.jme3.math.Vector3f;
import jme3utilities.math.noise.Generator;

import java.util.*;

public final class HexCell {

	private static final double SQRT_3 = Math.sqrt(3);

	private static final int[][] NEIGHBOUR_OFFSETS = {{1, 0}, {1, -1}, {0, -1}, {-1, 0}, {-1, 1}, {0, 1}};

	private final int q;
	private final int r;
	private final double radius;

	private final double width;

	public HexCell(int q, int r, double radius) {
		this.q = q;
		this.r = r;
		this.radius = radius;
		this.width = radius * SQRT_3;
	}

	public Vector3f center() {
		double x = radius * (SQRT_3 * q + (SQRT_3 / 2.0) * r);
		double y = radius * (1.5 * r);
		return new Vector3f((float) x, (float) y, 0f);
	}

	public boolean contains(Vector3f v) {
		// Step 1: Compute hex center in world space
		double centerX = radius * (SQRT_3 * q + (SQRT_3 / 2.0) * r);
		double centerY = radius * (1.5 * r);

		// Step 2: Translate point to local space using absolute symmetry
		double dx = Math.abs(v.x - centerX);
		double dy = Math.abs(v.y - centerY);

		// Quick bounding box rejection
		if (dx > width / 2.0 || dy > radius) {
			return false;
		}

		// Step 3: Check against slanted edges
		// Equation derived from line half-plane: (sqrt(3) * dy + dx) <= width
		return (SQRT_3 * dy + dx) <= width;
	}

	public int q() {
		return q;
	}

	public int r() {
		return r;
	}

	public double radius() {
		return radius;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		HexCell that = (HexCell) obj;
		return this.q == that.q &&
				this.r == that.r &&
				Double.doubleToLongBits(this.radius) == Double.doubleToLongBits(that.radius);
	}

	@Override
	public int hashCode() {
		return Objects.hash(q, r, radius);
	}

	@Override
	public String toString() {
		return "HexCell[q=%s, r=%s, radius=%s]".formatted(q, r, radius);
	}

	public Collection<HexCell> neighbours() {
		return Arrays.stream(NEIGHBOUR_OFFSETS)
				.map(offset -> new HexCell(q + offset[0], r + offset[1], radius))
				.toList();
	}

	public Collection<HexCell> neighbourRing(int offset) {
		Set<HexCell> ring = new HashSet<>();

		if (offset <= 0) {
			if (offset == 0) ring.add(new HexCell(q, r, radius));
			return ring;
		}

		// Direction vectors for a pointy hex grid
//		int[][] directions = {{1, 0}, {1, -1}, {0, -1}, {-1, 0}, {-1, 1}, {0, 1}};

		// Start N steps away in direction 4 (bottom-left)
		int currentQ = q + NEIGHBOUR_OFFSETS[4][0] * offset;
		int currentR = r + NEIGHBOUR_OFFSETS[4][1] * offset;

		// Travel along each of the 6 sides of length N
		for (int i = 0; i < 6; i++) {
			for (int step = 0; step < offset; step++) {
				ring.add(new HexCell(currentQ, currentR, radius));
				currentQ += NEIGHBOUR_OFFSETS[i][0];
				currentR += NEIGHBOUR_OFFSETS[i][1];
			}
		}

		return ring;
	}

	public Vector2d randomPoint(Generator generator) {
		double hW = (SQRT_3 / 2.0) * radius; // half width
		double hH = 0.5 * radius;           // half step height

		// Select 1 of 3 rhombuses
		int choice = generator.nextInt(3);
		double u = generator.nextDouble();
		double v = generator.nextDouble();

		double x, y;
		if (choice == 0) {
			// Top-right rhombus
			x = u * hW - v * hW;
			y = u * hH + v * hH + (v * radius); // aligned along axes
		} else if (choice == 1) {
			// Bottom-right rhombus
			x = u * hW + v * hW;
			y = u * hH - v * (1.5 * radius);
		} else {
			// Left rhombus
			x = -u * hW + v * 0;
			y = u * hH + v * radius;
		}

		return new Vector2d(x, y);
	}
}
