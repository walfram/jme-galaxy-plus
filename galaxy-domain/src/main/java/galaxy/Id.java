package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

public record Id(String value) {

	public Id(JsonNode src) {
		this(src.path("id").asText());
	}

	public Id(UUID uuid) {
		this(uuid.toString());
	}

	public Id() {
		this(UUID.randomUUID());
	}

	public void serializeTo(ObjectNode root) {
		root.put("id", value);
	}

}
