package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record Resources(double value) {
	public Resources(JsonNode stats) {
		this(stats.path("resources").asDouble());
	}

	public void serializeTo(ObjectNode stats) {
		stats.put("resources", value);
	}
}
