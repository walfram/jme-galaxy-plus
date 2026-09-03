package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record Name(String value) {
	public Name(JsonNode props) {
		this(props.path("name").asText());
	}

	public void serializeTo(ObjectNode props) {
		props.put("name", value);
	}

	public void changeTo(String other) {
	}
}
