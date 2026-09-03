package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record Transform(double x, double y) {
	public Transform(JsonNode transform) {
		this(
				transform.path("x").asDouble(),
				transform.path("y").asDouble()
		);
	}

	public void serializeTo(ObjectNode transform) {
		transform.put("x", x);
		transform.put("y", y);
	}
}
