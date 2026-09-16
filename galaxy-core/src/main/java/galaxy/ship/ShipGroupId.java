package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public record ShipGroupId(String value) {

	public ShipGroupId() {
		this(UUID.randomUUID().toString());
	}

	public ShipGroupId(JsonNode src) {
		this(src.asText());
	}
}
