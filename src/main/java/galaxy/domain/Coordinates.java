package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.jme3.math.Vector3f;

public record Coordinates(float x, float y, float z) {

	public Coordinates(Vector3f source) {
		this(source.x, source.y, source.z);
	}

	public Coordinates(JsonNode source) {
		this(source.path("x").floatValue(), source.path("y").floatValue(), source.path("z").floatValue());
	}

	public Vector3f asVector3f() {
		return new Vector3f(x, y, z);
	}
}
