package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record Size(double value) {
	public Size(JsonNode stats) {
		this(stats.get("size").asDouble());
	}

	public void serializeTo(ObjectNode stats) {
		stats.put("size", value);
	}
}
