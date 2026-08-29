package hex.grid;

import com.jme3.math.Vector3f;

import java.awt.geom.Point2D;

public record HexCell(int q, int r, double radius) {

	private static final double SQRT_3 = Math.sqrt(3);

	public Vector3f center() {
		double x = radius * (SQRT_3 * q + (SQRT_3 / 2.0) * r);
		double y = radius * (1.5 * r);
		return new Vector3f((float) x, (float) y, 0f);
	}
}
