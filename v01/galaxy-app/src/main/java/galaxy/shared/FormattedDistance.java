package galaxy.shared;

import com.jme3.math.Vector3f;

public class FormattedDistance {
	private final Vector3f from;
	private final Vector3f to;

	public FormattedDistance(Vector3f from, Vector3f to) {
		this.from = from;
		this.to = to;
	}

	public String value() {
		return "%.04f".formatted(from.distance(to));
	}
}
