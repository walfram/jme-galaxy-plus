package galaxy.domain;

import com.jme3.math.Vector3f;

public record Coordinates(float x, float y, float z) {
	public Coordinates(Vector3f source) {
		this(source.x, source.y, source.z);
	}

	public Vector3f asVector3f() {
		return new Vector3f(x, y, z);
	}
}
