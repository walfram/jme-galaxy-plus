package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public record PlanetId(String value) {

	public PlanetId() {
		this(UUID.randomUUID().toString());
	}

	public PlanetId(JsonNode src) {
		this(src.asText());
	}
}
