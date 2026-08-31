package hex.grid;

import com.jme3.math.Vector3f;

public class HexGrid {
	private final double radius;

	public HexGrid(double radius) {
		this.radius = radius;
	}

	public HexCell toCell(Vector3f point) {
		return toCell(point.x, point.y);
	}

	public HexCell toCell(double x, double y) {
		// Step 1: Convert to fractional cube coordinates
		double qFrac = (Math.sqrt(3) / 3 * x - 1.0 / 3 * y) / radius;
		double rFrac = (2.0 / 3 * y) / radius;
		double sFrac = -qFrac - rFrac;

		// Step 2: Round to nearest integers
		int q = (int) Math.round(qFrac);
		int r = (int) Math.round(rFrac);
		int s = (int) Math.round(sFrac);

		// Step 3: Fix rounding discrepancies to enforce q + r + s = 0
		double qDiff = Math.abs(q - qFrac);
		double rDiff = Math.abs(r - rFrac);
		double sDiff = Math.abs(s - sFrac);

		if (qDiff > rDiff && qDiff > sDiff) {
			q = -r - s;
		} else if (rDiff > sDiff) {
			r = -q - s;
		}

		return new HexCell(q, r, radius);
	}

	public HexCell cell(int q, int r) {
		return new HexCell(q, r, radius);
	}
}
