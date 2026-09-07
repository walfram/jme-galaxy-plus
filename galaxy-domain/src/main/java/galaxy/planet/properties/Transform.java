package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static java.lang.Math.*;

public record Transform(double x, double y) {
	public Transform(JsonNode transform) {
		this(
				transform.get("x").asDouble(),
				transform.get("y").asDouble()
		);
	}

	public void serializeTo(ObjectNode transform) {
		transform.put("x", x);
		transform.put("y", y);
	}

	public double distanceTo(Transform transform) {
		return sqrt(pow(x - transform.x, 2) + pow(y - transform.y, 2));
	}
}
